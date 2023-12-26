/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.CablesException;
import com.capeelectric.model.Cables;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface CablesService {
	
   public Cables addCables(Cables cables) throws CablesException ;
	
	public List<Cables> retrieveCablesData(String fileName, String nodeId) throws CablesException;
	
	public Cables updateCables(Cables cables) throws CablesException ;

	void deleteCables(String nodeId) throws CablesException;
}
