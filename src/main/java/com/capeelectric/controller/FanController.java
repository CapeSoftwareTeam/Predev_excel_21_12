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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.FanException;
import com.capeelectric.exception.MCCBException;
import com.capeelectric.model.Fan;
import com.capeelectric.service.FanService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class FanController {
private static final Logger logger = LoggerFactory.getLogger(FanController.class);
	
	@Autowired
	private FanService fanService;
	
	@PostMapping("/diagram/saveFan")
	public ResponseEntity<String> addFan(@RequestBody Fan fan)
			throws FanException {
		logger.info("called addFan function File Name : {}", fan.getFileName());
		fanService.addFan(fan); 
		return new ResponseEntity<String>("Fan details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveFan/{fileName}/{nodeId}")
	public ResponseEntity<List<Fan>> retrieveFan(@PathVariable String fileName, @PathVariable String nodeId) 
			throws FanException {
		logger.info("called retrieveFan function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<Fan>>(fanService.retrieveFanData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateFan")
	public ResponseEntity<String> updateFan(@RequestBody Fan fan)
			throws FanException {
		logger.info("called updateFan function File Name : {}", fan.getFileName());
		fanService.updateFan(fan);
		return new ResponseEntity<String>("Fan details updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteFan/{nodeId}")
	public ResponseEntity<String> deleteFan (@PathVariable String nodeId)
	throws FanException{
		logger.info("called deleteFan function }", nodeId);
		fanService.deleteFan(nodeId);
		return new ResponseEntity<String>("Fan deleted Sucessfully",HttpStatus.CREATED);
	}


}
