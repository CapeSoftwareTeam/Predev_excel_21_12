package com.capeelectric.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.util.Constants;
import com.capeelectric.util.Utility;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v2")
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private RegistrationService registrationService;
	
	@Value("${app.web.domain}")
	private String webUrl;

	@PostMapping("/addRegistration")
	public ResponseEntity<Void> addRegistration(@RequestBody Register register)
			throws RegistrationException, MessagingException, MalformedURLException, URISyntaxException {
		logger.debug("Called addRegistration function UserName : {}", register.getUsername());
		Register createdRegister = registrationService.addRegistration(register);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdRegister.getRegisterId()).toUri();
		String resetUrl = Utility.getSiteURL(uri.toURL());
		if (createdRegister.getPermission().equalsIgnoreCase("YES")) {
			registrationService.sendEmailForOTPGeneration(register.getUsername(), Constants.EMAIL_SUBJECT_REGISTRATION + "\n" + "\n"
					+ (resetUrl.contains("localhost:5000")
							? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
							: "https://www."+webUrl)
					+ "/generateOtp" + ";email=" + register.getUsername());
			logger.debug("AwsEmailService call Successfully Ended");
		} else {
			registrationService
					.sendEmailToAdmin("Please Approve or Reject the inspector by Logging to " + "Admin Portal for User "
							+ register.getName() + " and Company " + register.getCompanyName() + " with their Email "
							+ register.getUsername() + ". You can login to admin Portal with this link " + "\n"
							+ (resetUrl.contains("localhost:5000")	
									? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
									: "https://admin."+webUrl)
							+ "/admin");
			logger.debug("AwsEmailService call Successfully Ended");
		}

		return ResponseEntity.created(uri).build();
	}
	 
	@PostMapping("/addViewerRegistration")
	public ResponseEntity<Void> addViewerRegistration(@RequestBody Register register)
			throws RegistrationException, MessagingException, MalformedURLException, CompanyDetailsException, URISyntaxException {
		logger.debug("called addRegistration function UserName : {}", register.getUsername());
		Register createdRegister = registrationService.addViewerRegistration(register);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdRegister.getRegisterId()).toUri();
		String resetUrl = Utility.getSiteURL(uri.toURL());
		registrationService.sendEmailForOTPGeneration(createdRegister.getUsername(), Constants.EMAIL_SUBJECT_REGISTRATION + "\n" + "\n"
				+ (resetUrl.contains("localhost:5000")
						? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
						: "https://www."+webUrl)
				+ "/generateOtp" + ";email=" + register.getUsername());
		logger.debug("AwsEmailService Successfully call Ended");
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/retrieveRegistration/{userName}")
	public Optional<Register> retrieveRegistration(@PathVariable String userName) throws RegistrationException {
		logger.debug("called retrieveRegistration function UserName : {}", userName);
		return registrationService.retrieveRegistration(userName);
	}
	
	/**
	 * Retrieving license based on project
	*/
	@GetMapping("/retrieveRegistration/{userName}/{project}")
	public ResponseEntity<?> retrieveRegistrationWithProject(@PathVariable String userName,@PathVariable String project) throws RegistrationException {
		logger.debug("called retrieveRegistration function UserName : {}, ProjectName is : {}", userName,project);
		return new ResponseEntity<>(registrationService.retrieveRegistrationWithProject(userName,project), HttpStatus.OK);
	}	
	
	/**
	 * retrieveAllProjectLicense based on username
	*/
	@GetMapping("/retrieveAllProjectLicense/{userName}")
	public ResponseEntity<?> retrieveAllProjectLicense(@PathVariable String userName) throws RegistrationException {
		logger.debug("called retrieveAllProjectLicense function UserName : {}", userName);
		return new ResponseEntity<>(registrationService.retrieveAllProjectLicense(userName), HttpStatus.OK);
	}

	@PutMapping("/updateRegistration/{adminApproveRequired}")
	public ResponseEntity<String> updateRegistration(@RequestBody Register register,@PathVariable("adminApproveRequired") Boolean adminApproveRequired)
			throws IOException, MessagingException, RegistrationException, CompanyDetailsException, URISyntaxException {
		logger.debug("called updateRegistration function UserName : {}, IsLicenseUpdate : {}", register.getUsername(),
				adminApproveRequired);
		registrationService.updateRegistration(register, adminApproveRequired);
 		logger.debug("updateRegistration function Successfully call Ended");
		return new ResponseEntity<String>("Successfully Updated Registration", HttpStatus.OK);
	}
	
	@GetMapping("/sendOtp/{userName}/{mobileNumber}")
	public ResponseEntity<Void> sendOtp(@PathVariable String userName,@PathVariable String mobileNumber)
			throws IOException, MessagingException, RegistrationException {
		logger.debug("called sendOtp function UserName : {}, MobileNumber : {}", userName, mobileNumber);
		logger.debug("Registration Service call started for Send Otp");
		registrationService.sendOtp(userName, mobileNumber);
		logger.debug("Registration Service called successfully for Send Otp");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/newOtpGeneration/{username}/{mobileNumber}")
	public String sendNewOtp(@PathVariable String username,@PathVariable String mobileNumber)
			throws IOException, MessagingException, RegistrationException {
		logger.debug("called sendOtp function UserName : {}, MobileNumber : {}", username, mobileNumber);
		return registrationService.sendNewOtp(username,mobileNumber);
	}	
	
	@PutMapping("/updateLicence/{userName}/{numoflicence}/{project}")
	public ResponseEntity<String> updateLicence(@PathVariable String userName, @PathVariable String numoflicence, @PathVariable String project)
			throws RegistrationException {
		logger.debug("called updateLicence function UserName : {}, project : {}", userName,project);
		registrationService.updateLicence(userName, numoflicence,project);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/retrieveUserNameFromRegister/{userName}")
	public String retrieveUserNameFromRegister(@PathVariable String userName) throws RegistrationException {
		logger.debug("called retrieveUserName function UserName : {}", userName);
		return registrationService.retrieveUserNameFromRegister(userName);
	}
	
	@GetMapping("/isMobileNumberAvailable/{mobileNumber}")
	public String isMobileNumberAvailable(@PathVariable String mobileNumber)throws RegistrationException{
		logger.debug("called retrieveAllMobileNumber function mobileNumber : {}",mobileNumber);
		return registrationService.isMobileNumberAvailable(mobileNumber);
	}
}
