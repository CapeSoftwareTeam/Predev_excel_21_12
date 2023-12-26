package com.capeelectric.config;

import org.springframework.stereotype.Component;

import com.capeelectric.util.Constants;

@Component
public class OtpConfig {
	
//	@Value("${sms.otp.send}")
	private String sendOtp = Constants.SMS_OTP_SEND;
	
//	@Value("${sms.otp.verify}")	
	private String verifyOtp  = Constants.SMS_OTP_VERIFY;

	public String getSendOtp() {
		return sendOtp;
	}

	public void setSendOtp(String sendOtp) {
		this.sendOtp = sendOtp;
	}

	public String getVerifyOtp() {
		return verifyOtp;
	}

	public void setVerifyOtp(String verifyOtp) {
		this.verifyOtp = verifyOtp;
	}
	
	

}
