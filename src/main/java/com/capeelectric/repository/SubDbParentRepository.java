package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.SubDbParent;

@Repository
public interface SubDbParentRepository extends CrudRepository<SubDbParent, Integer>{

	Optional<SubDbParent> findByIpaoInspectionSubDB(Integer ipaoInspectionId);

}
