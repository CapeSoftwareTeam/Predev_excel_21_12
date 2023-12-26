package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.DeleteDiagramShapesException;
import com.capeelectric.exception.MCCBException;
import com.capeelectric.service.DeleteDiagramShapesService;

@RestController
@RequestMapping("/api/v2/")
public class DeleteDiagramShapesController {
	private static final Logger logger = LoggerFactory.getLogger(DeleteDiagramShapesController.class);
	@Autowired
	private DeleteDiagramShapesService deleteDiagramShapesService;

	@PutMapping("/diagram/deleteDiagramShapes")
	public ResponseEntity<String> deleteDiagramShapes(@RequestBody String nodeId) throws DeleteDiagramShapesException {
		logger.info("called deleteDiagramShapes function");
		deleteDiagramShapesService.deleteDiagramShapes(nodeId);
		return new ResponseEntity<String>("Diagram Shapes deleted Sucessfully", HttpStatus.CREATED);
	}	

}
