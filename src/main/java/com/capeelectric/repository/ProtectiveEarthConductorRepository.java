/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.ProtectiveEarthConductor;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface ProtectiveEarthConductorRepository extends CrudRepository<ProtectiveEarthConductor, Integer>{
	
	Optional<ProtectiveEarthConductor> findByFileNameAndCableConnectorId(String fileName, String nodeId);
	
	Optional<ProtectiveEarthConductor> findBySafetyTestingPECId(Integer safetyTestingPECId);

}