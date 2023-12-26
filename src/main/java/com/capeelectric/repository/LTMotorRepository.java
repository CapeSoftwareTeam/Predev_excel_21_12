/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.LTMotor;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Repository
public interface LTMotorRepository extends CrudRepository<LTMotor, Integer> {

	public Optional<LTMotor> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<LTMotor> findByMotorId(Integer motorId);

	public void deleteByNodeId(String nodeId);

	public Optional<LTMotor> findByNodeId(String nodeId);

}