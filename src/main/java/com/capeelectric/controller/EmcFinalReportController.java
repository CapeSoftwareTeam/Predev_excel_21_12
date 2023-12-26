package com.capeelectric.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.EmcFinalReportException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.EmcFinalReport;
import com.capeelectric.service.EMCFinalReportService;

/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/v2")
public class EmcFinalReportController {

	private static final Logger logger = LoggerFactory.getLogger(EmcFinalReportController.class);

	@Autowired
	EMCFinalReportService finalReportService;
	
	@GetMapping("/emc/retrieveEmcReport/{userName}/{emcId}")
	public ResponseEntity<Optional<EmcFinalReport>> retrieveEmcReports(@PathVariable String userName,
			@PathVariable Integer emcId) throws EmcFinalReportException {
		logger.info("FinalReportAPI_started retrieveFinalEmcReport function userName: {},emcId : {}", userName, emcId);

		return new ResponseEntity<Optional<EmcFinalReport>>(
				finalReportService.retrieveEmcReports(userName, emcId), HttpStatus.OK);

	}

	@GetMapping("/emc/retrieveListOfClientDetails/{userName}")
	public ResponseEntity<List<ClientDetails>> retrieveListOfClientDetails(@PathVariable String userName)
			throws EmcFinalReportException {
		logger.info("FinalReportAPI_started retrieveListOfClientDetails function userName: {},emcId : {}", userName);

		return new ResponseEntity<List<ClientDetails>>(finalReportService.retrieveListOfClientDetails(userName),
				HttpStatus.OK);

	}
	
	@GetMapping("/emc/retrieveAllClients")
	public ResponseEntity<List<ClientDetails>> retrieveAllCLientDetails() throws EmcFinalReportException{
		logger.info("FinalReportAPI_started retrieveAllCLientDetails");
			
		return new ResponseEntity<List<ClientDetails>>(finalReportService.retrieveAllCLientDetails(),
						HttpStatus.OK);
	}


}
