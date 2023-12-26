package com.capeelectric.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CablesException;
import com.capeelectric.exception.EarthConnectorException;
import com.capeelectric.model.EarthConnector;
import com.capeelectric.service.EarthConnectorService;


@RestController
@RequestMapping("/api/v2")
public class EarthConnectorController {
	private static final Logger logger = LoggerFactory.getLogger(EarthConnectorController.class);

	@Autowired
	private EarthConnectorService earthConnectorService;

	@PostMapping("/diagram/addearthconnector")
	public ResponseEntity<String> addEarthConnector(@RequestBody EarthConnector earthConnector)
			throws CablesException, EarthConnectorException {
		logger.info("called addEarthConnector function File Name : {}", earthConnector.getFileName());
		earthConnectorService.addEarthConnector(earthConnector);
		return new ResponseEntity<String>("EarthConnector details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveearthconnector/{fileName}/{nodeId}")
	public ResponseEntity<Optional<EarthConnector>> retrieveEarthConnector(@PathVariable String fileName,
			@PathVariable String nodeId) throws CablesException, EarthConnectorException {
		logger.info("called retrieveEarthConnector function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<Optional<EarthConnector>>(
				earthConnectorService.retrieveEarthConnectorData(fileName, nodeId), HttpStatus.OK);
	}

	@PutMapping("/diagram/updateearthconnector")
	public ResponseEntity<String> updateEarthConnector(@RequestBody EarthConnector earthConnector)
			throws CablesException, EarthConnectorException {
		logger.info("called update earthConnector function File Name : {}", earthConnector.getFileName());
		earthConnectorService.updateEarthConnector(earthConnector);
		return new ResponseEntity<String>("EarthConnector details updated successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/diagram/{earthCableConnectorid}")
	public ResponseEntity<String> deleteEarthConnector(@PathVariable String earthCableConnectorid)
			throws EarthConnectorException {
		logger.info("deleteEarthConnector function started for EarthCableConnectorid:{}", earthCableConnectorid);
		earthConnectorService.deleteEarthConnector(earthCableConnectorid);
		return new ResponseEntity<String>("Earth connector deleted successfully", HttpStatus.OK);
	}
}
