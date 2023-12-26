package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.ProtectiveEarthConductors;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SLDProtectiveEarthConductorsRepo extends CrudRepository<ProtectiveEarthConductors, Integer> {

	public Optional<ProtectiveEarthConductors> findByFileNameAndNodeId(String fileName, String nodeId);

	public void deleteByEarthProductiveCableID(String earthProductiveCableID);

	public Optional<ProtectiveEarthConductors> findByEarthProductiveCableID(String earthProductiveCableID);

	public Optional<ProtectiveEarthConductors> findByEarthProductiveConductorID(Integer earthProductiveConductorID);

	public Optional<ProtectiveEarthConductors> findByFileNameAndEarthProductiveCableID(String fileName,
			String earthProductiveCableID);

	public Optional<ProtectiveEarthConductors> findByNodeId(String nodeId);

	public Optional<ProtectiveEarthConductors> findByEarthProductiveConductorID(String nodeId);

}
