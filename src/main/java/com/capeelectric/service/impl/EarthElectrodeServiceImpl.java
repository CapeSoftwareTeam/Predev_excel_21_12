package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthElectrodeException;
import com.capeelectric.model.EarthElectrode;
import com.capeelectric.repository.EarthElectrodeRepo;
import com.capeelectric.service.EarthElectrodeService;
import com.capeelectric.util.UserFullName;

@Service
public class EarthElectrodeServiceImpl implements EarthElectrodeService {
	private static final Logger logger = LoggerFactory.getLogger(EarthElectrodeServiceImpl.class);
	@Autowired
	private EarthElectrodeRepo earthElectrodeRepo;
	@Autowired
	private UserFullName userFullName;

	@Override
	public EarthElectrode addEarthElectrode(EarthElectrode earthElectrode) throws EarthElectrodeException {
		logger.info("Called addEarthElectrode function");
		if (earthElectrode != null && earthElectrode.getFileName() != null) {
			Optional<EarthElectrode> electrodeRepo = earthElectrodeRepo
					.findByFileNameAndNodeId(earthElectrode.getFileName(), earthElectrode.getNodeId());

			if (!electrodeRepo.isPresent()) {
				earthElectrode.setCreatedDate(LocalDateTime.now());
				earthElectrode.setUpdatedDate(LocalDateTime.now());
				earthElectrode.setCreatedBy(userFullName.findByUserName(earthElectrode.getUserName()));
				earthElectrode.setUpdatedBy(userFullName.findByUserName(earthElectrode.getUserName()));
				logger.info("Ended addEarthElectrode function");
				return earthElectrodeRepo.save(earthElectrode);
			} else {
				logger.error("Node Id " + earthElectrode.getNodeId() + "is already exist for File Name "
						+ earthElectrode.getFileName());
				throw new EarthElectrodeException(
						"This EarthElectrode details is already exist for File Name " + earthElectrode.getFileName());
			}
		} else {
			logger.error("Invalid Inputs");
			throw new EarthElectrodeException("Invalid Inputs");
		}
	}

	@Override
	public Optional<EarthElectrode> retrieveEarthElectrode(String fileName, String nodeId)
			throws EarthElectrodeException {
		logger.info("Called retrieveEarthElectrode function");
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			logger.info("Ended retrieveEarthElectrode function");
			return earthElectrodeRepo.findByFileNameAndNodeId(fileName, nodeId);
		} else {
			logger.error("Invalid Inputs");
			throw new EarthElectrodeException("Invalid Inputs");
		}
	}

	@Transactional
	@Override
	public void updateEarthElectrode(EarthElectrode earthElectrode) throws EarthElectrodeException {
		logger.info("Called updateEarthElectrode function");
		if (earthElectrode != null && earthElectrode.getFileName() != null
				&& earthElectrode.getEarthconnectorid() != null) {
			Optional<EarthElectrode> earthElectrodeRepository = earthElectrodeRepo
					.findByEarthconnectorid(earthElectrode.getEarthconnectorid());

			if (earthElectrodeRepository.isPresent()) {
				earthElectrode.setUpdatedDate(LocalDateTime.now());
				earthElectrode.setUpdatedBy(userFullName.findByUserName(earthElectrode.getUserName()));
				logger.info("Ended updateEarthElectrode function");
				earthElectrodeRepo.save(earthElectrode);
			} else {
				logger.error("Given EarthElectrode Id is invalid");
				throw new EarthElectrodeException("Given EarthElectrode Id is invalid");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new EarthElectrodeException("Invalid Inputs");
		}
	}

	@Override
	public void deleteEarthElectrode(String nodeId) throws EarthElectrodeException {
		logger.info("called delete EarthElectrode function");
		if (nodeId != null && !nodeId.isEmpty()) {
			earthElectrodeRepo.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted EarthElectrode function ");
		} else {
			logger.error("Invalid Inputs");
			throw new EarthElectrodeException("Invalid Inputs");
		}
	}

}
