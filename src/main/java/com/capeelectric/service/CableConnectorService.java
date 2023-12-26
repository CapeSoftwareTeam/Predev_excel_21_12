/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.CableConnectorException;
import com.capeelectric.model.CableConnector;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface CableConnectorService {
	
   public CableConnector addCableConnector(CableConnector cableConnector) throws CableConnectorException ;
	
	public List<CableConnector> retrieveCableConnectorData(String fileName, String cableConnectorId) throws CableConnectorException;
	
	public CableConnector updateCableConnector(CableConnector cableConnector) throws CableConnectorException ;
}
