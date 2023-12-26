/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.Cables;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface CablesRepository extends CrudRepository<Cables, Integer> {

	public Optional<Cables> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<Cables> findByCableId(Integer cableId);

	public void deleteByNodeId(String nodeId);

	public Optional<Cables> findByNodeId(String nodeId);
}
