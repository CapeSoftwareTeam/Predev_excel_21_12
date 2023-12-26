/**
 * 
 */
package com.capeelectric.service;


import java.util.List;

import com.capeelectric.exception.ACBException;
import com.capeelectric.exception.EarthConnectorException;
import com.capeelectric.model.ACB;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface ACBService {
	
	public ACB addACB(ACB acb) throws ACBException ;
	
	public List<ACB> retrieveACBData(String fileName, String nodeId) throws ACBException;
	
	public ACB updateACB(ACB acb) throws ACBException ;
	
	public void deleteACB(String nodeId)throws ACBException ;


}
