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

import com.capeelectric.exception.ACBException;
import com.capeelectric.exception.SwitchBoardException;
import com.capeelectric.model.SwitchBoard;
import com.capeelectric.repository.SwitchBoardRepository;
import com.capeelectric.service.SwitchBoardService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class SwitchBoardServiceImpl implements SwitchBoardService{
	private static final Logger logger = LoggerFactory.getLogger(SwitchBoardServiceImpl.class);

	@Autowired
	private SwitchBoardRepository switchBoardRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public SwitchBoard addSwitchBoard(SwitchBoard switchBoard) throws SwitchBoardException {
		logger.info("Called addSwitchBoard function");
		if (switchBoard != null && switchBoard.getFileName() != null ) {
			Optional<SwitchBoard> switchBoardRepo = switchBoardRepository.findByFileNameAndNodeId(switchBoard.getFileName(),switchBoard.getNodeId());
			
			if(!switchBoardRepo.isPresent()) {
				switchBoard.setCreatedDate(LocalDateTime.now());
				switchBoard.setUpdatedDate(LocalDateTime.now());
				switchBoard.setCreatedBy(userFullName.findByUserName(switchBoard.getUserName()));
				switchBoard.setUpdatedBy(userFullName.findByUserName(switchBoard.getUserName()));
				logger.info("Ended addSwitchBoard function");
				return switchBoardRepository.save(switchBoard);
			}
			else {
				logger.error("Node Id "+switchBoard.getNodeId()+"is already exist for File Name "+switchBoard.getFileName());
				throw new SwitchBoardException("This switchBoard details is already exist for File Name "+switchBoard.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new SwitchBoardException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<SwitchBoard> retrieveSwitchBoardData(String fileName, String nodeId) throws SwitchBoardException {
		logger.info("Called retrieveSwitchBoardData function");
		SwitchBoard switchBoard =new SwitchBoard();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<SwitchBoard> switchBoardRepo = switchBoardRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<SwitchBoard> switchBoardList =new ArrayList<SwitchBoard>();
			if(switchBoardRepo.isPresent()) {			
				switchBoard = switchBoardRepo.get();
				switchBoardList.add(switchBoard);
				logger.info("SwitchBoard data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveSwitchBoardData function");
				return switchBoardList;
			}
			else {
				return switchBoardList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new SwitchBoardException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public SwitchBoard updateSwitchBoard(SwitchBoard switchBoard) throws SwitchBoardException {
		logger.info("Called updateSwitchBoard function");
		if (switchBoard != null && switchBoard.getFileName() != null && switchBoard.getSwitchBoardId() != null) {
			Optional<SwitchBoard> switchBoardRepo = switchBoardRepository.findBySwitchBoardId(switchBoard.getSwitchBoardId());
			
			if(switchBoardRepo.isPresent()) {
				switchBoard.setUpdatedDate(LocalDateTime.now());
				switchBoard.setUpdatedBy(userFullName.findByUserName(switchBoard.getUserName()));
				logger.info("Ended updateSwitchBoard function");
				return switchBoardRepository.save(switchBoard);
			}
			else {
				logger.error("Given SwitchBoard Details Id is invalid");
				throw new SwitchBoardException("Given SwitchBoard Details Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new SwitchBoardException("Invalid Inputs");
		}	
	}
	
	@Override
	public void deleteSwitchBoard(String nodeId) throws SwitchBoardException {
		logger.info("called SwitchBoard function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			switchBoardRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully deleted ACB");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  SwitchBoardException("Invalid Inputs");
		}
	}	

}
