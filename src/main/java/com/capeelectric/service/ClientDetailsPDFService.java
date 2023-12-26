package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.model.ClientDetails;


public interface ClientDetailsPDFService {

	public void printClientDetails(String userName, Integer emcId, List<ClientDetails> clientDetailsRepo) throws ClientDetailsException;

//	public void printClientDetails(String userName, Integer emcId) throws ClientDetailsException;
	
}
