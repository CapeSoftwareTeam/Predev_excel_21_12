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

import com.capeelectric.exception.LTMotorException;
import com.capeelectric.exception.MCCBException;
import com.capeelectric.model.LTMotor;
import com.capeelectric.repository.LTMotorRepository;
import com.capeelectric.service.LTMotorService;
import com.capeelectric.util.UserFullName;


/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class LTMotorServiceImpl implements LTMotorService {
	private static final Logger logger = LoggerFactory.getLogger(LTMotorServiceImpl.class);

	@Autowired
	private LTMotorRepository ltMotorRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public LTMotor addLTMotor(LTMotor ltMotor) throws LTMotorException {
		logger.info("Called addLTMotor function");
		if (ltMotor != null && ltMotor.getFileName() != null ) {
			Optional<LTMotor> ltMotorRepo = ltMotorRepository.findByFileNameAndNodeId(ltMotor.getFileName(),ltMotor.getNodeId());
			
			if(!ltMotorRepo.isPresent()) {
				ltMotor.setCreatedDate(LocalDateTime.now());
				ltMotor.setUpdatedDate(LocalDateTime.now());
				ltMotor.setCreatedBy(userFullName.findByUserName(ltMotor.getUserName()));
				ltMotor.setUpdatedBy(userFullName.findByUserName(ltMotor.getUserName()));
				logger.info("Ended addLTMotor function");
				return ltMotorRepository.save(ltMotor);
			}
			else {
				logger.error("Node Id "+ltMotor.getNodeId()+"is already exist for File Name "+ltMotor.getFileName());
				throw new LTMotorException("Node Id "+ltMotor.getNodeId()+"is already exist for File Name "+ltMotor.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new LTMotorException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<LTMotor> retrieveLTMotorData(String fileName, String nodeId) throws LTMotorException {
		logger.info("Called retrieveLTMotorData function");
		LTMotor ltMotor =new LTMotor();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<LTMotor> ltMotorRepo = ltMotorRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<LTMotor> ltMotorList =new ArrayList<LTMotor>();
			if(ltMotorRepo.isPresent()) {			
				ltMotor = ltMotorRepo.get();
				ltMotorList.add(ltMotor);
				logger.info("LTMotor data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveLTMotorData function");
				return ltMotorList;
			}
			else {
				return ltMotorList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new LTMotorException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public LTMotor updateLTMotor(LTMotor ltMotor) throws LTMotorException {
		logger.info("Called updateLTMotor function");
		if (ltMotor != null && ltMotor.getFileName() != null && ltMotor.getMotorId() != null) {
			Optional<LTMotor> ltMotorRepo = ltMotorRepository.findByMotorId(ltMotor.getMotorId());
			
			if(ltMotorRepo.isPresent()) {
				ltMotor.setUpdatedDate(LocalDateTime.now());
				ltMotor.setUpdatedBy(userFullName.findByUserName(ltMotor.getUserName()));
				logger.info("Ended updateLTMotor function");
				return ltMotorRepository.save(ltMotor);
			}
			else {
				logger.error("Given LTMotor Id is invalid");
				throw new LTMotorException("Given LTMotor Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new LTMotorException("Invalid Inputs");
		}	
	}
	
	@Override
	public void deleteLTMotor(String nodeId) throws LTMotorException {
		logger.info("called LTMotor function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			ltMotorRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted LTMotor");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  LTMotorException("Invalid Inputs");
		}
	}	
}
