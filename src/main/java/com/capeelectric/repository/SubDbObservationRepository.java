package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.SubDbObservation;

@Repository
public interface SubDbObservationRepository extends CrudRepository<SubDbObservation, Integer>  {

	Optional<SubDbObservation> findByReferenceId(Integer subDbParentID);

}
