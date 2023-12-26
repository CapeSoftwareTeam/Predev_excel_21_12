package com.capeelectric.controller;

import java.util.Optional;

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
import com.capeelectric.exception.EarthElectrodeException;
import com.capeelectric.model.EarthElectrode;
import com.capeelectric.service.EarthElectrodeService;

@RestController
@RequestMapping("/api/v2")
public class EarthElectrodeController {
	private static final Logger logger = LoggerFactory.getLogger(EarthElectrodeController.class);

	@Autowired
	private EarthElectrodeService earthElectrodeService;

	@PostMapping("/diagram/addearthelectrode")
	public ResponseEntity<String> addEarthElectrode(@RequestBody EarthElectrode earthElectrode)
			throws EarthElectrodeException {	
		logger.info("called addEarthElectore function File Name : {}", earthElectrode.getFileName());
		earthElectrodeService.addEarthElectrode(earthElectrode);
		return new ResponseEntity<String>("EarthElectrode details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveearthelectrode/{fileName}/{nodeId}")
	public ResponseEntity<Optional<EarthElectrode>> retrieveEarthElectrode(@PathVariable String fileName,
			@PathVariable String nodeId) throws EarthElectrodeException {
		logger.info("called retrieveEarthElectrode function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<Optional<EarthElectrode>>(
				earthElectrodeService.retrieveEarthElectrode(fileName, nodeId), HttpStatus.OK);
	}

	@PutMapping("/diagram/updateearthelectrode")
	public ResponseEntity<String> updateEarthElectrode(@RequestBody EarthElectrode earthElectrode)
			throws EarthElectrodeException {
		logger.info("called update earth electrode function File Name : {}", earthElectrode.getFileName());
		earthElectrodeService.updateEarthElectrode(earthElectrode);
		return new ResponseEntity<String>("EarthElectrode details updated successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/diagram/deleteEarthElectrode/{nodeId}")
	public ResponseEntity<String> deleteEarthElectrode(@PathVariable String nodeId) throws EarthElectrodeException {
		logger.info("called deleteEarthElectrode function }", nodeId);
		earthElectrodeService.deleteEarthElectrode(nodeId);
		return new ResponseEntity<String>("EarthElectrode deleted Sucessfully", HttpStatus.CREATED);
	}

}
