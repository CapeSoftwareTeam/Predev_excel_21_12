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

import com.capeelectric.exception.CableConnectorException;
import com.capeelectric.model.CableConnector;
import com.capeelectric.repository.CableConnectorRepository;
import com.capeelectric.service.CableConnectorService;
import com.capeelectric.util.SLDFindNonRemovedObject;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class CableConnectorServiceImpl implements CableConnectorService {
	private static final Logger logger = LoggerFactory.getLogger(CableConnectorServiceImpl.class);

	@Autowired
	private CableConnectorRepository cableConnectorRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private SLDFindNonRemovedObject findNonRemovedObjects;
	
	@Transactional
	@Override
	public CableConnector addCableConnector(CableConnector cableConnector) throws CableConnectorException {
		logger.info("Called addCableConnector function");
		if (cableConnector != null && cableConnector.getFileName() != null ) {
			Optional<CableConnector> cableConnectorRepo = cableConnectorRepository.findByFileNameAndCableConnectorId(cableConnector.getFileName(),cableConnector.getCableConnectorId());
			
			if(!cableConnectorRepo.isPresent()) {
				cableConnector.setCreatedDate(LocalDateTime.now());
				cableConnector.setUpdatedDate(LocalDateTime.now());
				cableConnector.setCreatedBy(userFullName.findByUserName(cableConnector.getUserName()));
				cableConnector.setUpdatedBy(userFullName.findByUserName(cableConnector.getUserName()));
				logger.info("Ended addPAT function");
				return cableConnectorRepository.save(cableConnector);
			}
			else {
				logger.error("CableConnector Id "+cableConnector.getCableConnectorId()+"is already exist for File Name"+cableConnector.getFileName());
				throw new CableConnectorException("This cableConnector details is already exist for File Name "+cableConnector.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new CableConnectorException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<CableConnector> retrieveCableConnectorData(String fileName, String cableConnectorId) throws CableConnectorException {
		logger.info("Called retrieveCableConnectorData function");
		CableConnector cableConnector =new CableConnector();
		if (fileName != null && !fileName.isEmpty() && cableConnectorId != null && !cableConnectorId.isEmpty()) {
			Optional<CableConnector> cableConnectorRepo = cableConnectorRepository.findByFileNameAndCableConnectorId(fileName,cableConnectorId);
			List<CableConnector> cableConnectorList =new ArrayList<CableConnector>();					
			if(cableConnectorRepo.isPresent()) {
				cableConnectorRepo.get().setGeneralTestingCableConnector(findNonRemovedObjects.findNonRemovedCableConnectorData(cableConnectorRepo));
                logger.debug("Successfully done findNonRemovedCableConnectorData() call");	
				cableConnector = cableConnectorRepo.get();
				cableConnectorList.add(cableConnector);
				logger.info("CableConnector data available for fileName :"+fileName+" cableConnectorId :"+cableConnectorId);
				logger.info("Ended retrieveCableConnectorData function");
				return cableConnectorList;
			}
			else {
				return cableConnectorList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new CableConnectorException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public CableConnector updateCableConnector(CableConnector cableConnector) throws CableConnectorException {
		logger.info("Called updateCableConnector function");
		if (cableConnector != null && cableConnector.getFileName() != null && cableConnector.getCableId() != null) {
			Optional<CableConnector> cableConnectorRepo = cableConnectorRepository.findByCableId(cableConnector.getCableId());
			
			if(cableConnectorRepo.isPresent()) {
				cableConnector.setUpdatedDate(LocalDateTime.now());
				cableConnector.setUpdatedBy(userFullName.findByUserName(cableConnector.getUserName()));				
				logger.info("Ended updateCableConnector function");
                return cableConnectorRepository.save(cableConnector);
			}
			else {
				logger.error("Given CableConnector Id is invalid");
				throw new CableConnectorException("Given CableConnector Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new CableConnectorException("Invalid Inputs");
		}	
	}
}
