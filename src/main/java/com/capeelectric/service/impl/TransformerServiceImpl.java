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

import com.capeelectric.exception.MCCBException;
import com.capeelectric.exception.TransformerException;
import com.capeelectric.model.TransformerDetails;
import com.capeelectric.repository.TransformerRepository;
import com.capeelectric.service.TransformerService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class TransformerServiceImpl implements TransformerService{
	private static final Logger logger = LoggerFactory.getLogger(TransformerServiceImpl.class);

	@Autowired
	private TransformerRepository transformerRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public TransformerDetails addTransformer(TransformerDetails transformerDetails) throws TransformerException {
		logger.info("Called addTransformer function");
		if (transformerDetails != null && transformerDetails.getFileName() != null ) {
			Optional<TransformerDetails> transformerDetailsRepo = transformerRepository.findByFileNameAndNodeId(transformerDetails.getFileName(),transformerDetails.getNodeId());
			
			if(!transformerDetailsRepo.isPresent()) {
				transformerDetails.setCreatedDate(LocalDateTime.now());
				transformerDetails.setUpdatedDate(LocalDateTime.now());
				transformerDetails.setCreatedBy(userFullName.findByUserName(transformerDetails.getUserName()));
				transformerDetails.setUpdatedBy(userFullName.findByUserName(transformerDetails.getUserName()));
				logger.info("Ended addTransformer function");
				return transformerRepository.save(transformerDetails);
			}
			else {
				logger.error("Node Id "+transformerDetails.getNodeId()+"is already exist for File Name "+transformerDetails.getFileName());
				throw new TransformerException("This Transformer details is already exist for File Name "+transformerDetails.getFileName());
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new TransformerException("Invalid Inputs");
		}	
	}
	
	@Override
	public List<TransformerDetails> retrieveTransformerData(String fileName, String nodeId) throws TransformerException {
		logger.info("Called retrieveTransformerData function");
		TransformerDetails transformerDetails =new TransformerDetails();
		if (fileName != null && !fileName.isEmpty() && nodeId != null && !nodeId.isEmpty()) {
			Optional<TransformerDetails> transformerDetailsRepo = transformerRepository.findByFileNameAndNodeId(fileName,nodeId);
			List<TransformerDetails> transformerDetailsList =new ArrayList<TransformerDetails>();
			if(transformerDetailsRepo.isPresent()) {			
				transformerDetails = transformerDetailsRepo.get();
				transformerDetailsList.add(transformerDetails);
				logger.info("Transformer data available for fileName :"+fileName+" nodeId :"+nodeId);
				logger.info("Ended retrieveTransformerData function");
				return transformerDetailsList;
			}
			else {
				return transformerDetailsList;
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new TransformerException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public TransformerDetails updateTransformer(TransformerDetails transformerDetails) throws TransformerException {
		logger.info("Called updateTransformer function");
		if (transformerDetails != null && transformerDetails.getFileName() != null && transformerDetails.getTransformerDetailsId() != null) {
			Optional<TransformerDetails> transformerDetailsRepo = transformerRepository.findByTransformerDetailsId(transformerDetails.getTransformerDetailsId());
			
			if(transformerDetailsRepo.isPresent()) {
				transformerDetails.setUpdatedDate(LocalDateTime.now());
				transformerDetails.setUpdatedBy(userFullName.findByUserName(transformerDetails.getUserName()));
				logger.info("Ended updateMCCB function");
				return transformerRepository.save(transformerDetails);
			}
			else {
				logger.error("Given Transformer Details Id is invalid");
				throw new TransformerException("Given Transformer Details Id is invalid");
			}						
		}
		else {
			logger.error("Invalid Inputs");
			throw new TransformerException("Invalid Inputs");
		}	
	}
	
	@Override
	public void deleteTransformer(String nodeId) throws TransformerException {
		logger.info("called Transformer function");    
		if (nodeId != null && !nodeId.isEmpty()) {
			transformerRepository.deleteByNodeId(nodeId);
			logger.debug("Sucessfully Deleted MCCB");
		}
		else {
			logger.error("Invalid Inputs");
			throw new  TransformerException("Invalid Inputs");
		}
	}	
	

}
