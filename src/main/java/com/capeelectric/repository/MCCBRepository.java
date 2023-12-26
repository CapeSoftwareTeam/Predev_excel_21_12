/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.MCCB;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface MCCBRepository extends CrudRepository<MCCB, Integer> {

	public Optional<MCCB> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<MCCB> findByMccbID(Integer mccbID);

	public void deleteByNodeId(String nodeId);

	public Optional<MCCB> findByNodeId(String nodeId);

}