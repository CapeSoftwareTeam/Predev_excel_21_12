package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.capeelectric.model.ClientDetails;

@Repository
public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Integer> {


	List<ClientDetails> findByEmcId(Integer emcId);

	public Optional<ClientDetails> findByUserNameAndEmcId(String userName, Integer emcId);

	public Optional<ClientDetails> findByClientNameAndStatus(String clientName,String active);
	
	List<ClientDetails> findByUserName(String clientName);
	
	public Optional<ClientDetails> findByClientName(String clientName);

	public Optional<ClientDetails> findByEmailAndStatus(String userName, String string);

}