/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.MCB;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface MCBRepository extends CrudRepository<MCB, Integer> {

	public Optional<MCB> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<MCB> findByMcbID(Integer mcbID);

	public void deleteByNodeId(String nodeId);

	public Optional<MCB> findByNodeId(String nodeId);

}
