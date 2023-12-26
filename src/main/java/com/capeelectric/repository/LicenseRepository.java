package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.licence.License;

public interface LicenseRepository extends CrudRepository<License, Integer> {

	public Optional<License> findByUserName(String username);

}
