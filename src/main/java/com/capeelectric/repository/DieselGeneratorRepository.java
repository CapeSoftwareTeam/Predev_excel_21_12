/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.DieselGenerator;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DieselGeneratorRepository extends CrudRepository<DieselGenerator, Integer> {

	public Optional<DieselGenerator> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<DieselGenerator> findByDieselGeneratorID(Integer dieselGeneratorId);

	public void deleteByNodeId(String nodeId);

	public Optional<DieselGenerator> findByNodeId(String nodeId);
}
