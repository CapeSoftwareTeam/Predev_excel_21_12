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

import com.capeelectric.exception.MCBException;
import com.capeelectric.exception.MCCBException;
import com.capeelectric.model.MCCB;
import com.capeelectric.service.MCCBService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class MCCBController {
private static final Logger logger = LoggerFactory.getLogger(MCCBController.class);
	
	@Autowired
	private MCCBService mccbService;
	
	@PostMapping("/diagram/saveMCCB")
	public ResponseEntity<String> addMCCB(@RequestBody MCCB mccb)
			throws MCCBException {
		logger.info("called addMCCB function File Name : {}", mccb.getFileName());
		mccbService.addMCCB(mccb); 
		return new ResponseEntity<String>("MCCB details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveMCCB/{fileName}/{nodeId}")
	public ResponseEntity<List<MCCB>> retrieveMCCB(@PathVariable String fileName, @PathVariable String nodeId) 
			throws MCCBException {
		logger.info("called retrieveMCCB function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<MCCB>>(mccbService.retrieveMCCBData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateMCCB")
	public ResponseEntity<String> updateMCCB(@RequestBody MCCB mccb)
			throws MCCBException {
		logger.info("called updateMCCB function File Name : {}", mccb.getFileName());
		mccbService.updateMCCB(mccb);
		return new ResponseEntity<String>("MCCB details updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteMCCB/{nodeId}")
	public ResponseEntity<String> deleteMCCB (@PathVariable String nodeId)
	throws MCCBException{
		logger.info("called deleteMCCBfunction }", nodeId);
		mccbService.deleteMCCB(nodeId);
		return new ResponseEntity<String>("MCCB deleted Sucessfully",HttpStatus.CREATED);
	}

}
