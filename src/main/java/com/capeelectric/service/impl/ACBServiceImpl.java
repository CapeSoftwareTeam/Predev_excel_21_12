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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capeelectric.exception.ACBException;
import com.capeelectric.exception.EarthConnectorException;
import com.capeelectric.model.ACB;
import com.capeelectric.repository.ACBRepository;
import com.capeelectric.service.ACBService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class ACBServiceImpl implements ACBService {
	private static final Logger logger = LoggerFactory.getLogger(ACBServiceImpl.class);

	@Autowired
	private ACBRepository acbRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public ACB addACB(ACB acb) throws ACBException {
		logger.info("Called addACB function");
		if (acb != null && acb.getFileName() != null ) {
			Optional<ACB> acbRepo = acbRepository.findByFileNameAndNodeId(acb.getFileName(),acb.getNodeId());
			
			if(!acbRepo.isPresent()) {
				acb.setCreatedDate(LocalDateTime.now());
				acb.setUpdatedDate(LocalDateTime.now());
				acb.setCreatedBy(userFullName.findByUserName(acb.getUserName()));
				acb.setUpdatedBy(userFullName.findByUserName(acb.getUserName()));
				logger.info("Ended addACB function");
				return acbRepository.save(acb);
			}
			else {
				logger.error("Node Id "+acb.getNodeId()+"is already exist for File Name "+acb.getFileName());
				throw new ACBException("This ACB details is already exist for File Name "+acb.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new ACBException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<ACB> retrieveACBData(String fileName, String nodeId) throws ACBException {
		logger.info("Called retrieveACBData function");
		ACB acb =new ACB();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<ACB> acbRepo = acbRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<ACB> acbList =new ArrayList<ACB>();
			if(acbRepo.isPresent()) {			
				acb = acbRepo.get();
				acbList.add(acb);
				logger.info("ACB data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveACBData function");
				return acbList;
			}
			else {
				return acbList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new ACBException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public ACB updateACB(ACB acb) throws ACBException {
		logger.info("Called updateACB function");
		if (acb != null && acb.getFileName() != null && acb.getAcbID() != null) {
			Optional<ACB> acbRepo = acbRepository.findByAcbID(acb.getAcbID());
			
			if(acbRepo.isPresent()) {
				acb.setUpdatedDate(LocalDateTime.now());
				acb.setUpdatedBy(userFullName.findByUserName(acb.getUserName()));
				logger.info("Ended updateACB function");
				return acbRepository.save(acb);
			}
			else {
				logger.error("Given ACB Id is invalid");
				throw new ACBException("Given ACB Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new ACBException("Invalid Inputs");
		}	
	}
	
	@Override
	public void deleteACB(String nodeId) throws ACBException {
		logger.info("called deleteACB function");
		if (nodeId != null && !nodeId.isEmpty()) {
			acbRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully deleted ACB");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  ACBException("Invalid Inputs");
		}
	}	



}
