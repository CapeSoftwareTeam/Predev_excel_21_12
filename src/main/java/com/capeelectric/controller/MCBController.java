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

import com.capeelectric.exception.LightException;
import com.capeelectric.exception.MCBException;
import com.capeelectric.model.MCB;
import com.capeelectric.service.MCBService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class MCBController {
private static final Logger logger = LoggerFactory.getLogger(MCBController.class);
	
	@Autowired
	private MCBService mcbService;
	
	@PostMapping("/diagram/saveMCB")
	public ResponseEntity<String> addMCB(@RequestBody MCB mcb)
			throws MCBException {
		logger.info("called addMCB function File Name : {}", mcb.getFileName());
		mcbService.addMCB(mcb);
		return new ResponseEntity<String>("MCB details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveMCB/{fileName}/{nodeId}")
	public ResponseEntity<List<MCB>> retrieveMCB(@PathVariable String fileName, @PathVariable String nodeId) 
			throws MCBException {
		logger.info("called retrieveMCB function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<MCB>>(mcbService.retrieveMCBData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateMCB")
	public ResponseEntity<String> updateMCB(@RequestBody MCB mcb)
			throws MCBException {
		logger.info("called updateMCB function File Name : {}", mcb.getFileName());
		mcbService.updateMCB(mcb);
		return new ResponseEntity<String>("MCB details updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteMCB/{nodeId}")
	public ResponseEntity<String> deleteMCB (@PathVariable String nodeId)
	throws MCBException{
		logger.info("called deleteMCBfunction }", nodeId);
		mcbService.deleteMCB(nodeId);
		return new ResponseEntity<String>("MCB deleted Sucessfully",HttpStatus.CREATED);
	}

}
