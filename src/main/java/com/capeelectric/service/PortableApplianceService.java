/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.PortableApplianceException;
import com.capeelectric.model.PortableAppliance;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface PortableApplianceService {
	
	public PortableAppliance addPAT(PortableAppliance portableAppliance) throws PortableApplianceException ;
	
	public List<PortableAppliance> retrievePATData(String fileName, String nodeId) throws PortableApplianceException;
	
	public PortableAppliance updatePAT(PortableAppliance portableAppliance) throws PortableApplianceException ;

	void deletePAT(String nodeId) throws PortableApplianceException;
}
