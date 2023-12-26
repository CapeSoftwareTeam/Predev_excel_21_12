package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.licence.License;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.LicenseRepository;
import com.capeelectric.service.ClientDetailsService;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(ClientDetailsServiceImpl.class);

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	private ClientDetails clientDetailsData;
	
	@Autowired
	private LicenseRepository licenseRepository;

	@Transactional
	@Override

	public ClientDetails saveClientDetails(ClientDetails clientDetails) throws ClientDetailsException {

		if (clientDetails != null && clientDetails.getUserName() != null) {

			Optional<ClientDetails> clientEMCDetailsRepo = clientDetailsRepository
					.findByClientNameAndStatus(clientDetails.getClientName(), "Active");
			logger.debug("Basic Client Repo data available");

			if (!clientEMCDetailsRepo.isPresent() || (clientEMCDetailsRepo.isPresent()
					&& clientEMCDetailsRepo.get().getStatus().equals("InActive"))) {
				clientDetails.setStatus("Active");
				clientDetails.setCreatedDate(LocalDateTime.now());
				clientDetails.setCreatedBy(clientDetails.getUserName());
				clientDetails.setUpdatedBy(clientDetails.getUserName());
				clientDetails.setUpdatedDate(LocalDateTime.now());
				clientDetails.setAllStepsCompleted("step-1 completed");
				return clientDetailsRepository.save(clientDetails);
			} else {
				logger.error("Client name " + clientDetails.getClientName() + " already exists");
				throw new ClientDetailsException("Client name " + clientDetails.getClientName() + " already exists");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new ClientDetailsException("Invalid Inputs");
		}

	}

	@Override
	public List<ClientDetails> retrieveClientDetails(String userName, Integer emcId) throws ClientDetailsException {
		if (userName != null && !userName.isEmpty() && emcId != null) {
			List<ClientDetails> clientDetailsRepo = clientDetailsRepository.findByEmcId(emcId);
			if (clientDetailsRepo != null && !clientDetailsRepo.isEmpty()) {
				return clientDetailsRepo;
			} else {
				logger.error("Given EmcId doesn't exist in ClientDetails");
				throw new ClientDetailsException("Given EmcId doesn't exist in ClientDetails");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new ClientDetailsException("Invalid Inputs");
		}
	}

	@Transactional
	@Override
	public void updateClientDetails(ClientDetails clientDetails) throws ClientDetailsException {

		if (clientDetails != null && clientDetails.getUserName() != null && clientDetails.getEmcId() != null) {
			Optional<ClientDetails> clientDetailsRepo1 = clientDetailsRepository
					.findByClientNameAndStatus(clientDetails.getClientName(),"Active");
			Optional<ClientDetails> clientDetailsRepo = clientDetailsRepository.findById(clientDetails.getEmcId());
			if (!clientDetailsRepo1.isPresent()
					|| clientDetailsRepo1.get().getClientName().equals(clientDetailsRepo.get().getClientName())) {
				if (clientDetailsRepo.isPresent()
						&& clientDetailsRepo.get().getEmcId().equals(clientDetails.getEmcId())) {
					clientDetails.setUpdatedBy(clientDetails.getUserName());
					clientDetails.setUpdatedDate(LocalDateTime.now());
					clientDetailsRepository.save(clientDetails);
				} else {
					logger.error("Given Emc Id is Invalid");
					throw new ClientDetailsException("Given Emc Id is Invalid");
				}
			} else {
				logger.error("Client name " + clientDetails.getClientName() + " already exists");
				throw new ClientDetailsException("Client name " + clientDetails.getClientName() + " already exists");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new ClientDetailsException("Invalid inputs");
		}
		logger.info("Ended updateBasicLpsDetails function");

	}

	@Override
	public void updateClientDetailsStatus(ClientDetails clientDetails) throws ClientDetailsException {
		if (clientDetails != null && clientDetails.getUserName() != null && clientDetails.getEmcId() != null) {
			List<ClientDetails> clientDetailsRepo = clientDetailsRepository.findByEmcId(clientDetails.getEmcId());

			if (clientDetailsRepo != null && !clientDetailsRepo.isEmpty() && clientDetailsRepo.get(0) != null) {
				if (null == clientDetailsRepo.get(0).getAllStepsCompleted()
						|| !clientDetailsRepo.get(0).getAllStepsCompleted().equalsIgnoreCase("AllStepCompleted")) {
					Optional<License> licence = licenseRepository.findByUserName(clientDetails.getUserName());
					licence.get().setEmcNoOfLicence(String.valueOf(Integer.parseInt(licence.get().getEmcNoOfLicence())+1));
					licenseRepository.save(licence.get());
				}
				clientDetailsData = clientDetailsRepo.get(0);
				clientDetailsData.setStatus("InActive");
				clientDetailsData.setUpdatedDate(LocalDateTime.now());	
				clientDetailsData.setUpdatedBy(clientDetails.getUserName());
				clientDetailsRepository.save(clientDetailsData);
			} else {
				logger.error("Given Emc Id is Invalid");
				throw new ClientDetailsException("Given Emc Id is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new ClientDetailsException("Invalid inputs");
		}

	}

	@Override
	public Optional<ClientDetails> licenseClientDetails(String userName) {
		return clientDetailsRepository.findByEmailAndStatus(userName, "Active");
	}
	
	/*Validating Client Name*/
	@Override
	public Optional<ClientDetails> findingClientName(String clientName) throws ClientDetailsException {
		logger.info("Called findingClientName function");

		Optional<ClientDetails> clientDetailsRepo = clientDetailsRepository.findByClientNameAndStatus(clientName,
				"Active");

		if (clientDetailsRepo.isPresent()) {

			List<ClientDetails> clientList = new ArrayList<ClientDetails>();
			clientList.add(clientDetailsRepo.get());

			return clientDetailsRepo;
		}
		return null;

	}
}
