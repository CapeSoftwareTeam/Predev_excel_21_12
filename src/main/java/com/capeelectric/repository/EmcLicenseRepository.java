package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.licence.EmcLicense;
import com.capeelectric.model.licence.RiskLicense;
@Repository
public interface EmcLicenseRepository extends CrudRepository<EmcLicense, Integer>{
	
	public Optional<EmcLicense> findByUserName(String userName);

}	
