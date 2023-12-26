package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.service.FacilityDataPDFService;

@RestController
@RequestMapping("/api/v2")
public class FacilityDataPDFController {
	private static final Logger logger = LoggerFactory.getLogger(FacilityDataPDFController.class);

	@Autowired
	private FacilityDataPDFService facilityDataPDFService;

	@GetMapping("/emc/printFacilityDataDetails/{userName}/{emcId}")
	public ResponseEntity<String> printFacilityDataDetails(@PathVariable String userName, @PathVariable Integer emcId)
			throws FacilityDataException {
		logger.info("called printFacilityDataDetails function userName: {},emcId : {}", userName, emcId);
	//	facilityDataPDFService.printFacilityDataDetails(userName, emcId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
