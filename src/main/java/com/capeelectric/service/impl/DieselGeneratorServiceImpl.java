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

import com.capeelectric.exception.DieselGeneratorException;
import com.capeelectric.exception.FanException;
import com.capeelectric.model.DieselGenerator;
import com.capeelectric.repository.DieselGeneratorRepository;
import com.capeelectric.service.DieselGeneratorService;
import com.capeelectric.util.SLDFindNonRemovedObject;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class DieselGeneratorServiceImpl implements DieselGeneratorService {
	private static final Logger logger = LoggerFactory.getLogger(DieselGeneratorServiceImpl.class);

	@Autowired
	private DieselGeneratorRepository dieselGeneratorRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public DieselGenerator addDieselGenerator(DieselGenerator dieselGenerator) throws DieselGeneratorException {
		logger.info("Called addDieselGenerator function");
		if (dieselGenerator != null && dieselGenerator.getFileName() != null ) {
			Optional<DieselGenerator> dieselGeneratorRepo = dieselGeneratorRepository.findByFileNameAndNodeId(dieselGenerator.getFileName(),dieselGenerator.getNodeId());
			
			if(!dieselGeneratorRepo.isPresent()) {
				dieselGenerator.setCreatedDate(LocalDateTime.now());
				dieselGenerator.setUpdatedDate(LocalDateTime.now());
				dieselGenerator.setCreatedBy(userFullName.findByUserName(dieselGenerator.getUserName()));
				dieselGenerator.setUpdatedBy(userFullName.findByUserName(dieselGenerator.getUserName()));
				logger.info("Ended addDieselGenerator function");
				return dieselGeneratorRepository.save(dieselGenerator);
			}
			else {
				logger.error("DieselGenerator Node Id "+dieselGenerator.getNodeId()+"is already exist for File Name"+dieselGenerator.getFileName());
				throw new DieselGeneratorException("This DieselGenerator details is already exist for File Name "+dieselGenerator.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new DieselGeneratorException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<DieselGenerator> retrieveDieselGeneratorData(String fileName, String nodeId) throws DieselGeneratorException {
		logger.info("Called retrieveDieselGeneratorData function");
		DieselGenerator dieselGenerator =new DieselGenerator();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<DieselGenerator> dieselGeneratorRepo = dieselGeneratorRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<DieselGenerator> dieselGeneratorList =new ArrayList<DieselGenerator>();					
			if(dieselGeneratorRepo.isPresent()) {
				dieselGenerator = dieselGeneratorRepo.get();
				dieselGeneratorList.add(dieselGenerator);
				logger.info("DieselGenerator data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveDgData function");
				return dieselGeneratorList;
			}
			else {
				return dieselGeneratorList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new DieselGeneratorException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public DieselGenerator updateDieselGenerator(DieselGenerator dieselGenerator) throws DieselGeneratorException {
		logger.info("Called updateCables function");
		if (dieselGenerator != null && dieselGenerator.getFileName() != null && dieselGenerator.getDieselGeneratorID() != null) {
			Optional<DieselGenerator> dieselGeneratorRepo = dieselGeneratorRepository.findByDieselGeneratorID(dieselGenerator.getDieselGeneratorID());
			
			if(dieselGeneratorRepo.isPresent()) {
				dieselGenerator.setUpdatedDate(LocalDateTime.now());
				dieselGenerator.setUpdatedBy(userFullName.findByUserName(dieselGenerator.getUserName()));
				logger.info("Ended updateDieselGenerator function");
				return dieselGeneratorRepository.save(dieselGenerator);
			}
			else {
				logger.error("Given DieselGenerator Id is invalid");
				throw new DieselGeneratorException("Given DieselGenerator Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new DieselGeneratorException("Invalid Inputs");
		}	
	}
	
	
	@Override
	public void deleteDieselGenerator(String nodeId) throws DieselGeneratorException {
		logger.info("called deleteDieselGenerator function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			dieselGeneratorRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted DieselGenerator  function ");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  DieselGeneratorException("Invalid Inputs");
		}
	}	
}
