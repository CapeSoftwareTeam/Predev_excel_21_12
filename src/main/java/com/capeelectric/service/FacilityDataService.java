package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.model.FacilityData;

public interface FacilityDataService {

	public void addFacilityData(FacilityData facilityData) throws FacilityDataException;

	public List<FacilityData> retrieveFacilityData(String userName, Integer emcId) throws FacilityDataException;

	public void updateFacilityData(FacilityData facilityData) throws FacilityDataException;

}
