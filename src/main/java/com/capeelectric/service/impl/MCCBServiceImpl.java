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
import com.capeelectric.exception.MCCBException;
import com.capeelectric.model.MCCB;
import com.capeelectric.repository.MCCBRepository;
import com.capeelectric.service.MCCBService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class MCCBServiceImpl implements MCCBService {
	private static final Logger logger = LoggerFactory.getLogger(MCCBServiceImpl.class);

	@Autowired
	private MCCBRepository mccbRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public MCCB addMCCB(MCCB mccb) throws MCCBException {
		logger.info("Called addMCCB function");
		if (mccb != null && mccb.getFileName() != null ) {
			Optional<MCCB> mccbRepo = mccbRepository.findByFileNameAndNodeId(mccb.getFileName(),mccb.getNodeId());
			
			if(!mccbRepo.isPresent()) {
				mccb.setCreatedDate(LocalDateTime.now());
				mccb.setUpdatedDate(LocalDateTime.now());
				mccb.setCreatedBy(userFullName.findByUserName(mccb.getUserName()));
				mccb.setUpdatedBy(userFullName.findByUserName(mccb.getUserName()));
				logger.info("Ended addMCCB function");
				return mccbRepository.save(mccb);
			}
			else {
				logger.error("Node Id "+mccb.getNodeId()+"is already exist for File Name "+mccb.getFileName());
				throw new MCCBException("This MCCB details is already exist for File Name "+mccb.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new MCCBException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<MCCB> retrieveMCCBData(String fileName, String nodeId) throws MCCBException {
		logger.info("Called retrieveMCCBData function");
		MCCB mccb =new MCCB();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<MCCB> mccbRepo = mccbRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<MCCB> mccbList =new ArrayList<MCCB>();
			if(mccbRepo.isPresent()) {			
				mccb = mccbRepo.get();
				mccbList.add(mccb);
				logger.info("MCCB data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveMCCBData function");
				return mccbList;
			}
			else {
				return mccbList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new MCCBException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public MCCB updateMCCB(MCCB mccb) throws MCCBException {
		logger.info("Called updateMCCB function");
		if (mccb != null && mccb.getFileName() != null && mccb.getMccbID() != null) {
			Optional<MCCB> mccbRepo = mccbRepository.findByMccbID(mccb.getMccbID());
			
			if(mccbRepo.isPresent()) {
				mccb.setUpdatedDate(LocalDateTime.now());
				mccb.setUpdatedBy(userFullName.findByUserName(mccb.getUserName()));
				logger.info("Ended updateMCCB function");
				return mccbRepository.save(mccb);
			}
			else {
				logger.error("Given MCCB Id is invalid");
				throw new MCCBException("Given MCCB Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new MCCBException("Invalid Inputs");
		}	
	}
	
	
	@Override
	public void deleteMCCB(String nodeId) throws MCCBException {
		logger.info("called MCCB function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			mccbRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted MCCB");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  MCCBException("Invalid Inputs");
		}
	}	
}
