/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SwitchBoard;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SwitchBoardRepository extends CrudRepository<SwitchBoard, Integer> {

	public Optional<SwitchBoard> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<SwitchBoard> findBySwitchBoardId(Integer switchBoardId);

	public void deleteByNodeId(String nodeId);

	public Optional<SwitchBoard> findByNodeId(String nodeId);

}
