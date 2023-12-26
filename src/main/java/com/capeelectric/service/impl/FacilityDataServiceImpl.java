package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.FacilityData;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.service.FacilityDataService;
import com.capeelectric.util.EmcStatusUpdate;

@Service
public class FacilityDataServiceImpl implements FacilityDataService {

	private static final Logger logger = LoggerFactory.getLogger(FacilityDataServiceImpl.class);

	@Autowired
	private FacilityDataRepository facilityDataRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	
	@Autowired
	private EmcStatusUpdate emcStatusUpdate;

	@Override
	public void addFacilityData(FacilityData facilityData) throws FacilityDataException {
		if (facilityData != null && facilityData.getUserName() != null) {
			Optional<FacilityData> facilityDataRep = facilityDataRepository.findByEmcId(facilityData.getEmcId());

			Optional<ClientDetails> clientDetailsRepo = clientDetailsRepository
					.findByUserNameAndEmcId(facilityData.getUserName(), facilityData.getEmcId());
			if (clientDetailsRepo.isPresent() && clientDetailsRepo.get().getEmcId().equals(facilityData.getEmcId())) {
				if (!facilityDataRep.isPresent()) {
					facilityData.setCreatedDate(LocalDateTime.now());
					facilityData.setCreatedBy(facilityData.getUserName());
					facilityDataRepository.save(facilityData);
					emcStatusUpdate.updateEmcStatus("step-2 completed",facilityData.getUserName(),facilityData.getEmcId());
					logger.debug("Basic Lps UpdatedBy and UpdatedDate by DownConductor");
				} else {
					logger.error("Given FacilityData Already Exists");
					throw new FacilityDataException("Given FacilityData Already Exists");
				}

			} else {
				logger.error("Given EMC Id is is Not Registered in ClientDetails");
				throw new FacilityDataException("Given EMC Id is is Not Registered in ClientDetails");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new FacilityDataException("Invalid Inputs");
		}

	}

	@Override
	public List<FacilityData> retrieveFacilityData(String userName, Integer emcId) throws FacilityDataException {
		if (userName != null && !userName.isEmpty() && emcId != null) {
			List<FacilityData> facilityDataRep = facilityDataRepository.findByUserNameAndEmcId(userName, emcId);
			if (facilityDataRep != null && !facilityDataRep.isEmpty()) {
				return facilityDataRep;
			} else {
				logger.error("Given UserName & Id doesn't exist in FacilityData Details");
				throw new FacilityDataException("Given UserName & Id doesn't exist in FacilityData Details");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new FacilityDataException("Invalid Inputs");
		}
	}

	@Override
	public void updateFacilityData(FacilityData facilityData) throws FacilityDataException {
		if (facilityData != null && facilityData.getEmcId() != null && facilityData.getUserName() != null) {
			Optional<FacilityData> facilityDataRep = facilityDataRepository.findByEmcId(facilityData.getEmcId());
			if (facilityDataRep.isPresent() && facilityDataRep.get().getEmcId().equals(facilityData.getEmcId())) {
				facilityData.setUpdatedDate(LocalDateTime.now());
				facilityData.setUpdatedBy(facilityData.getUserName());
				facilityDataRepository.save(facilityData);
			} else {
				logger.error("Given Emc Id is Invalid");
				throw new FacilityDataException("Given Emc Id is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new FacilityDataException("Invalid inputs");
		}

	}

}
