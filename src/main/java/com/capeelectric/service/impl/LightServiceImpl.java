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

import com.capeelectric.exception.EarthConnectorException;
import com.capeelectric.exception.LightException;
import com.capeelectric.exception.SwitchBoardException;
import com.capeelectric.model.Light;
import com.capeelectric.repository.LightRepository;
import com.capeelectric.service.LightService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class LightServiceImpl implements LightService {
	private static final Logger logger = LoggerFactory.getLogger(LightServiceImpl.class);

	@Autowired
	private LightRepository lightRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public Light addLight(Light light) throws LightException {
		logger.info("Called addLight function");
		if (light != null && light.getFileName() != null ) {
			Optional<Light> lightRepo = lightRepository.findByFileNameAndNodeId(light.getFileName(),light.getNodeId());
			
			if(!lightRepo.isPresent()) {
				light.setCreatedDate(LocalDateTime.now());
				light.setUpdatedDate(LocalDateTime.now());
				light.setCreatedBy(userFullName.findByUserName(light.getUserName()));
				light.setUpdatedBy(userFullName.findByUserName(light.getUserName()));
				logger.info("Ended addLight function");
				return lightRepository.save(light);
			}
			else {
				logger.error("Node Id "+light.getNodeId()+"is already exist for File Name"+light.getFileName());
				throw new LightException("This Light details is already exist for File Name "+light.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new LightException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<Light> retrieveLightData(String fileName, String nodeId) throws LightException {
		logger.info("Called retrieveLightData function");
		Light light =new Light();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<Light> lightRepo = lightRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<Light> lightList =new ArrayList<Light>();
			if(lightRepo.isPresent()) {			
				light = lightRepo.get();
				lightList.add(light);
				logger.info("Light data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveLightData function");
				return lightList;
			}
			else {
				return lightList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new LightException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public Light updateLight(Light light) throws LightException {
		logger.info("Called updateLight function");
		if (light != null && light.getFileName() != null && light.getLightId() != null) {
			Optional<Light> lightRepo = lightRepository.findByLightId(light.getLightId());
			
			if(lightRepo.isPresent()) {
				light.setUpdatedDate(LocalDateTime.now());
				light.setUpdatedBy(userFullName.findByUserName(light.getUserName()));
				logger.info("Ended updateLight function");
				return lightRepository.save(light);
			}
			else {
				logger.error("Given Light Id is invalid");
				throw new LightException("Given Light Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new LightException("Invalid Inputs");
		}	
	}
	
	@Override
	public void deleteLight(String nodeId) throws LightException {
		logger.info("called Light function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			lightRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully deleted Light");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  LightException("Invalid Inputs");
		}
	}	
}
