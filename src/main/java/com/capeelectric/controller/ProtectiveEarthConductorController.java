/**
 * 
 */
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

import com.capeelectric.exception.ProtectiveEarthConductorException;
import com.capeelectric.model.ProtectiveEarthConductor;
import com.capeelectric.service.ProtectiveEarthConductorService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class ProtectiveEarthConductorController {
private static final Logger logger = LoggerFactory.getLogger(ProtectiveEarthConductorController.class);
	
	@Autowired
	private ProtectiveEarthConductorService protectiveEarthConductorService;
	
	@PostMapping("/diagram/savePEC")
	public ResponseEntity<String> addPEC(@RequestBody ProtectiveEarthConductor protectiveEarthConductor)
			throws ProtectiveEarthConductorException {
		logger.info("called addPEC function File Name : {}", protectiveEarthConductor.getFileName());
		protectiveEarthConductorService.addPEC(protectiveEarthConductor); 
		return new ResponseEntity<String>("ProtectiveEarthConductor details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrievePEC/{fileName}/{nodeId}")
	public ResponseEntity<List<ProtectiveEarthConductor>> retrievePEC(@PathVariable String fileName, @PathVariable String nodeId) 
			throws ProtectiveEarthConductorException {
		logger.info("called retrievePEC function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<ProtectiveEarthConductor>>(protectiveEarthConductorService.retrievePECData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updatePEC")
	public ResponseEntity<String> updatePEC(@RequestBody ProtectiveEarthConductor protectiveEarthConductor)
			throws ProtectiveEarthConductorException {
		logger.info("called updatePEC function File Name : {}", protectiveEarthConductor.getFileName());
		protectiveEarthConductorService.updatePEC(protectiveEarthConductor);
		return new ResponseEntity<String>("ProtectiveEarthConductor details updated successfully", HttpStatus.CREATED);
	}

}
