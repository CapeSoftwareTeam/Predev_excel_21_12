package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.IsolationCurrent;



public interface InspectionIsolationRepository extends CrudRepository<IsolationCurrent, Integer> {

	public IsolationCurrent findByLocation(String location);
	
	public IsolationCurrent findByDistributionBoardDetailsAndReferanceAndLocation(String distributionDetails, String referance, String location);
	
}
