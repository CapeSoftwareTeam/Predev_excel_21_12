package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.LVInternSave;

@Repository
public interface InternSaveRepo extends CrudRepository<LVInternSave, Integer> {

	public Optional<LVInternSave> findByUserNameAndSiteId(String userName, Integer siteId);

}
