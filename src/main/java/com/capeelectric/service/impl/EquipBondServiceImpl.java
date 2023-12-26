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
import com.capeelectric.exception.EquipBondException;
import com.capeelectric.model.EquipotentialBonding;
import com.capeelectric.repository.EquipBondRepository;
import com.capeelectric.service.EquipBondService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class EquipBondServiceImpl implements EquipBondService {
	private static final Logger logger = LoggerFactory.getLogger(EquipBondServiceImpl.class);

	@Autowired
	private EquipBondRepository equipBondRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public EquipotentialBonding addEquipBond(EquipotentialBonding equipBond) throws EquipBondException {
		logger.info("Called addEquipBond function");
		if (equipBond != null && equipBond.getFileName() != null ) {
			Optional<EquipotentialBonding> equipBondRepo = equipBondRepository.findByFileNameAndNodeId(equipBond.getFileName(),equipBond.getNodeId());
			
			if(!equipBondRepo.isPresent()) {
				equipBond.setCreatedDate(LocalDateTime.now());
				equipBond.setUpdatedDate(LocalDateTime.now());
				equipBond.setCreatedBy(userFullName.findByUserName(equipBond.getUserName()));
				equipBond.setUpdatedBy(userFullName.findByUserName(equipBond.getUserName()));
				logger.info("Ended addEquipBond function");
				return equipBondRepository.save(equipBond);
			}
			else {
				logger.error("Node Id "+equipBond.getNodeId()+"is already exist for File Name "+equipBond.getFileName());
				throw new EquipBondException("This EquipBond details is already exist for File Name "+equipBond.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new EquipBondException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<EquipotentialBonding> retrieveEquipBondData(String fileName, String nodeId) throws EquipBondException {
		logger.info("Called retrieveEquipBondData function");
		EquipotentialBonding equipBond =new EquipotentialBonding();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<EquipotentialBonding> equipBondRepo = equipBondRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<EquipotentialBonding> equipBondList =new ArrayList<EquipotentialBonding>();
			if(equipBondRepo.isPresent()) {			
				equipBond = equipBondRepo.get();
				equipBondList.add(equipBond);
				logger.info("EquipBond data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveEquipBondData function");
				return equipBondList;
			}
			else {
				return equipBondList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new EquipBondException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public EquipotentialBonding updateEquipBond(EquipotentialBonding equipBond) throws EquipBondException {
		logger.info("Called updateEquipBond function");
		if (equipBond != null && equipBond.getFileName() != null && equipBond.getEquipBondID() != null) {
			Optional<EquipotentialBonding> equipBondRepo = equipBondRepository.findByEquipBondID(equipBond.getEquipBondID());
			
			if(equipBondRepo.isPresent()) {
				equipBond.setUpdatedDate(LocalDateTime.now());
				equipBond.setUpdatedBy(userFullName.findByUserName(equipBond.getUserName()));
				logger.info("Ended updateEquipBond function");
				return equipBondRepository.save(equipBond);
			}
			else {
				logger.error("Given EquipBond Id is invalid");
				throw new EquipBondException("Given EquipBond Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new EquipBondException("Invalid Inputs");
		}	
	}
	
	@Override
	public void deleteEquiBond(String nodeId) throws EquipBondException {
		logger.info("called deleteEquipotential_Bonding function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			equipBondRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted EquipBond function ");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  EquipBondException("Invalid Inputs");
		}
	}	

}
