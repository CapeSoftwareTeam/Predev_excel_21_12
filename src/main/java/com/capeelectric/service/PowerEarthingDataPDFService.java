package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.PowerEarthingData;

public interface PowerEarthingDataPDFService {

	public void printPowerEarthingData(String userName, Integer emcId, Optional<PowerEarthingData> powerEarthingDataRep) throws PowerEarthingDataException;

}
