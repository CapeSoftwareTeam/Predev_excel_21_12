package com.capeelectric.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.config.PaymentConfig;
import com.capeelectric.exception.PaymentException;
import com.capeelectric.model.ApplicationLicense;
import com.capeelectric.model.RazorPay;
import com.capeelectric.model.Response;
import com.capeelectric.service.ApplicationPaymentService;
import com.google.gson.Gson;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class PaymentController {

	@Autowired	
	private PaymentConfig paymentConfig;
	
	@Autowired	
	private ApplicationPaymentService applicationPaymentService;

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	private RazorpayClient client;

	private static Gson gson = new Gson();

	/**
	 * 
	 * @param applicationLicense in Requestbody
	 * createOrder function creating razorpay orderID for customer then storing that info to corresponding table 
	 * @return corresponding checkout information for razorpay payment
	 * @throws RazorpayException
	 * @throws PaymentException
	 */
	@PostMapping(value = "/applicationPayment")
	public ResponseEntity<String> createOrder(@RequestBody ApplicationLicense applicationLicense)
			throws RazorpayException, PaymentException {
		this.client = new RazorpayClient(paymentConfig.getSecretId(), paymentConfig.getSecretKey());
		try {
			logger.debug("Order creation started");
			Order order = createRazorPayOrder(applicationLicense.getLicenseAmount());
			RazorPay razorPay = getApplicationRazorPay((String) order.get("id"), applicationLicense);
			applicationLicense.setOrderId(order.get("id"));
			applicationPaymentService.addPaymentDetails(applicationLicense);
			return new ResponseEntity<String>(gson.toJson(getResponse(razorPay, 200)), HttpStatus.OK);
		} catch (RazorpayException e) {
			logger.debug("Order creation failed" + e.getMessage());
			return new ResponseEntity<String>(gson.toJson(getResponse(new RazorPay(), 500)),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * @param status
	 * @param orderId
	 * updating payment status to orderId table
 	 * @return
	 * @throws RazorpayException
	 */
	
	@PutMapping(value = "/updateAppPayStatus/{status}/{orderId}")
	public ResponseEntity<Void> updateAppPayStatus(@PathVariable String status, @PathVariable String orderId)
			throws RazorpayException {
		logger.debug("updatePaymentStatus function started.. OrderId: {}, OrderStatus: {}, ApplicationName: {} ",
				orderId, status);
		applicationPaymentService.updatePaymentStatus(status, orderId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/**
	 * @param username
	 * @return fetching payment information list for corresponding user
	 * @throws RazorpayException
	 */
	@GetMapping(value = "/retriveAppPayStatus/{username}")
	public ResponseEntity<List<ApplicationLicense>> retriveAppPayStatus(@PathVariable String username)
			throws RazorpayException {
		logger.debug("retrivePaymentStatus function started.. username: {} ", username);
		return new ResponseEntity<List<ApplicationLicense>>(applicationPaymentService.retrivePaymentStatus(username),
				HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param razorPay
	 * @param statusCode
	 * @return
	 */
	private Response getResponse(RazorPay razorPay, int statusCode) {
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setRazorPay(razorPay);
		return response;
	}

	/**
	 * 
	 * @param orderId
	 * @param applicationLicense
	 * @return
	 */
	private RazorPay getApplicationRazorPay(String orderId, ApplicationLicense applicationLicense) {
		RazorPay razorPay = new RazorPay();
//		razorPay.setApplicationFee(convertRupeeToPaise(customer.getAmount()));
		razorPay.setCustomerName(applicationLicense.getInspectorName());
		razorPay.setCustomerEmail(applicationLicense.getInspectorEmail());
		razorPay.setRazorpayOrderId(orderId);
		razorPay.setSecretKey(paymentConfig.getSecretId());
		razorPay.setNotes("notes" + orderId);
		return razorPay;
	}

	/**
	 * 
	 * @param amount
	 * @return client order
	 * @throws RazorpayException
	 */
	
	private Order createRazorPayOrder(String amount) throws RazorpayException {
		JSONObject options = new JSONObject();
		options.put("amount", convertRupeeToPaise(amount));
		// TODO need to changes
		options.put("currency", "INR");
//		options.put("receipt", "txn_123456");
//		options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
		return client.Orders.create(options);
	}

	/**
	 * 
	 * @param paise
	 * @return
	 */
	private String convertRupeeToPaise(String paise) {
		BigDecimal b = new BigDecimal(paise);
		BigDecimal value = b.multiply(new BigDecimal("100"));
		return value.setScale(0, RoundingMode.UP).toString();
	}
}
