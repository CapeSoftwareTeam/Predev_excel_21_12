package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;

public interface PowerEarthingDataService {

	public void savePowerEarthingData(PowerEarthingData powerEarthingData) throws PowerEarthingDataException;

	public List<PowerEarthingData> retrievePowerEarthingData(String userName, Integer emcId)
			throws PowerEarthingDataException;

	public void updatePowerEarthingData(PowerEarthingData powerEarthingData) throws PowerEarthingDataException;

}
