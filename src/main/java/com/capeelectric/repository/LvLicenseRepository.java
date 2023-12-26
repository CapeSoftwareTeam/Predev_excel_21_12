package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.licence.LvLicense;
@Repository
public interface LvLicenseRepository extends CrudRepository<LvLicense, Integer> {

	public Optional<LvLicense> findByUserName(String userName);

}
