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

import com.capeelectric.exception.EquipBondException;
import com.capeelectric.exception.PortableApplianceException;
import com.capeelectric.model.PortableAppliance;
import com.capeelectric.repository.PortableApplianceRepository;
import com.capeelectric.service.PortableApplianceService;
import com.capeelectric.util.SLDFindNonRemovedObject;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class PortableApplianceImpl implements PortableApplianceService {
	private static final Logger logger = LoggerFactory.getLogger(PortableApplianceImpl.class);

	@Autowired
	private PortableApplianceRepository patRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private SLDFindNonRemovedObject findNonRemovedObjects;
	
	@Transactional
	@Override
	public PortableAppliance addPAT(PortableAppliance portableAppliance) throws PortableApplianceException {
		logger.info("Called addPAT function");
		if (portableAppliance != null && portableAppliance.getFileName() != null ) {
			Optional<PortableAppliance> patRepo = patRepository.findByFileNameAndNodeId(portableAppliance.getFileName(),portableAppliance.getNodeId());
			
			if(!patRepo.isPresent()) {
				portableAppliance.setCreatedDate(LocalDateTime.now());
				portableAppliance.setUpdatedDate(LocalDateTime.now());
				portableAppliance.setCreatedBy(userFullName.findByUserName(portableAppliance.getUserName()));
				portableAppliance.setUpdatedBy(userFullName.findByUserName(portableAppliance.getUserName()));
				logger.info("Ended addPAT function");
				return patRepository.save(portableAppliance);
			}
			else {
				logger.error("Portable Appliance Node Id "+portableAppliance.getNodeId()+"is already exist for File Name"+portableAppliance.getFileName());
				throw new PortableApplianceException("This Portable Appliance details is already exist for File Name "+portableAppliance.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new PortableApplianceException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<PortableAppliance> retrievePATData(String fileName, String nodeId) throws PortableApplianceException {
		logger.info("Called retrievePATData function");
		PortableAppliance pat =new PortableAppliance();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<PortableAppliance> patRepo = patRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<PortableAppliance> patList =new ArrayList<PortableAppliance>();					
			if(patRepo.isPresent()) {
				patRepo.get().setGeneralTestingPAT(findNonRemovedObjects.findNonRemovedPATData(patRepo));
				logger.debug("Successfully done findNonRemovedPATData() call");	
				pat = patRepo.get();
				patList.add(pat);
				logger.info("Portable Appliance data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrievePATData function");
				return patList;
			}
			else {
				return patList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new PortableApplianceException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public PortableAppliance updatePAT(PortableAppliance portableAppliance) throws PortableApplianceException {
		logger.info("Called updatePAT function");
		if (portableAppliance != null && portableAppliance.getFileName() != null && portableAppliance.getPortableApplianceId() != null) {
			Optional<PortableAppliance> patRepo = patRepository.findByPortableApplianceId(portableAppliance.getPortableApplianceId());
			
			if(patRepo.isPresent()) {
				portableAppliance.setUpdatedDate(LocalDateTime.now());
				portableAppliance.setUpdatedBy(userFullName.findByUserName(portableAppliance.getUserName()));
				logger.info("Ended updatePAT function");
				return patRepository.save(portableAppliance);
			}
			else {
				logger.error("Given Portable Appliance Id is invalid");
				throw new PortableApplianceException("Given Portable Appliance Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new PortableApplianceException("Invalid Inputs");
		}	
	}
	
	
	@Override
	public void deletePAT(String nodeId) throws PortableApplianceException {
		logger.info("called deletePAT function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			patRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted PAT function ");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  PortableApplianceException("Invalid Inputs");
		}
	}	
}
