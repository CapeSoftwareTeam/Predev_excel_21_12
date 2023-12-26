package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.capeelectric.model.licence.RiskLicense;
@Repository
public interface RiskLicenseRepository extends CrudRepository<RiskLicense, Integer>{
	
	public Optional<RiskLicense> findByUserName(String userName);

}	
