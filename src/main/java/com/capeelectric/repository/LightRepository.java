/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.Light;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface LightRepository extends CrudRepository<Light, Integer> {

	public Optional<Light> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<Light> findByLightId(Integer lightId);

	public void deleteByNodeId(String nodeId);

	public Optional<Light> findByNodeId(String nodeId);

}
