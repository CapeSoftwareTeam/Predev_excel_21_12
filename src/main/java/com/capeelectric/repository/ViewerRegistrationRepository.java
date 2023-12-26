package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.ViewerRegister;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Repository
public interface ViewerRegistrationRepository extends CrudRepository<ViewerRegister, Integer> {

	public Optional<ViewerRegister> findByUsername(String username);
	
	public Optional<ViewerRegister> findByContactNumber(String number);

}
