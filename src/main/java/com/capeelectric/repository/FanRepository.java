/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.Fan;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface FanRepository extends CrudRepository<Fan, Integer> {

	public Optional<Fan> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<Fan> findByFanId(Integer fanId);

	public void deleteByNodeId(String nodeId);

	public Optional<Fan> findByNodeId(String nodeId);

}