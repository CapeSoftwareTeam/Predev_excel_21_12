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

import com.capeelectric.exception.CablesException;
import com.capeelectric.exception.MCCBException;
import com.capeelectric.model.Cables;
import com.capeelectric.service.CablesService;

@RestController
@RequestMapping("/api/v2")
public class CableController {
private static final Logger logger = LoggerFactory.getLogger(CableController.class);
	
	@Autowired
	private CablesService cablesService;
	
	@PostMapping("/diagram/saveCables")
	public ResponseEntity<String> addCables(@RequestBody Cables cables)
			throws CablesException {
		logger.info("called addCables function File Name : {}", cables.getFileName());
		cablesService.addCables(cables);
		return new ResponseEntity<String>("Cables details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveCables/{fileName}/{nodeId}")
	public ResponseEntity<List<Cables>> retrieveCables(@PathVariable String fileName, @PathVariable String nodeId) 
			throws CablesException {
		logger.info("called retrieveCables function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<Cables>>(cablesService.retrieveCablesData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateCables")
	public ResponseEntity<String> updateCables(@RequestBody Cables cables)
			throws CablesException {
		logger.info("called updateCables function File Name : {}", cables.getFileName());
		cablesService.updateCables(cables);
		return new ResponseEntity<String>("cables details updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteCables/{nodeId}")
	public ResponseEntity<String> deleteCables (@PathVariable String nodeId)
	throws CablesException{
		logger.info("called deleteCable function }", nodeId);
		cablesService.deleteCables(nodeId);
		return new ResponseEntity<String>("cables deleted Sucessfully",HttpStatus.CREATED);
		}

	
	
	
}
