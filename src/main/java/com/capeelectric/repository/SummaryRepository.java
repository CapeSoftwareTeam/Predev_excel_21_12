package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.Summary;

/**
 * @author capeelectricsoftware
 *
 */
@Repository
public interface SummaryRepository extends CrudRepository<Summary, Integer> {

	@Query(value="SELECT * FROM lv_safety_verification.summary_table where SITE_ID =:siteId AND STATUS != 'R'",nativeQuery = true)
	public Optional<Summary> findBySiteId(Integer siteId);
	
	public List<Summary> findByUserNameAndSiteId(String userName, Integer siteId);

}
