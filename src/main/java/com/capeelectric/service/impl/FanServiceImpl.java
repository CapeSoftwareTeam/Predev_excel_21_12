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

import com.capeelectric.exception.FanException;
import com.capeelectric.exception.PortableApplianceException;
import com.capeelectric.model.Fan;
import com.capeelectric.repository.FanRepository;
import com.capeelectric.service.FanService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class FanServiceImpl implements FanService {
	private static final Logger logger = LoggerFactory.getLogger(FanServiceImpl.class);

	@Autowired
	private FanRepository fanRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public Fan addFan(Fan fan) throws FanException {
		logger.info("Called addFan function");
		if (fan != null && fan.getFileName() != null ) {
            Optional<Fan> fanRepo = fanRepository.findByFileNameAndNodeId(fan.getFileName(),fan.getNodeId());
			
			if(!fanRepo.isPresent()) {
				fan.setCreatedDate(LocalDateTime.now());
				fan.setUpdatedDate(LocalDateTime.now());
				fan.setCreatedBy(userFullName.findByUserName(fan.getUserName()));
				fan.setUpdatedBy(userFullName.findByUserName(fan.getUserName()));
				
				logger.info("Ended addFan function");
				return fanRepository.save(fan);
			}
			else {
				logger.error("Node Id "+fan.getNodeId()+"is already exist for File Name "+fan.getFileName());
				throw new FanException("This Fan details is already exist for File Name "+fan.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new FanException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<Fan> retrieveFanData(String fileName, String nodeId) throws FanException {
		logger.info("Called retrieveFanData function");
		Fan fan =new Fan();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<Fan> fanRepo = fanRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<Fan> fanList =new ArrayList<Fan>();
			if(fanRepo.isPresent()) {			
				fan = fanRepo.get();
				fanList.add(fan);
				logger.info("Fan data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveFanData function");
				return fanList;
			}
			else {
				return fanList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new FanException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public Fan updateFan(Fan fan) throws FanException {
		logger.info("Called updateFan function");
		if (fan != null && fan.getFileName() != null && fan.getFanId() != null) {
			Optional<Fan> fanRepo = fanRepository.findByFanId(fan.getFanId());
			
			if(fanRepo.isPresent()) {
				fan.setUpdatedDate(LocalDateTime.now());
				fan.setUpdatedBy(userFullName.findByUserName(fan.getUserName()));
				logger.info("Ended updateFan function");
				return fanRepository.save(fan);
			}
			else {
				logger.error("Given Fan Id is invalid");
				throw new FanException("Given Fan Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new FanException("Invalid Inputs");
		}	
	}
	
	@Override
	public void deleteFan(String nodeId) throws FanException {
		logger.info("called deleteFan function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			fanRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted Fan function ");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  FanException("Invalid Inputs");
		}
	}	
}
