package com.capeelectric.util;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.ClientDetails;
import com.capeelectric.repository.ClientDetailsRepository;

@Configuration
public class EmcStatusUpdate {
	
	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	
	public void updateEmcStatus(String status, String userName, Integer emcId) {
		Optional<ClientDetails> clientDetails = clientDetailsRepository.findByUserNameAndEmcId(userName,emcId);
		if(clientDetails.isPresent()) {
			clientDetails.get().setAllStepsCompleted(status);
			clientDetails.get().setUpdatedDate(LocalDateTime.now());
			clientDetailsRepository.save(clientDetails.get());
		}
	}
}
