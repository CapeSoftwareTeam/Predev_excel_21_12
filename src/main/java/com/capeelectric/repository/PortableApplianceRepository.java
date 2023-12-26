/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.PortableAppliance;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface PortableApplianceRepository extends CrudRepository<PortableAppliance, Integer> {

	public Optional<PortableAppliance> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<PortableAppliance> findByPortableApplianceId(Integer portableApplianceId);

	public void deleteByNodeId(String nodeId);

	public Optional<PortableAppliance> findByNodeId(String nodeId);
}
