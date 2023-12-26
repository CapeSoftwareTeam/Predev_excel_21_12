/**
 * 
 */
package com.capeelectric.service;


import java.util.List;

import com.capeelectric.exception.MCCBException;
import com.capeelectric.model.MCCB;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface MCCBService {
	
	public MCCB addMCCB(MCCB mccb) throws MCCBException ;
	
	public List<MCCB> retrieveMCCBData(String fileName, String nodeId) throws MCCBException;
	
	public MCCB updateMCCB(MCCB mccb) throws MCCBException ;

	void deleteMCCB(String nodeId) throws MCCBException;


}
