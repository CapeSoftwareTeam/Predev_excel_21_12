package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.FacilityData;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Repository
public interface FacilityDataRepository extends CrudRepository<FacilityData, Integer> {

	Optional<FacilityData> findByEmcId(Integer emcId);
	List<FacilityData> findByUserNameAndEmcId(String userName, Integer emcId);
	

}
