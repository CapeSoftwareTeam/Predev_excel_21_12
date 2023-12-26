package com.capeelectric.service.impl;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.AWSConfiguration;
import com.capeelectric.exception.PaymentException;
import com.capeelectric.model.ApplicationLicense;
import com.capeelectric.model.licence.License;
import com.capeelectric.repository.ApplicationPaymentRepo;
import com.capeelectric.repository.LicenseRepository;
import com.capeelectric.service.ApplicationPaymentService;
import com.capeelectric.util.UserFullName;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class ApplicationPaymentServiceImp implements ApplicationPaymentService {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationPaymentServiceImp.class);

	@Autowired
	private ApplicationPaymentRepo applicationPaymentRepo;

	@Autowired
	private LicenseRepository licenseRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserFullName userFullName;

	@Autowired
	private AWSConfiguration awsConfiguration;

	/**
	 * @param applicationLicense addPaymentDetails function saving applicationlicense details into DB
	 * return void
	 */
	@Override
	public void addPaymentDetails(ApplicationLicense applicationLicense) throws PaymentException {
		if (null != applicationLicense && null != applicationLicense.getInspectorEmail()) {
			applicationLicense.setStatus("INITIATED");
			applicationLicense.setCreatedDate(LocalDateTime.now());
			applicationLicense.setCreatedBy(applicationLicense.getInspectorName());
			applicationLicense.setUpdatedBy(applicationLicense.getInspectorName());
			applicationLicense.setUpdatedDate(LocalDateTime.now());

			logger.debug("Sucessfully Saved ApplicationLicense details into DB");
			applicationPaymentRepo.save(applicationLicense);
		} else {
			logger.error("Invalid Input");
			throw new PaymentException("Invalid Input");
		}

	}

	/**
	 * @param status, orderId 
	 * this function updating payment status whether success or failed for given orderId, then 
	 * updating license value if status is success and trigger a email to inspector
	 * @return void
	 * 
	 */

	@Override
	public void updatePaymentStatus(String status, String orderId) {
		Optional<ApplicationLicense> cutomerRepo = applicationPaymentRepo.findByOrderId(orderId);
		if (cutomerRepo.isPresent()) {
			logger.debug("Sucessfully updated OrderStatus into DB...  OrderStatus is [{}]", status);
			cutomerRepo.get().setUpdatedDate(LocalDateTime.now());
			cutomerRepo.get().setStatus(status);
			ApplicationLicense applicationLicenseRepo = applicationPaymentRepo.save(cutomerRepo.get());

			// if payment transaction is SUCCESS
			if (applicationLicenseRepo.getStatus() != null
					&& !applicationLicenseRepo.getStatus().equalsIgnoreCase("SUCCESS")) {
				Optional<License> licenseRepo = licenseRepository
						.findByUserName(applicationLicenseRepo.getInspectorEmail());
				if (licenseRepo.isPresent()) {
					logger.debug("Insepector license is updated");
					getApplicationModel(licenseRepo, applicationLicenseRepo.getApplicationName(),
							applicationLicenseRepo.getNoofLicense());
				} else {
					logger.debug("Insepector doesnt have license");
					Optional<License> license = Optional.of(new License());
					license.get().setUserName(applicationLicenseRepo.getInspectorEmail());
					getApplicationModel(license, applicationLicenseRepo.getApplicationName(),
							applicationLicenseRepo.getNoofLicense());
				}
				sendEmail(applicationLicenseRepo.getInspectorEmail(), getEmailContent(
						applicationLicenseRepo.getInspectorEmail(), applicationLicenseRepo.getApplicationName()));
				logger.debug(
						"Sucessfully updated " + applicationLicenseRepo.getInspectorEmail() + " "
								+ applicationLicenseRepo.getApplicationName() + " License [{}]",
						applicationLicenseRepo.getNoofLicense());
				licenseRepository.save(licenseRepo.get());
			}
		}
	}

	/**
	 * 
	 * @param email
	 * @param application
	 * @return email content for success payment
	 */
	private String getEmailContent(String email, String application) {
		return "Hi " + userFullName.findByUserName(email) + "," + "\n" +"  "+ application
				+ " License has been successfully purchased and it would get reflected in the license bucket."
				+ "\n";
	}

	private Optional<License> getApplicationModel(Optional<License> licenseRepo, String applicationName,
			Integer noofLicense) {
		switch (applicationName) {
		case "LV": {
			licenseRepo.get().setLvNoOfLicence(Integer.toString(noofLicense));
			break;
		}
		case "LPS": {
			licenseRepo.get().setLpsNoOfLicence(Integer.toString((noofLicense)));
			break;
		}
		case "RISK": {
			licenseRepo.get().setRiskNoOfLicence(Integer.toString((noofLicense)));
			break;
		}
		case "EMC": {
			licenseRepo.get().setEmcNoOfLicence(Integer.toString((noofLicense)));
			break;
		}
		}
		return licenseRepo;
	}

	/**
	 * @param username this function returning listoflicense for given username
	 */

	@Override
	public List<ApplicationLicense> retrivePaymentStatus(String username) {
		logger.debug("retrivePaymentStatus service call started");
		return applicationPaymentRepo.findByInspectorEmail(username);
	}

	/**
	 * 
	 * @param email
	 * @param content sending email
	 */

	private void sendEmail(String email, String content) {
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		restTemplate.exchange(awsConfiguration.getSendEmail() + email + "/" + content, HttpMethod.GET, null,
				String.class);

		logger.debug("Cape-Electric-AWS-Email service Response was successfully");

	}

}
