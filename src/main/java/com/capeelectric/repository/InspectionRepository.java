package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.PeriodicInspection;
/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface InspectionRepository extends CrudRepository<PeriodicInspection, Integer>{

	@Query(value="SELECT * FROM lv_safety_verification.periodic_inspection_table where SITE_ID =:siteId AND STATUS != 'R'",nativeQuery = true)
	public Optional<PeriodicInspection> findBySiteId(Integer siteId);

	public PeriodicInspection findByUserNameAndSiteId(String userName, Integer siteId);

}
