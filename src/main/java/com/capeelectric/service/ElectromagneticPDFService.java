package com.capeelectric.service;

import com.capeelectric.exception.ElectromagneticCompatabilityException;

public interface ElectromagneticPDFService {

	public void printElectromagneticData(String userName, Integer emcId) throws ElectromagneticCompatabilityException;

}
