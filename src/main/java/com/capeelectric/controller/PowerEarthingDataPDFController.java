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

import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.service.PowerEarthingDataPDFService;

@RestController
@RequestMapping("/api/v2")
public class PowerEarthingDataPDFController {
	private static final Logger logger = LoggerFactory.getLogger(PowerEarthingDataPDFController.class);

	@Autowired
	private PowerEarthingDataPDFService powerEarthingDataPDFService;

	@GetMapping("/emc/printPowerEarthingData/{userName}/{emcId}")
	public ResponseEntity<String> printPowerEarthingData(@PathVariable String userName, @PathVariable Integer emcId)
			throws PowerEarthingDataException {
		logger.info("called printPowerEarthingData function userName: {},emcId : {}", userName, emcId);
	//	powerEarthingDataPDFService.printPowerEarthingData(userName, emcId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
