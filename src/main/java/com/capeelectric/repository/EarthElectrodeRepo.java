package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EarthElectrode;

/**
 * @author CAPE-SOFTWARE
 *
 */

public interface EarthElectrodeRepo extends CrudRepository<EarthElectrode, Integer> {

	public Optional<EarthElectrode> findByEarthconnectorid(Integer earthElectrodeId);

	public void deleteByNodeId(String nodeId);

	public Optional<EarthElectrode> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<EarthElectrode> findByNodeId(String nodeId);

}
