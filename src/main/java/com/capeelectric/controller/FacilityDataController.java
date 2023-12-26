package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.model.FacilityData;
import com.capeelectric.service.FacilityDataService;

/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/v2")

public class FacilityDataController {
	private static final Logger logger = LoggerFactory.getLogger(FacilityDataController.class);

	@Autowired
	private FacilityDataService facilityDataService;

	@PostMapping("/emc/saveFacilityData")
	public ResponseEntity<String> addFacilityData(@RequestBody FacilityData facilityData)
			throws FacilityDataException {
		logger.debug("started saveFacilityData function userName: {},emcId : {}", facilityData.getUserName(),
				facilityData.getEmcId());
		facilityDataService.addFacilityData(facilityData);
		logger.debug("ended saveFacilityData function");
		return new ResponseEntity<String>("FacilityData  Details Saved Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/emc/retrieveFacilityData/{userName}/{emcId}")
	public ResponseEntity<List<FacilityData>> retrieveFacilityData(@PathVariable String userName,
			@PathVariable Integer emcId) throws FacilityDataException {
		logger.info("called retrieveFacilityData function UserName: {}, emcId : {}", userName, emcId);
		return new ResponseEntity<List<FacilityData>>(facilityDataService.retrieveFacilityData(userName, emcId),
				HttpStatus.OK);
	}

	@PutMapping("/emc/updateFacilityData")
	public ResponseEntity<String> updateFacilityData(@RequestBody FacilityData facilityData)
			throws FacilityDataException {
		logger.info("called updateBasicLpsDetails function UserName : {},getEmcId : {}", facilityData.getUserName(),
				facilityData.getEmcId());
		facilityDataService.updateFacilityData(facilityData);
		return new ResponseEntity<String>("FacilityData  Details Updated Successfully", HttpStatus.OK);
	}

}
