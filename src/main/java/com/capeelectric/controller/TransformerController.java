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
import com.capeelectric.exception.TransformerException;
import com.capeelectric.model.TransformerDetails;
import com.capeelectric.service.TransformerService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class TransformerController {
	private static final Logger logger = LoggerFactory.getLogger(TransformerController.class);

	@Autowired
	private TransformerService transformerService;
	
	@PostMapping("/diagram/saveTransformer")
	public ResponseEntity<String> addTransformer(@RequestBody TransformerDetails transformerDetails)
			throws TransformerException {
		logger.info("called addTransformer function File Name : {}", transformerDetails.getFileName());
		transformerService.addTransformer(transformerDetails); 
		return new ResponseEntity<String>("Transformer details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveTransformer/{fileName}/{nodeId}")
	public ResponseEntity<List<TransformerDetails>> retrieveTransformer(@PathVariable String fileName, @PathVariable String nodeId) 
			throws TransformerException {
		logger.info("called retrieveTransformer function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<TransformerDetails>>(transformerService.retrieveTransformerData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateTransformer")
	public ResponseEntity<String> updateTransformer(@RequestBody TransformerDetails transformerDetails)
			throws TransformerException {
		logger.info("called updateTransformer function File Name : {}", transformerDetails.getFileName());
		transformerService.updateTransformer(transformerDetails);
		return new ResponseEntity<String>("Transformer details updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteTransformer/{nodeId}")
	public ResponseEntity<String> deleteTransformer (@PathVariable String nodeId)
	throws TransformerException{
		logger.info("called deleteTransformer function }", nodeId);
		transformerService.deleteTransformer(nodeId);
		return new ResponseEntity<String>("Transformer deleted Sucessfully",HttpStatus.CREATED);
	}

	
}
