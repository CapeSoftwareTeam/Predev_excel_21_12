/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.CableConnector;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface CableConnectorRepository extends CrudRepository<CableConnector, Integer>{
	
	Optional<CableConnector> findByFileNameAndCableConnectorId(String fileName, String cableConnectorId);
	
	Optional<CableConnector> findByCableId(Integer cableId);
}
