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

import com.capeelectric.exception.LTMotorException;
import com.capeelectric.exception.MCCBException;
import com.capeelectric.model.LTMotor;
import com.capeelectric.service.LTMotorService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class LTMotorController {
private static final Logger logger = LoggerFactory.getLogger(LTMotorController.class);
	
	@Autowired
	private LTMotorService ltMotorService;
	
	@PostMapping("/diagram/saveLTMotor")
	public ResponseEntity<String> addLTMotor(@RequestBody LTMotor ltMotor)
			throws LTMotorException {
		logger.info("called addLTMotor function File Name : {}", ltMotor.getFileName());
		ltMotorService.addLTMotor(ltMotor); 
		return new ResponseEntity<String>("LTMotor details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveLTMotor/{fileName}/{nodeId}")
	public ResponseEntity<List<LTMotor>> retrieveLTMotor(@PathVariable String fileName, @PathVariable String nodeId) 
			throws LTMotorException {
		logger.info("called retrieveLTMotor function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<LTMotor>>(ltMotorService.retrieveLTMotorData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateLTMotor")
	public ResponseEntity<String> updateLTMotor(@RequestBody LTMotor ltMotor)
			throws LTMotorException {
		logger.info("called updateLTMotor function File Name : {}", ltMotor.getFileName());
		ltMotorService.updateLTMotor(ltMotor);
		return new ResponseEntity<String>("LTMotor details updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteLTMotor/{nodeId}")
	public ResponseEntity<String> deleteMCCB (@PathVariable String nodeId)
	throws LTMotorException{
		logger.info("called deleteLTMotor function }", nodeId);
		ltMotorService.deleteLTMotor(nodeId);
		return new ResponseEntity<String>("LTMotor deleted Sucessfully",HttpStatus.CREATED);
	}


}
