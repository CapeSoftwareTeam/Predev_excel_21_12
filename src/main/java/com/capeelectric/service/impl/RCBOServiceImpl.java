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

import com.capeelectric.exception.RCBOException;
import com.capeelectric.model.RCBO;
import com.capeelectric.repository.RCBORepository;
import com.capeelectric.service.RCBOService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class RCBOServiceImpl implements RCBOService {
	private static final Logger logger = LoggerFactory.getLogger(RCBOServiceImpl.class);

	@Autowired
	private RCBORepository rcboRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public RCBO addRCBO(RCBO rcbo) throws RCBOException {
		logger.info("Called addRCBO function");
		if (rcbo != null && rcbo.getFileName() != null ) {
			Optional<RCBO> rcboRepo = rcboRepository.findByFileNameAndNodeId(rcbo.getFileName(),rcbo.getNodeId());
			
			if(!rcboRepo.isPresent()) {
				rcbo.setCreatedDate(LocalDateTime.now());
				rcbo.setUpdatedDate(LocalDateTime.now());
				rcbo.setCreatedBy(userFullName.findByUserName(rcbo.getUserName()));
				rcbo.setUpdatedBy(userFullName.findByUserName(rcbo.getUserName()));
				logger.info("Ended addRCBO function");
				return rcboRepository.save(rcbo);
			}
			else {
				logger.error("Node Id "+rcbo.getNodeId()+"is already exist for File Name"+rcbo.getFileName());
				throw new RCBOException("This RCBO details is already exist for File Name "+rcbo.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new RCBOException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<RCBO> retrieveRCBOData(String fileName, String nodeId) throws RCBOException {
		logger.info("Called retrieveRCBOData function");
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<RCBO> rcboRepo = rcboRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<RCBO> rcboList =new ArrayList<RCBO>();
			if(rcboRepo.isPresent()) {			
				RCBO rcbo = rcboRepo.get();
				rcboList.add(rcbo);
				logger.info("RCBO data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveRCBOData function");
				return rcboList;
			}
			else {
				return rcboList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new RCBOException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public RCBO updateRCBO(RCBO rcbo) throws RCBOException {
		logger.info("Called updateRCBO function");
		if (rcbo != null && rcbo.getFileName() != null && rcbo.getRcboId() != null) {
			Optional<RCBO> rcboRepo = rcboRepository.findByRcboId(rcbo.getRcboId());
			
			if(rcboRepo.isPresent()) {
				rcbo.setUpdatedDate(LocalDateTime.now());
				rcbo.setUpdatedBy(userFullName.findByUserName(rcbo.getUserName()));
				logger.info("Ended updateRCBO function");
				return rcboRepository.save(rcbo);
			}
			else {
				logger.error("Given RCBO Id is invalid");
				throw new RCBOException("Given RCBO Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new RCBOException("Invalid Inputs");
		}	
	}
}
