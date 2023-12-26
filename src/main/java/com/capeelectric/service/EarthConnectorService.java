package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.EarthConnectorException;
import com.capeelectric.model.EarthConnector;
import com.capeelectric.model.ProtectiveEarthConductor;




public interface EarthConnectorService {
	public EarthConnector addEarthConnector(EarthConnector earthConnector) throws EarthConnectorException;

	public EarthConnector updateEarthConnector(EarthConnector earthConnector) throws EarthConnectorException;

	public Optional<EarthConnector> retrieveEarthConnectorData(String fileName, String nodeId)
			throws EarthConnectorException;

	public void deleteEarthConnector(String earthCableConnectorid) throws EarthConnectorException;

//	EarthConnector addProtectiveEarthConductor(EarthConnector earthConnector)
//			throws EarthConnectorException;
//
//	Optional<EarthConnector> retrieveProtectiveEarthConductor(String fileName, String nodeId);

}
