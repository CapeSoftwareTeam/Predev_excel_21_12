/**
 * 
 */
package com.capeelectric.service;


import java.util.List;

import com.capeelectric.exception.MCBException;
import com.capeelectric.model.MCB;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface MCBService {
	
	public MCB addMCB(MCB mcb) throws MCBException ;
	
	public List<MCB> retrieveMCBData(String fileName, String nodeId) throws MCBException;
	
	public MCB updateMCB(MCB mcb) throws MCBException ;

	void deleteMCB(String nodeId) throws MCBException;


}
