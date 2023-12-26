package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.ElectromagneticCompatability;

public interface ElectromagneticCompatabilityService {

	public void saveElectromagneticCompatability(ElectromagneticCompatability electromagneticCompatability)
			throws ElectromagneticCompatabilityException, ClientDetailsException, FacilityDataException,
			PowerEarthingDataException, Exception;

	public List<ElectromagneticCompatability> retrieveElectromagneticCompatability(String userName, Integer emcId)
			throws ElectromagneticCompatabilityException;

	public void updateElectromagneticCompatability(ElectromagneticCompatability electromagneticCompatability)
			throws ElectromagneticCompatabilityException;

}
