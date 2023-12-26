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
import com.capeelectric.exception.SwitchBoardException;
import com.capeelectric.model.SwitchBoard;
import com.capeelectric.service.SwitchBoardService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class SwitchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SwitchBoardController.class);

	@Autowired
	private SwitchBoardService switchBoardService;
	
	@PostMapping("/diagram/saveSwitchBoard")
	public ResponseEntity<String> addSwitchBoard(@RequestBody SwitchBoard switchBoard)
			throws SwitchBoardException {
		logger.info("called addSwitchBoard function File Name : {}", switchBoard.getFileName());
		switchBoardService.addSwitchBoard(switchBoard); 
		return new ResponseEntity<String>("Switch Board saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveSwitchBoard/{fileName}/{nodeId}")
	public ResponseEntity<List<SwitchBoard>> retrieveSwitchBoard(@PathVariable String fileName, @PathVariable String nodeId) 
			throws SwitchBoardException {
		logger.info("called retrieveSwitchBoard function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<SwitchBoard>>(switchBoardService.retrieveSwitchBoardData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateSwitchBoard")
	public ResponseEntity<String> updateSwitchBoard(@RequestBody SwitchBoard switchBoardDetails)
			throws SwitchBoardException {
		logger.info("called updateSwitchBoard function File Name : {}",switchBoardDetails.getFileName());
		switchBoardService.updateSwitchBoard(switchBoardDetails);
		return new ResponseEntity<String>("Switch Board updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteSwitchBoard/{nodeId}")
	public ResponseEntity<String> deleteSwitchBoard (@PathVariable String nodeId)
	throws SwitchBoardException{
		logger.info("called deleteSwitchBoard function }", nodeId);
		switchBoardService.deleteSwitchBoard(nodeId);
		return new ResponseEntity<String>("  SwitchBoard deleted Sucessfully",HttpStatus.CREATED);
	}
		

	
}
