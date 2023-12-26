package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.licence.LpsLicense;
@Repository
public interface LpsLicenseRepository extends CrudRepository<LpsLicense, Integer>{
	
	public Optional<LpsLicense> findByUserName(String userName);

}	
