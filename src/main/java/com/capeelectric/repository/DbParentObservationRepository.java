package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.DbParentObservation;

@Repository
public interface DbParentObservationRepository extends CrudRepository<DbParentObservation, Integer> {

	Optional<DbParentObservation> findByReferenceId(Integer dbParentID);

}
