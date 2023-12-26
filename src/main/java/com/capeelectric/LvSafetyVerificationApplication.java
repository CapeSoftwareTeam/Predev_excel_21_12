package com.capeelectric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.capeelectric.response.JwtDetails;
import com.google.gson.Gson;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class LvSafetyVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LvSafetyVerificationApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public JwtDetails getJwtToken() {
		Gson gson = new Gson();
	    String secretName = "jwt/token";
	    String region = "ap-south-1";
	    AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

	    try {
	    	getSecretValueResult = client.getSecretValue(getSecretValueRequest);
	    	return gson.fromJson(getSecretValueResult.getSecretString(), JwtDetails.class);
	    } catch (Exception e) {
	        // For a list of exceptions thrown, see
	        // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
	        throw e;
	    }
	}

}
