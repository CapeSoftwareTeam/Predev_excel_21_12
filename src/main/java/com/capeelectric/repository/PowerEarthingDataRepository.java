package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.PowerEarthingData;


/**
 * 
 * @author capeelectricsoftware
 *
 */
@Repository
public interface PowerEarthingDataRepository extends CrudRepository<PowerEarthingData, Integer> {

	Optional<PowerEarthingData> findByEmcId(Integer emcId);

	List<PowerEarthingData> findByUserNameAndEmcId(String userName, Integer emcId);

	

}
