/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.RCBO;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface RCBORepository extends CrudRepository<RCBO, Integer>{
	
	Optional<RCBO> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<RCBO> findByRcboId(Integer rcboId);
}
