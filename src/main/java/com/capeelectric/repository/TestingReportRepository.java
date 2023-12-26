package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.TestingReport;

@Repository
public interface TestingReportRepository extends CrudRepository<TestingReport, Integer> {
	
	@Query(value="SELECT * FROM lv_safety_verification.testing_reports_table where SITE_ID =:siteId AND STATUS != 'R'",nativeQuery = true)
	Optional<TestingReport> findBySiteId(Integer siteId);

	TestingReport findByUserNameAndSiteId(String userName, Integer siteId);

}
