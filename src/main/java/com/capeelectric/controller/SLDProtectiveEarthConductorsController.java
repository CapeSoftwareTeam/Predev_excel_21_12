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

import com.capeelectric.exception.ProtectiveEarthConductorsException;
import com.capeelectric.model.ProtectiveEarthConductors;
import com.capeelectric.service.SLDProtectiveEarthConductorsService;

@RestController
@RequestMapping("/api/v2")
public class SLDProtectiveEarthConductorsController {
	private static final Logger logger = LoggerFactory.getLogger(SLDProtectiveEarthConductorsController.class);

	@Autowired
	private SLDProtectiveEarthConductorsService sLDProtectiveEarthConductorsService;

	@PostMapping("/diagram/addprotectiveearthconductors")
	public ResponseEntity<String> addProtectiveEarthconductors(
			@RequestBody ProtectiveEarthConductors protectiveEarthConductors)
			throws ProtectiveEarthConductorsException {
		logger.info("called addProtectiveEarthconductors function File Name : {}",
				protectiveEarthConductors.getFileName());
		sLDProtectiveEarthConductorsService.addProtectiveEarthConductors(protectiveEarthConductors);
		return new ResponseEntity<String>("Protective EarthConductors details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveprotectiveearthconductors/{fileName}/{nodeId}")
	public ResponseEntity<Optional<ProtectiveEarthConductors>> retrieveProtectiveEarthConductors(
			@PathVariable String fileName, @PathVariable String nodeId) throws ProtectiveEarthConductorsException {
		logger.info("called retrieveProtectiveEarthConductors function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<Optional<ProtectiveEarthConductors>>(
				sLDProtectiveEarthConductorsService.retrieveProtectiveEarthConductors(fileName, nodeId), HttpStatus.OK);
	}

	@PutMapping("/diagram/updateprotectiveearthconductors")	
	public ResponseEntity<String> updateProtectiveEarthConductors(
			@RequestBody ProtectiveEarthConductors protectiveEarthConductors)
			throws ProtectiveEarthConductorsException {
		logger.info("called updateProtectiveEarthConductors File Name : {}", protectiveEarthConductors.getFileName());
		sLDProtectiveEarthConductorsService.updateProtectiveEarthConductors(protectiveEarthConductors);
		return new ResponseEntity<String>("ProtectiveEarthConductors details updated successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/diagram/deleteprotectiveearthconductors/{nodeId}")
	public ResponseEntity<String> deleteProtectiveEarthConductors(@PathVariable String nodeId)
			throws ProtectiveEarthConductorsException {
		logger.info("called deleteProtectiveEarthConductors function }", nodeId);
		sLDProtectiveEarthConductorsService.deleteProtectiveEarthConductors(nodeId);
		return new ResponseEntity<String>("ProtectiveEarthConductors deleted Sucessfully", HttpStatus.CREATED);
	}

}
