package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ProtectiveEarthConductorsException;
import com.capeelectric.model.ProtectiveEarthConductors;
import com.capeelectric.repository.SLDProtectiveEarthConductorsRepo;
import com.capeelectric.service.SLDProtectiveEarthConductorsService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */

@Service	
public class SLDProtectiveEarthConductorsServiceImpl implements SLDProtectiveEarthConductorsService {
	private static final Logger logger = LoggerFactory.getLogger(CablesServiceImpl.class);

	@Autowired
	private SLDProtectiveEarthConductorsRepo sLDProtectiveEarthConductorsRepo;

	@Autowired
	private UserFullName userFullName;

	@Transactional
	@Override
	public ProtectiveEarthConductors addProtectiveEarthConductors(ProtectiveEarthConductors protectiveEarthConductors)
			throws ProtectiveEarthConductorsException {
		logger.info("Called addProtectiveEarthConductors function");
		if (protectiveEarthConductors != null && protectiveEarthConductors.getFileName() != null) {
			Optional<ProtectiveEarthConductors> ProtectiveEarthConductorsrepo = sLDProtectiveEarthConductorsRepo
					.findByFileNameAndEarthProductiveCableID(protectiveEarthConductors.getFileName(),
							protectiveEarthConductors.getEarthProductiveCableID());
			if (!ProtectiveEarthConductorsrepo.isPresent()) {
				protectiveEarthConductors.setCreatedDate(LocalDateTime.now());
				protectiveEarthConductors.setUpdatedDate(LocalDateTime.now());
				protectiveEarthConductors
						.setCreatedBy(userFullName.findByUserName(protectiveEarthConductors.getUserName()));
				protectiveEarthConductors
						.setUpdatedBy(userFullName.findByUserName(protectiveEarthConductors.getUserName()));
				logger.info("Ended addprotectiveEarthConductors function");
				return sLDProtectiveEarthConductorsRepo.save(protectiveEarthConductors);
			} else {
				logger.error("protectiveEarthConductors Node Id " + protectiveEarthConductors.getNodeId()
						+ "is already exist for File Name" + protectiveEarthConductors.getFileName());
				throw new ProtectiveEarthConductorsException(
						"This protectiveEarthConductors details is already exist for File Name "
								+ protectiveEarthConductors.getFileName());
			}
		} else {
			logger.error("Invalid Inputs");
			throw new ProtectiveEarthConductorsException("Invalid Inputs");
		}
	}

	@Override
	public Optional<ProtectiveEarthConductors> retrieveProtectiveEarthConductors(String fileName,
			String earthProductiveCableID) throws ProtectiveEarthConductorsException {
		logger.info("Called retrieveEarthElectrode function");
		if (fileName != null && !fileName.isEmpty() && earthProductiveCableID != null
				&& !earthProductiveCableID.isEmpty()) {
			logger.info("Ended retrieveProtectiveEarthConductors function");
			return sLDProtectiveEarthConductorsRepo.findByFileNameAndEarthProductiveCableID(fileName, earthProductiveCableID);
		} else {
			logger.error("Invalid Inputs");
			throw new ProtectiveEarthConductorsException("Invalid Inputs");
		}
	}

	@Transactional
	@Override
	public ProtectiveEarthConductors updateProtectiveEarthConductors(
			ProtectiveEarthConductors protectiveEarthConductors) throws ProtectiveEarthConductorsException {
		logger.info("Called updateProtectiveEarthConductors function");
		if (protectiveEarthConductors != null && protectiveEarthConductors.getFileName() != null
				&& protectiveEarthConductors.getEarthProductiveConductorID() != null) {
			Optional<ProtectiveEarthConductors> protectiveEarthConductorsrepo = sLDProtectiveEarthConductorsRepo
					.findByEarthProductiveConductorID(protectiveEarthConductors.getEarthProductiveConductorID());

			if (protectiveEarthConductorsrepo.isPresent()) {
				protectiveEarthConductors.setUpdatedDate(LocalDateTime.now());
				protectiveEarthConductors
						.setUpdatedBy(userFullName.findByUserName(protectiveEarthConductors.getUserName()));
				logger.info("Ended updateProtectiveEarthConductors function");
				return sLDProtectiveEarthConductorsRepo.save(protectiveEarthConductors);
			} else {
				logger.error("Given ProtectiveEarthConductors Id is invalid");
				throw new ProtectiveEarthConductorsException("Given ProtectiveEarthConductors Id is invalid");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new ProtectiveEarthConductorsException("Invalid Inputs");
		}
	}

	@Override
	public void deleteProtectiveEarthConductors(String earthProductiveCableID)
			throws ProtectiveEarthConductorsException {
		logger.info("called ProtectiveEarthConductors function");
		if (earthProductiveCableID != null && !earthProductiveCableID.isEmpty()) {
			Optional<ProtectiveEarthConductors> earthProductiveCable = sLDProtectiveEarthConductorsRepo
					.findByEarthProductiveCableID(earthProductiveCableID);
			if (earthProductiveCable.isPresent()) {
				sLDProtectiveEarthConductorsRepo.deleteById(earthProductiveCable.get().getEarthProductiveConductorID());
				logger.debug("Sucessfully Deleted ProtectiveEarthConductors");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new ProtectiveEarthConductorsException("Invalid Inputs");
		}
	}

}
