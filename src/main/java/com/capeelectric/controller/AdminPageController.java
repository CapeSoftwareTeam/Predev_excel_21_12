package com.capeelectric.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.exception.PdfException;
import com.capeelectric.exception.RegisterPermissionRequestException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.request.RegisterPermissionRequest;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.util.Utility;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "*")
public class AdminPageController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);

	@Value("${app.web.domain}")
	private String webUrl;

	@Autowired
	private RegistrationService registrationService;
	
	private Boolean flagOTP = false;
	
	private Boolean applicationRequestFlag = false;

	@GetMapping("/retrieveAllRegistration")	
	public List<Register> retrieveAllRegistration() throws RegistrationException {
		logger.info("called retrieveAllRegistration function");
		return registrationService.retrieveAllRegistration();
	}

	@PutMapping("/updatePermission")
	public ResponseEntity<String> updatePermission(@RequestBody RegisterPermissionRequest registerPermissionRequest)
			throws RegistrationException, RegisterPermissionRequestException, MessagingException, URISyntaxException, IOException {
		logger.info("called updatePermission function AdminUserName : {}",
				registerPermissionRequest.getAdminUserName());
		Map<String, String> updatePermission = registrationService.updatePermission(registerPermissionRequest);
		//updatePermission having 3 object(username,registerid,isRequiredOtp & name) hardcode default so except this thing will generating Mail
		if (updatePermission.size() > 4 && !registerPermissionRequest.getPermission().equalsIgnoreCase("No")) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(updatePermission.get("registerId")).toUri();
			String resetUrl = Utility.getSiteURL(uri.toURL());
			registrationService.sendEmailForOTPGeneration(updatePermission.get("UserName"),
					(applicationRequestFlag ? "" : getApplicationTypes(updatePermission) + "\n")
							+ (registerPermissionRequest.getComment() != null
									&& !registerPermissionRequest.getComment().isEmpty()
											? "\n" + "Comment : " + registerPermissionRequest.getComment() + "\n" + "\n"
											: "")
							+ (updatePermission.get("isRequiredOtp").equalsIgnoreCase("YES") && flagOTP
									? getOtpMsg(resetUrl, updatePermission.get("UserName"))
									: ""));
			flagOTP = false;
			applicationRequestFlag = false;
		}
		else {
			registrationService.sendEmailForOTPGeneration(updatePermission.get("UserName"),"Hi "+ getName( updatePermission.get("Name"))+","+ "\n"+ " Your request for accessing SOLVE App is Rejected."+ "\n" 
							+ (registerPermissionRequest.getComment() != null
									&& !registerPermissionRequest.getComment().isEmpty()
											? "\n" + "Comment : " + registerPermissionRequest.getComment() + "\n" + "\n"
											: ""));
		}

		return new ResponseEntity<String>("Successfully Updated RegisterPermission", HttpStatus.OK);
	}

	/**
	 * @param resetURL,username
	 * this getOtpMsg funtion returning otp link based given username
	*/
	private String getOtpMsg(String resetUrl, String userName) {

		return "You can generate OTP with this link" + "\n"
				+ (resetUrl.contains("localhost:5000")
						? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
						: "https://www." + webUrl)
				+ "/generateOtp" + ";email=" + userName;
	}

	/**
	 * @param map<String,String> updatePermission 
	 * getApplicationTypes function itrating updatePermission except username,isRequiredOtp&registerId objects
	*/
	private String getApplicationTypes(Map<String, String> updatePermission) {
		String applicationwithPermission = "Hi "+ getName( updatePermission.get("Name"))+","+ "\n" +" Your request for accessing following applications in SOLVE App is Approved/Rejected."
				+ "\n";

		 int i=1;
		for (Map.Entry<String, String> permission : updatePermission.entrySet()) {
			if (!permission.getKey().equalsIgnoreCase("UserName")
					&& !permission.getKey().equalsIgnoreCase("isRequiredOtp")
					&& !permission.getKey().equalsIgnoreCase("Name")
					&& !permission.getKey().equalsIgnoreCase("registerId")) {
				if(permission.getValue().equalsIgnoreCase("Approved")) {
					flagOTP = true; 
				}
				applicationRequestFlag = true;	
				applicationwithPermission += "     "+(i++)+"."+permission.getKey() + " --> " + permission.getValue()+ "\n";
			}
		}
		return applicationwithPermission;
	}
	
	private String getName(String name) {
		return null != name ? name.substring(0, 1).toUpperCase() + name.substring(1, name.length()) : "";
	}
}
