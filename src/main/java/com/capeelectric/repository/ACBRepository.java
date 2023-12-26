/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.ACB;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface ACBRepository extends CrudRepository<ACB, Integer> {

	public Optional<ACB> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<ACB> findByAcbID(Integer acbID);

	public Optional<ACB> deleteByNodeId(String nodeId);

	public Optional<ACB> findByNodeId(String nodeId);

}