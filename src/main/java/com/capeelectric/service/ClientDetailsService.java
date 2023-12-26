package com.capeelectric.service;

import java.util.List;
import java.util.Optional;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.model.ClientDetails;

public interface ClientDetailsService {

	public ClientDetails saveClientDetails(ClientDetails clientDetails) throws ClientDetailsException;

	public List<ClientDetails> retrieveClientDetails(String userName, Integer emcId) throws ClientDetailsException;

	public void updateClientDetails(ClientDetails clientDetails) throws ClientDetailsException;
	
	public void updateClientDetailsStatus(ClientDetails clientDetails) throws ClientDetailsException;

	public Optional<ClientDetails> licenseClientDetails(String userName);

	public Optional<ClientDetails> findingClientName(String clientName) throws ClientDetailsException;

}
