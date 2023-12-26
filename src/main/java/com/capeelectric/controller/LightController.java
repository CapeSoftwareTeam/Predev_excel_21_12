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
import com.capeelectric.exception.LightException;
import com.capeelectric.model.Light;
import com.capeelectric.service.LightService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class LightController {
private static final Logger logger = LoggerFactory.getLogger(LightController.class);
	
	@Autowired
	private LightService lightService;
	
	@PostMapping("/diagram/saveLight")
	public ResponseEntity<String> addLight(@RequestBody Light light)
			throws LightException {
		logger.info("called addLight function File Name : {}", light.getFileName());
		lightService.addLight(light);
		return new ResponseEntity<String>("Light details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveLight/{fileName}/{nodeId}")
	public ResponseEntity<List<Light>> retrieveLight(@PathVariable String fileName, @PathVariable String nodeId) 
			throws LightException {
		logger.info("called retrieveLight function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<Light>>(lightService.retrieveLightData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateLight")
	public ResponseEntity<String> updateLight(@RequestBody Light light)
			throws LightException {
		logger.info("called updateLight function File Name : {}", light.getFileName());
		lightService.updateLight(light);
		return new ResponseEntity<String>("Light details updated successfully", HttpStatus.CREATED);
	}
	

	@DeleteMapping("/diagram/deleteLight/{nodeId}")
	public ResponseEntity<String> deleteLight (@PathVariable String nodeId)
	throws LightException{
		logger.info("called deletelight function }", nodeId);
		lightService.deleteLight(nodeId);
		return new ResponseEntity<String>("light deleted Sucessfully",HttpStatus.CREATED);
	}

}
