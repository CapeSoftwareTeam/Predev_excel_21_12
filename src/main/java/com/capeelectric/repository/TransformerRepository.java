/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.TransformerDetails;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface TransformerRepository extends CrudRepository<TransformerDetails, Integer> {

	public Optional<TransformerDetails> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<TransformerDetails> findByTransformerDetailsId(Integer transformerDetailsId);

	public void deleteByNodeId(String nodeId);

	public Optional<TransformerDetails> findByNodeId(String nodeId);

}
