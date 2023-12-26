/**
 * 
 */
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

import com.capeelectric.exception.ProtectiveEarthConductorException;
import com.capeelectric.model.ProtectiveEarthConductor;
import com.capeelectric.repository.ProtectiveEarthConductorRepository;
import com.capeelectric.service.ProtectiveEarthConductorService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class ProtectiveEarthConductorServiceImpl implements ProtectiveEarthConductorService {
	private static final Logger logger = LoggerFactory.getLogger(ProtectiveEarthConductorServiceImpl.class);

	@Autowired
	private ProtectiveEarthConductorRepository protectiveEarthConductorRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public ProtectiveEarthConductor addPEC(ProtectiveEarthConductor protectiveEarthConductor) throws ProtectiveEarthConductorException {
		logger.info("Called addPEC function");
		if (protectiveEarthConductor != null && protectiveEarthConductor.getFileName() != null ) {
			Optional<ProtectiveEarthConductor> protectiveEarthConductorRepo = protectiveEarthConductorRepository.findByFileNameAndCableConnectorId(protectiveEarthConductor.getFileName(),protectiveEarthConductor.getCableConnectorId());
			
			if(!protectiveEarthConductorRepo.isPresent()) {
				protectiveEarthConductor.setCreatedDate(LocalDateTime.now());
				protectiveEarthConductor.setUpdatedDate(LocalDateTime.now());
				protectiveEarthConductor.setCreatedBy(userFullName.findByUserName(protectiveEarthConductor.getUserName()));
				protectiveEarthConductor.setUpdatedBy(userFullName.findByUserName(protectiveEarthConductor.getUserName()));
				logger.info("Ended addEquipBond function");
				return protectiveEarthConductorRepository.save(protectiveEarthConductor);
			}
			else {
				logger.error("Node Id "+protectiveEarthConductor.getCableConnectorId()+"is already exist for File Name "+protectiveEarthConductor.getFileName());
				throw new ProtectiveEarthConductorException("This ProtectiveEarthConductor details is already exist for File Name "+protectiveEarthConductor.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new ProtectiveEarthConductorException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<ProtectiveEarthConductor> retrievePECData(String fileName, String nodeId) throws ProtectiveEarthConductorException {
		logger.info("Called retrievePECData function");
		ProtectiveEarthConductor protectiveEarthConductor =new ProtectiveEarthConductor();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<ProtectiveEarthConductor> protectiveEarthConductorRepo = protectiveEarthConductorRepository.findByFileNameAndCableConnectorId(fileName,nodeId);
			List<ProtectiveEarthConductor> protectiveEarthConductorList =new ArrayList<ProtectiveEarthConductor>();
			if(protectiveEarthConductorRepo.isPresent()) {			
				protectiveEarthConductor = protectiveEarthConductorRepo.get();
				protectiveEarthConductorList.add(protectiveEarthConductor);
				logger.info("ProtectiveEarthConductor data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrievePECData function");
				return protectiveEarthConductorList;
			}
			else {
				return protectiveEarthConductorList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new ProtectiveEarthConductorException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public ProtectiveEarthConductor updatePEC(ProtectiveEarthConductor protectiveEarthConductor) throws ProtectiveEarthConductorException {
		logger.info("Called updatePEC function");
		if (protectiveEarthConductor != null && protectiveEarthConductor.getFileName() != null && protectiveEarthConductor.getSafetyTestingPECId() != null) {
			Optional<ProtectiveEarthConductor> protectiveEarthConductorRepo = protectiveEarthConductorRepository.findBySafetyTestingPECId(protectiveEarthConductor.getSafetyTestingPECId());
			
			if(protectiveEarthConductorRepo.isPresent()) {
				protectiveEarthConductor.setUpdatedDate(LocalDateTime.now());
				protectiveEarthConductor.setUpdatedBy(userFullName.findByUserName(protectiveEarthConductor.getUserName()));
				logger.info("Ended updatePEC function");
				return protectiveEarthConductorRepository.save(protectiveEarthConductor);
			}
			else {
				logger.error("Given ProtectiveEarthConductor Id is invalid");
				throw new ProtectiveEarthConductorException("Given EquipBond Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new ProtectiveEarthConductorException("Invalid Inputs");
		}	
	}

}
