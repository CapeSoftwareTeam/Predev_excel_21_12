/**
 * 
 */
package com.capeelectric.service;


import java.util.List;

import com.capeelectric.exception.EquipBondException;
import com.capeelectric.model.EquipotentialBonding;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EquipBondService {
	
	public EquipotentialBonding addEquipBond(EquipotentialBonding equipBond) throws EquipBondException ;
	
	public List<EquipotentialBonding> retrieveEquipBondData(String fileName, String nodeId) throws EquipBondException;
	
	public EquipotentialBonding updateEquipBond(EquipotentialBonding equipBond) throws EquipBondException ;

	void deleteEquiBond(String nodeId) throws EquipBondException;


}
