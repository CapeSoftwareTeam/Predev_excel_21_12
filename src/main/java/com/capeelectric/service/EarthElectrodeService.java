package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.EarthElectrodeException;
import com.capeelectric.model.EarthElectrode;

public interface EarthElectrodeService {

	public EarthElectrode addEarthElectrode(EarthElectrode earthElectrode) throws EarthElectrodeException;

	public Optional<EarthElectrode> retrieveEarthElectrode(String fileName, String nodeId)
			throws EarthElectrodeException;

	public void updateEarthElectrode(EarthElectrode earthElectrode) throws EarthElectrodeException;

	public void deleteEarthElectrode(String nodeId) throws EarthElectrodeException;

}
