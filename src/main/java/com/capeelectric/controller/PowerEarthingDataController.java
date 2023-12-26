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

import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.service.PowerEarthingDataService;

/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/v2")
public class PowerEarthingDataController {

	
	private static final Logger logger = LoggerFactory.getLogger(PowerEarthingDataController.class);

	@Autowired
	private PowerEarthingDataService powerEarthingDataService;

	@PostMapping("/emc/savePowerEarthingData")
	public ResponseEntity<String> savePowerEarthingData(@RequestBody PowerEarthingData powerEarthingData)
			throws PowerEarthingDataException {
		logger.debug("started savePowerEarthingData function userName: {},emcId : {}", powerEarthingData.getUserName(),
				powerEarthingData.getEmcId());

		powerEarthingDataService.savePowerEarthingData(powerEarthingData);
		logger.debug("ended savePowerEarthingData function");

		return new ResponseEntity<String>("PowerEarthingData Successfully Saved", HttpStatus.OK);

	}
	@GetMapping("/emc/retrievePowerEarthingData/{userName}/{emcId}")
	public ResponseEntity<List<PowerEarthingData>> retrievePowerEarthingData(@PathVariable String userName,
			@PathVariable Integer emcId) throws PowerEarthingDataException {
		logger.info("called retrieveFacilityData function UserName: {}, emcId : {}", userName, emcId);
		return new ResponseEntity<List<PowerEarthingData>>(powerEarthingDataService.retrievePowerEarthingData(userName, emcId),
				HttpStatus.OK);
	}
	@PutMapping("/emc/updatePowerEarthingData")
	public ResponseEntity<String> updatePowerEarthingData(@RequestBody PowerEarthingData powerEarthingData)
			throws PowerEarthingDataException {
		logger.info("called updatePowerEarthingData function UserName : {},emcId : {}", powerEarthingData.getUserName(),
				powerEarthingData.getEmcId());
		powerEarthingDataService.updatePowerEarthingData(powerEarthingData);
		return new ResponseEntity<String>("PowerEarthingData Updated Successfully", HttpStatus.OK);
	}

}
