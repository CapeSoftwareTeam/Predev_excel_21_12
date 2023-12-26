/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EquipotentialBonding;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EquipBondRepository extends CrudRepository<EquipotentialBonding, Integer> {

	public Optional<EquipotentialBonding> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<EquipotentialBonding> findByEquipBondID(Integer equipBondID);

	public void deleteByNodeId(String nodeId);

	public Optional<EquipotentialBonding> findByNodeId(String nodeId);

}