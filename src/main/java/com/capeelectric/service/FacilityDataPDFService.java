package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.model.FacilityData;

public interface FacilityDataPDFService {

	public void printFacilityDataDetails(String userName, Integer emcId, Optional<FacilityData> facilityDataRep) throws FacilityDataException;

}
