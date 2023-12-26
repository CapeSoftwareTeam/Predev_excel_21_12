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

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.ElectromagneticCompatabilityRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.repository.PowerEarthingDataRepository;
import com.capeelectric.service.ClientDetailsPDFService;
import com.capeelectric.service.ElectromagneticPDFService;
import com.capeelectric.service.FacilityDataPDFService;
import com.capeelectric.service.PowerEarthingDataPDFService;
import com.capeelectric.service.PrintFinalPDFService;

@RestController
@RequestMapping("/api/v2")
public class ClientDetailsPDFController {
	private static final Logger logger = LoggerFactory.getLogger(ClientDetailsPDFController.class);

	@Autowired
	private ClientDetailsPDFService clientDetailsPDFService;

	@Autowired
	PowerEarthingDataRepository powerEarthingDataRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	ElectromagneticCompatabilityRepository electromagneticCompatabilityRepository;

	@Autowired
	private FacilityDataRepository facilityDataRepository;

	private ClientDetails clientDetails;

	@Autowired
	private FacilityDataPDFService facilityDataPDFService;

	@Autowired
	private PowerEarthingDataPDFService powerEarthingDataPDFService;

	@Autowired
	private ElectromagneticPDFService electromagneticPDFService;

	@Autowired
	private PrintFinalPDFService printFinalPDFService;

	@GetMapping("/emc/printClientDetails/{userName}/{emcId}/{clientName}")
	public ResponseEntity<String> printClientDetails(@PathVariable String userName, @PathVariable Integer emcId,@PathVariable String clientName)
			throws ClientDetailsException, FacilityDataException, PowerEarthingDataException,
			ElectromagneticCompatabilityException, Exception {
		logger.info("called printClientDetails function userName: {},emcId : {}", userName, emcId);
		// clientDetailsPDFService.printClientDetails(userName, emcId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
