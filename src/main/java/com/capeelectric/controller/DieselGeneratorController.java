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

import com.capeelectric.exception.DieselGeneratorException;
import com.capeelectric.exception.MCCBException;
import com.capeelectric.model.DieselGenerator;
import com.capeelectric.service.DieselGeneratorService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class DieselGeneratorController {
private static final Logger logger = LoggerFactory.getLogger(DieselGeneratorController.class);
	
	@Autowired
	private DieselGeneratorService dieselGeneratorService;
	
	@PostMapping("/diagram/saveDieselGenerator")
	public ResponseEntity<String> addDieselGenerator(@RequestBody DieselGenerator dieselGenerator)
			throws DieselGeneratorException {
		logger.info("called addDieselGenerator function File Name : {}", dieselGenerator.getFileName());
		dieselGeneratorService.addDieselGenerator(dieselGenerator); 
		return new ResponseEntity<String>("DieselGenerator details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveDieselGenerator/{fileName}/{nodeId}")
	public ResponseEntity<List<DieselGenerator>> retrieveDieselGenerator(@PathVariable String fileName, @PathVariable String nodeId) 
			throws DieselGeneratorException {
		logger.info("called retrieveDieselGenerator function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<DieselGenerator>>(dieselGeneratorService.retrieveDieselGeneratorData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateDieselGenerator")
	public ResponseEntity<String> updateDieselGenerator(@RequestBody DieselGenerator dieselGenerator)
			throws DieselGeneratorException {
		logger.info("called updateDieselGenerator function File Name : {}", dieselGenerator.getFileName());
		dieselGeneratorService.updateDieselGenerator(dieselGenerator);
		return new ResponseEntity<String>("DieselGenerator details updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteDieselGenerator/{nodeId}")
	public ResponseEntity<String> deleteDieselGenerator (@PathVariable String nodeId)
	throws DieselGeneratorException{
		logger.info("called deleteDieselGenerator function }", nodeId);
		dieselGeneratorService.deleteDieselGenerator(nodeId);
		return new ResponseEntity<String>("DieselGenerator deleted Sucessfully",HttpStatus.CREATED);
	}


}
