package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.ElectromagneticCompatability;


/**
 * 
 * @author capeelectricsoftware
 *
 */
@Repository
public interface ElectromagneticCompatabilityRepository  extends CrudRepository<ElectromagneticCompatability, Integer> {

	Optional<ElectromagneticCompatability> findByEmcId(Integer emcId);

	List<ElectromagneticCompatability> findByUserNameAndEmcId(String userName, Integer emcId);

}
