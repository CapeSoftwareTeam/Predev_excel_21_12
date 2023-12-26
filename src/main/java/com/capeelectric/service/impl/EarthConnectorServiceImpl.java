package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthConnectorException;
import com.capeelectric.model.EarthConnector;
import com.capeelectric.repository.EarthConnectorRepository;
import com.capeelectric.service.EarthConnectorService;

import com.capeelectric.util.UserFullName;
/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class EarthConnectorServiceImpl implements EarthConnectorService {

	private static final Logger logger = LoggerFactory.getLogger(CablesServiceImpl.class);

	@Autowired
	private EarthConnectorRepository earthConnectorRepository;

	@Autowired
	private UserFullName userFullName;

	@Transactional
	@Override
	public EarthConnector addEarthConnector(EarthConnector earthConnector) throws EarthConnectorException {
		logger.info("Called addEarthConnector function");
		if (earthConnector != null && earthConnector.getFileName() != null) {
			Optional<EarthConnector> earthConnectorRepo = earthConnectorRepository
					.findByFileNameAndEarthCableConnectorid(earthConnector.getFileName(), earthConnector.getNodeId());

			if (!earthConnectorRepo.isPresent()) {
				earthConnector.setCreatedDate(LocalDateTime.now());
				earthConnector.setUpdatedDate(LocalDateTime.now());
				earthConnector.setCreatedBy(userFullName.findByUserName(earthConnector.getUserName()));
				earthConnector.setUpdatedBy(userFullName.findByUserName(earthConnector.getUserName()));
				logger.info("Ended addEarthConnector function");
				return earthConnectorRepository.save(earthConnector);
			} else {
				logger.error("EarthConnector Node Id " + earthConnector.getNodeId() + "is already exist for File Name"
						+ earthConnector.getFileName());
				throw new EarthConnectorException(
						"This earthConnector details is already exist for File Name " + earthConnector.getFileName());
			}
		}

		else {
			logger.error("Invalid Inputs");
			throw new EarthConnectorException("Invalid Inputs");
		}
	}

	@Override
	public Optional<EarthConnector> retrieveEarthConnectorData(String fileName, String nodeId)
			throws EarthConnectorException {
		logger.info("Called retrieveEarthConnectorData function");

		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			return earthConnectorRepository.findByFileNameAndEarthCableConnectorid(fileName, nodeId);
		} else {
			logger.error("Invalid Inputs");
			throw new EarthConnectorException("Invalid Inputs");
		}
	}

	@Transactional
	@Override
	public EarthConnector updateEarthConnector(EarthConnector earthConnector) throws EarthConnectorException {
		logger.info("Called updateEarthConnector function");
		if (earthConnector != null && earthConnector.getFileName() != null
				&& earthConnector.getEarthconnectorid() != null) {
			Optional<EarthConnector> earthConnectorRepo = earthConnectorRepository
					.findByEarthconnectorid(earthConnector.getEarthconnectorid());

			if (earthConnectorRepo.isPresent()) {
				earthConnector.setUpdatedDate(LocalDateTime.now());
				earthConnector.setUpdatedBy(userFullName.findByUserName(earthConnector.getUserName()));
				logger.info("Ended updateEarthConnector function");
				return earthConnectorRepository.save(earthConnector);
			} else {
				logger.error("Given EarthConnector Id is invalid");
				throw new EarthConnectorException("Given EarthConnector Id is invalid");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new EarthConnectorException("Invalid Inputs");
		}
	}
	
	@Override
	public void deleteEarthConnector(String earthCableConnectorid) throws EarthConnectorException {
		logger.info("called deleteEarthConnector function");
		if (earthCableConnectorid != null && earthCableConnectorid != null && !earthCableConnectorid.isEmpty()) {
			earthConnectorRepository.deleteByEarthCableConnectorid(earthCableConnectorid);
			logger.debug("Sucessfully deletedEarthConnector");
		}
		else {
			logger.error("Invalid Inputs");
			throw new EarthConnectorException("Invalid Inputs");
		}
	}
	

}
		


