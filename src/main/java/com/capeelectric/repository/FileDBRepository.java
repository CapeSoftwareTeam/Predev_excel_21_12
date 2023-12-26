package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.ResponseFile;

@Repository

public interface FileDBRepository extends CrudRepository<ResponseFile, Integer> {

	Optional<ResponseFile> findByEmcId(Integer emcid);

}
