package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CableConnectorException;
import com.capeelectric.model.CableConnector;
import com.capeelectric.service.CableConnectorService;

@RestController
@RequestMapping("/api/v2")
public class CableConnectorController {
private static final Logger logger = LoggerFactory.getLogger(CableConnectorController.class);
	
	@Autowired
	private CableConnectorService cableConnectorService;
	
	@PostMapping("/diagram/saveCableConnector")
	public ResponseEntity<String> addCableConnector(@RequestBody CableConnector cableConnector)
			throws CableConnectorException {
		logger.info("called addCableConnector function File Name : {}", cableConnector.getFileName());
		cableConnectorService.addCableConnector(cableConnector);
		return new ResponseEntity<String>("CableConnector details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveCableConnector/{fileName}/{nodeId}")
	public ResponseEntity<List<CableConnector>> retrieveCableConnector(@PathVariable String fileName, @PathVariable String nodeId) 
			throws CableConnectorException {
		logger.info("called retrieveCableConnector function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<CableConnector>>(cableConnectorService.retrieveCableConnectorData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateCableConnector")
	public ResponseEntity<String> updateCableConnector(@RequestBody CableConnector cableConnector)
			throws CableConnectorException {
		logger.info("called updateCableConnector function File Name : {}", cableConnector.getFileName());
		cableConnectorService.updateCableConnector(cableConnector);
		return new ResponseEntity<String>("CableConnector details updated successfully", HttpStatus.CREATED);
	}
}
