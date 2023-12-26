/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.DieselGeneratorException;
import com.capeelectric.model.DieselGenerator;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DieselGeneratorService {
	
   public DieselGenerator addDieselGenerator(DieselGenerator dieselGenerator) throws DieselGeneratorException ;
	
	public List<DieselGenerator> retrieveDieselGeneratorData(String fileName, String nodeId) throws DieselGeneratorException;
	
	public DieselGenerator updateDieselGenerator(DieselGenerator dieselGenerator) throws DieselGeneratorException ;

	void deleteDieselGenerator(String nodeId) throws DieselGeneratorException;
}
