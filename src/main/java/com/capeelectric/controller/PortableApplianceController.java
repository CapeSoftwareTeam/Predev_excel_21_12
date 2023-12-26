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

import com.capeelectric.exception.MCCBException;
import com.capeelectric.exception.PortableApplianceException;
import com.capeelectric.model.PortableAppliance;
import com.capeelectric.service.PortableApplianceService;

@RestController
@RequestMapping("/api/v2")
public class PortableApplianceController {
private static final Logger logger = LoggerFactory.getLogger(PortableApplianceController.class);
	
	@Autowired
	private PortableApplianceService portableApplianceService;
	
	@PostMapping("/diagram/savePAT")
	public ResponseEntity<String> addPAT(@RequestBody PortableAppliance portableAppliance)
			throws PortableApplianceException {
		logger.info("called addPAT function File Name : {}", portableAppliance.getFileName());
		portableApplianceService.addPAT(portableAppliance);
		return new ResponseEntity<String>("Portable Appliances details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrievePAT/{fileName}/{nodeId}")
	public ResponseEntity<List<PortableAppliance>> retrievePAT(@PathVariable String fileName, @PathVariable String nodeId) 
			throws PortableApplianceException {
		logger.info("called retrievePAT function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<PortableAppliance>>(portableApplianceService.retrievePATData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updatePAT")
	public ResponseEntity<String> updatePAT(@RequestBody PortableAppliance portableAppliance)
			throws PortableApplianceException {
		logger.info("called updatePAT function File Name : {}", portableAppliance.getFileName());
		portableApplianceService.updatePAT(portableAppliance);
		return new ResponseEntity<String>("Portable Appliances details updated successfully", HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/diagram/deletePAT/{nodeId}")
	public ResponseEntity<String> deletePAT (@PathVariable String nodeId)
	throws PortableApplianceException{
		logger.info("called deletePAT function }", nodeId);
		portableApplianceService.deletePAT(nodeId);
		return new ResponseEntity<String>("PAT deleted Sucessfully",HttpStatus.CREATED);
	}

}
