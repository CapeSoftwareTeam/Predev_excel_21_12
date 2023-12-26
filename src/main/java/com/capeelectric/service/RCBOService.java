/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.RCBOException;
import com.capeelectric.model.RCBO;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface RCBOService {

	public RCBO addRCBO(RCBO rcbo) throws RCBOException ;
	
	public List<RCBO> retrieveRCBOData(String fileName, String nodeId) throws RCBOException;
	
	public RCBO updateRCBO(RCBO rcbo) throws RCBOException ;
}
