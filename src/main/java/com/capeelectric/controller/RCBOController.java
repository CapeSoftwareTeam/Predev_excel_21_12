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

import com.capeelectric.exception.RCBOException;
import com.capeelectric.model.RCBO;
import com.capeelectric.service.RCBOService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class RCBOController {
private static final Logger logger = LoggerFactory.getLogger(RCBOController.class);
	
	@Autowired
	private RCBOService rcboService;
	
	@PostMapping("/diagram/saveRCBO")
	public ResponseEntity<String> addRCBO(@RequestBody RCBO rcbo)
			throws RCBOException {
		logger.info("called addRCBO function File Name : {}", rcbo.getFileName());
		rcboService.addRCBO(rcbo);
		return new ResponseEntity<String>("RCBO details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveRCBO/{fileName}/{nodeId}")
	public ResponseEntity<List<RCBO>> retrieveRCBO(@PathVariable String fileName, @PathVariable String nodeId) 
			throws RCBOException {
		logger.info("called retrieveRCBO function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<RCBO>>(rcboService.retrieveRCBOData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateRCBO")
	public ResponseEntity<String> updateRCBO(@RequestBody RCBO rcbo)
			throws RCBOException {
		logger.info("called updateRCBO function File Name : {}", rcbo.getFileName());
		rcboService.updateRCBO(rcbo);
		return new ResponseEntity<String>("RCBO details updated successfully", HttpStatus.CREATED);
	}
}
