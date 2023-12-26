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

import com.capeelectric.exception.ACBException;
import com.capeelectric.model.ACB;
import com.capeelectric.service.ACBService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class ACBController {
private static final Logger logger = LoggerFactory.getLogger(ACBController.class);
	
	@Autowired
	private ACBService acbService;
	
	@PostMapping("/diagram/saveACB")
	public ResponseEntity<String> addACB(@RequestBody ACB acb)
			throws ACBException {
		logger.info("called addACB function File Name : {}", acb.getFileName());
		acbService.addACB(acb); 
		return new ResponseEntity<String>("ACB details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveACB/{fileName}/{nodeId}")
	public ResponseEntity<List<ACB>> retrieveACB(@PathVariable String fileName, @PathVariable String nodeId) 
			throws ACBException {
		logger.info("called retrieveACB function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<ACB>>(acbService.retrieveACBData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateACB")
	public ResponseEntity<String> updateACB(@RequestBody ACB acb)
			throws ACBException {
		logger.info("called updateACB function File Name : {}", acb.getFileName());
		acbService.updateACB(acb);
		return new ResponseEntity<String>("ACB details updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteACB/{nodeId}")
	public ResponseEntity<String> deleteACB (@PathVariable String nodeId)
	throws ACBException{
		logger.info("called deleteACB function }", nodeId);
		acbService.deleteACB(nodeId);
		return new ResponseEntity<String>("ACB deleted Sucessfully",HttpStatus.CREATED);
	}
		
	

}
