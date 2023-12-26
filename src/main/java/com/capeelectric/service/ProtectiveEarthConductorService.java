/**
 * 
 */
package com.capeelectric.service;


import java.util.List;

import com.capeelectric.exception.ProtectiveEarthConductorException;
import com.capeelectric.model.ProtectiveEarthConductor;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface ProtectiveEarthConductorService {
	
	public ProtectiveEarthConductor addPEC(ProtectiveEarthConductor equipBond) throws ProtectiveEarthConductorException ;
	
	public List<ProtectiveEarthConductor> retrievePECData(String fileName, String nodeId) throws ProtectiveEarthConductorException;
	
	public ProtectiveEarthConductor updatePEC(ProtectiveEarthConductor protectiveEarthConductor) throws ProtectiveEarthConductorException ;


}
