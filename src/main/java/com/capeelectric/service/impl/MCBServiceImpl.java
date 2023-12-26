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

import com.capeelectric.exception.MCBException;
import com.capeelectric.exception.SwitchBoardException;
import com.capeelectric.model.MCB;
import com.capeelectric.repository.MCBRepository;
import com.capeelectric.service.MCBService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class MCBServiceImpl implements MCBService {
	private static final Logger logger = LoggerFactory.getLogger(MCBServiceImpl.class);

	@Autowired
	private MCBRepository mcbRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public MCB addMCB(MCB mcb) throws MCBException {
		logger.info("Called addMCB function");
		if (mcb != null && mcb.getFileName() != null ) {
			Optional<MCB> mcbRepo = mcbRepository.findByFileNameAndNodeId(mcb.getFileName(),mcb.getNodeId());
			
			if(!mcbRepo.isPresent()) {
				mcb.setCreatedDate(LocalDateTime.now());
				mcb.setUpdatedDate(LocalDateTime.now());
				mcb.setCreatedBy(userFullName.findByUserName(mcb.getUserName()));
				mcb.setUpdatedBy(userFullName.findByUserName(mcb.getUserName()));
				logger.info("Ended addMCB function");
				return mcbRepository.save(mcb);
			}
			else {
				logger.error("MCB Node Id "+mcb.getNodeId()+"is already exist for File Name"+mcb.getFileName());
				throw new MCBException("This MCB details is already exist for File Name "+mcb.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new MCBException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<MCB> retrieveMCBData(String fileName, String nodeId) throws MCBException {
		logger.info("Called retrieveMCBData function");
		MCB mcb =new MCB();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<MCB> mcbRepo = mcbRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<MCB> mcbList =new ArrayList<MCB>();
			if(mcbRepo.isPresent()) {			
				mcb = mcbRepo.get();
				mcbList.add(mcb);
				logger.info("MCB data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveMCBData function");
				return mcbList;
			}
			else {
				return mcbList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new MCBException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public MCB updateMCB(MCB mcb) throws MCBException {
		logger.info("Called updateMCB function");
		if (mcb != null && mcb.getFileName() != null && mcb.getMcbID() != null) {
			Optional<MCB> mcbRepo = mcbRepository.findByMcbID(mcb.getMcbID());
			
			if(mcbRepo.isPresent()) {
				mcb.setUpdatedDate(LocalDateTime.now());
				mcb.setUpdatedBy(userFullName.findByUserName(mcb.getUserName()));
				logger.info("Ended updateMCB function");
				return mcbRepository.save(mcb);
			}
			else {
				logger.error("Given MCB Id is invalid");
				throw new MCBException("Given MCB Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new MCBException("Invalid Inputs");
		}	
	}
	
	@Override
	public void deleteMCB(String nodeId) throws MCBException {
		logger.info("called SwitchBoard function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			mcbRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted MCB");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  MCBException("Invalid Inputs");
		}
	}	

}
