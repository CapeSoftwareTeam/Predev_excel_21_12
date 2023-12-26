package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.TestDistRecords;

@Repository
public interface DetailTestingRepository extends CrudRepository<TestDistRecords, Integer> {

}
	