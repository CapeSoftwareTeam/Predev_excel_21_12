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

import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.service.ElectromagneticPDFService;

@RestController
@RequestMapping("/api/v2")
public class ElectromagneticPDFController {
	
	private static final Logger logger = LoggerFactory.getLogger(ElectromagneticPDFController.class);

	@Autowired
	private ElectromagneticPDFService electromagneticPDFService;

	@GetMapping("/emc/printElectromagneticData/{userName}/{emcId}")
	public ResponseEntity<String> printElectromagneticData(@PathVariable String userName, @PathVariable Integer emcId)
			throws ElectromagneticCompatabilityException {
		logger.info("called printElectromagneticData function userName: {},emcId : {}", userName, emcId);
	//	electromagneticPDFService.printElectromagneticData(userName, emcId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
