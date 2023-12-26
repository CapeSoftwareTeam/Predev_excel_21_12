package com.capeelectric.service;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.RegisterPermissionRequestException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.request.RegisterPermissionRequest;



/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface RegistrationService {


	public Register addRegistration(Register inspector) throws RegistrationException;

	public Optional<Register> retrieveRegistration(String userName) throws RegistrationException;

	public void updateRegistration(Register inspector, Boolean adminApproveRequired) throws RegistrationException, CompanyDetailsException, MalformedURLException, MessagingException, URISyntaxException;

	public void sendOtp(String userName, String mobileNumber) throws RegistrationException;

	public Register addViewerRegistration(Register register) throws RegistrationException, CompanyDetailsException;

	public void updateLicence(String userName, String numoflicence, String project) throws RegistrationException;
	
	public String sendNewOtp(String username, String mobileNumber) throws RegistrationException;

	public Map<String, String> updatePermission(RegisterPermissionRequest registerPermissionRequest) throws RegisterPermissionRequestException;
	
	public List<Register> retrieveAllRegistration() throws RegistrationException;
	
	public String retrieveUserNameFromRegister(String userName) throws RegistrationException;
	
	public void sendEmail(String email, String content) throws URISyntaxException;
	
	public void sendEmailToAdmin(String content) throws URISyntaxException;
	
	public void sendEmailForComments(String toEmail, String ccEmail, String content) throws URISyntaxException;
	
	public void sendEmailPDF(String userName, Integer siteId, int count, String keyname);
	
	public void sendEmailForOTPGeneration(String email, String content) throws URISyntaxException;
	
	public void sendEMCEmailPDF(String userName, Integer id, int count, String keyname) ;

	//public Optional<Register> retrieveFromRegister(String userName);

	public Optional<?> retrieveRegistrationWithProject(String userName, String project);

	public  Optional<?> retrieveAllProjectLicense(String userName);

	public  String isMobileNumberAvailable(String mobileNumber) throws RegistrationException;

}
