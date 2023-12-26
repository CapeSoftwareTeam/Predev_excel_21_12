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

import com.capeelectric.exception.CablesException;
import com.capeelectric.exception.MCCBException;
import com.capeelectric.model.Cables;
import com.capeelectric.repository.CablesRepository;
import com.capeelectric.service.CablesService;
import com.capeelectric.util.SLDFindNonRemovedObject;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class CablesServiceImpl implements CablesService {
	private static final Logger logger = LoggerFactory.getLogger(CablesServiceImpl.class);

	@Autowired
	private CablesRepository cablesRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private SLDFindNonRemovedObject findNonRemovedObjects;
	
	@Transactional
	@Override
	public Cables addCables(Cables cables) throws CablesException {
		logger.info("Called addCables function");
		if (cables != null && cables.getFileName() != null ) {
			Optional<Cables> cablesRepo = cablesRepository.findByFileNameAndNodeId(cables.getFileName(),cables.getNodeId());
			
			if(!cablesRepo.isPresent()) {
				cables.setCreatedDate(LocalDateTime.now());
				cables.setUpdatedDate(LocalDateTime.now());
				cables.setCreatedBy(userFullName.findByUserName(cables.getUserName()));
				cables.setUpdatedBy(userFullName.findByUserName(cables.getUserName()));
				logger.info("Ended addCables function");
				return cablesRepository.save(cables);
			}
			else {
				logger.error("Cables Node Id "+cables.getNodeId()+"is already exist for File Name"+cables.getFileName());
				throw new CablesException("This cables details is already exist for File Name "+cables.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new CablesException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<Cables> retrieveCablesData(String fileName, String nodeId) throws CablesException {
		logger.info("Called retrieveCablesData function");
		Cables cables =new Cables();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<Cables> cablesRepo = cablesRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<Cables> cablesList =new ArrayList<Cables>();					
			if(cablesRepo.isPresent()) {
				cablesRepo.get().setGeneralTestingCables(findNonRemovedObjects.findNonRemovedCablesData(cablesRepo));
				logger.debug("Successfully done findNonRemovedCablesData() call");	
				cables = cablesRepo.get();
				cablesList.add(cables);
				logger.info("Cables data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveCablesData function");
				return cablesList;
			}
			else {
				return cablesList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new CablesException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public Cables updateCables(Cables cables) throws CablesException {
		logger.info("Called updateCables function");
		if (cables != null && cables.getFileName() != null && cables.getCableId() != null) {
			Optional<Cables> cablesRepo = cablesRepository.findByCableId(cables.getCableId());
			
			if(cablesRepo.isPresent()) {
				cables.setUpdatedDate(LocalDateTime.now());
				cables.setUpdatedBy(userFullName.findByUserName(cables.getUserName()));
				logger.info("Ended updateCables function");
				return cablesRepository.save(cables);
			}
			else {
				logger.error("Given Cables Id is invalid");
				throw new CablesException("Given Cables Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new CablesException("Invalid Inputs");
		}	
	}
	

	@Override
	public void deleteCables(String nodeId) throws CablesException {
		logger.info("called Cables function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			cablesRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted Cables");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  CablesException("Invalid Inputs");
		}
	}	
}
