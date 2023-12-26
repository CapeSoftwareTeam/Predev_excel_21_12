/**
 * 
 */
package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capeelectric.exception.DiagramComponentException;
import com.capeelectric.exception.EquipBondException;
import com.capeelectric.model.DiagramComponent;
import com.capeelectric.model.DiagramSymbol;
import com.capeelectric.repository.DiagramRepository;
import com.capeelectric.repository.DiagramSymbolRepository;
import com.capeelectric.service.DiagramService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class DiagramServiceImpl implements DiagramService {
	private static final Logger logger = LoggerFactory.getLogger(DiagramServiceImpl.class);

	@Autowired
	private DiagramRepository diagramRepository;

	@Autowired
	private UserFullName userFullName;

	@Autowired
	private DiagramSymbolRepository diagramSymbolRepository;

	@Transactional
	@Override
	public DiagramComponent addDiagram(DiagramComponent diagramComponent) throws DiagramComponentException {
		logger.info("Called addDiagram function");
		if (diagramComponent != null && diagramComponent.getUserName() != null
				&& diagramComponent.getFileName() != null) {
			Optional<DiagramComponent> diagramComponentRepo = diagramRepository
					.findByUserNameAndFileName(diagramComponent.getUserName(), diagramComponent.getFileName());

			if (!diagramComponentRepo.isPresent()) {
				diagramComponent.setCreatedDate(LocalDateTime.now());
				diagramComponent.setUpdatedDate(LocalDateTime.now());
				diagramComponent.setCreatedBy(userFullName.findByUserName(diagramComponent.getUserName()));
				diagramComponent.setUpdatedBy(userFullName.findByUserName(diagramComponent.getUserName()));
				logger.info("Ended addDiagram function");
				return diagramRepository.save(diagramComponent);
			} else {
				logger.error("File Name " + diagramComponent.getFileName() + " is already exist");
				throw new DiagramComponentException(
						"File Name " + diagramComponent.getFileName() + " is already exist");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new DiagramComponentException("Invalid Inputs");
		}
	}

	@Override
	public DiagramComponent retrieveDiagramComponent(String userName, String fileName)
			throws DiagramComponentException {
		logger.info("Called retrieveDiagramComponent function");
		if (userName != null && !userName.isEmpty() && fileName != null && !fileName.isEmpty()) {
			Optional<DiagramComponent> diagramComponentRepo = diagramRepository.findByUserNameAndFileName(userName,
					fileName);
			if (diagramComponentRepo.isPresent()) {
				DiagramComponent diagramComponent = diagramComponentRepo.get();
				logger.info("Diagram available for username :" + userName + " fileName :" + fileName);
				logger.info("Ended retrieveDiagramComponent function");
				return diagramComponent;
			} else {
				logger.error("Diagram is not available");
				throw new DiagramComponentException("Diagram is not available");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new DiagramComponentException("Invalid Inputs");
		}
	}

	@Override
	public String retrieveFileName(String userName, String fileName) throws DiagramComponentException {
		logger.info("Called retrieveFileName function");
		if (userName != null && !userName.isEmpty() && fileName != null && !fileName.isEmpty()) {
			Optional<DiagramComponent> diagramComponentRepo = diagramRepository.findByUserNameAndFileName(userName,
					fileName);
			if (!diagramComponentRepo.isPresent()) {
				logger.info("Ended retrieveDiagramComponent function");
				return "This File Name can be used";
			} else {
				logger.error("File Name is already exist");
				throw new DiagramComponentException("File Name is already exist");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new DiagramComponentException("Invalid Inputs");
		}
	}

	@Override
	public List<DiagramComponent> retrieveAllDiagram(String userName) throws DiagramComponentException {
		logger.info("Called retrieveAllDiagram function");
		if (userName != null && !userName.isEmpty()) {
			List<DiagramComponent> diagramComponentRepo = diagramRepository.findByUserName(userName);

			if (diagramComponentRepo != null && !diagramComponentRepo.isEmpty()) {
				logger.info("Diagrams List available for username :" + userName);
				logger.info("Ended retrieveAllDiagram function");
				return diagramComponentRepo;
			} else {
				logger.error("Diagram is not available");
				throw new DiagramComponentException("Diagram is not available");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new DiagramComponentException("Invalid Inputs");
		}
	}

	@Transactional
	@Override
	public DiagramComponent updateDiagram(DiagramComponent diagramComponent) throws DiagramComponentException {
		logger.info("Called updateDiagram function");
		if (diagramComponent != null && diagramComponent.getUserName() != null
				&& diagramComponent.getDiagramId() != null && diagramComponent.getDiagramId() != 0) {
			Optional<DiagramComponent> diagramComponentRepo = diagramRepository
					.findById(diagramComponent.getDiagramId());

			if (diagramComponentRepo.isPresent()
					&& diagramComponentRepo.get().getDiagramId().equals(diagramComponent.getDiagramId())) {
				diagramComponent.setUpdatedDate(LocalDateTime.now());
				diagramComponent.setUpdatedBy(userFullName.findByUserName(diagramComponent.getUserName()));
				logger.info("Ended addDiagram function");
				return diagramRepository.save(diagramComponent);
			} else {
				logger.error("Given diagram id is not available");
				throw new DiagramComponentException("Given diagram id is not availablee");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new DiagramComponentException("Invalid Inputs");
		}
	}

	@Override
	public List<DiagramSymbol> retrieveAllSymbol() {
		return (List<DiagramSymbol>) diagramSymbolRepository.findAll();
	}

	@Override
	public void deleteDiagram(Integer Id) throws DiagramComponentException {
		logger.info("called diagram function");
		if (Id != null) {
			diagramRepository.deleteById(null);
			logger.debug("Sucessfully Deleted diagram function ");
		} else {
			logger.error("Invalid Inputs");
			throw new DiagramComponentException("Invalid Inputs");
		}
	}

	@Override
	public void deleteDiagram(String nodeId) throws DiagramComponentException {
		// TODO Auto-generated method stub

	}

}
