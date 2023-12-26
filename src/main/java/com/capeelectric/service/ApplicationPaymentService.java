package com.capeelectric.service;

import java.util.List;
import com.capeelectric.exception.PaymentException;
import com.capeelectric.model.ApplicationLicense;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface ApplicationPaymentService {

	public void addPaymentDetails(ApplicationLicense applicationLicense) throws PaymentException;

	public void updatePaymentStatus(String status, String orderId);

	public List<ApplicationLicense> retrivePaymentStatus(String username);

}
