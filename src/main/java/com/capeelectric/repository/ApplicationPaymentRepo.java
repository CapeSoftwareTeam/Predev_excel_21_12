package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.ApplicationLicense;

public interface ApplicationPaymentRepo extends CrudRepository<ApplicationLicense, Integer> {

	public List<ApplicationLicense> findByInspectorEmail(String orderId);

	public Optional<ApplicationLicense> findByOrderId(String username);

//	public List<BuyRentMeter> findByCustomerEmail(String username);

}
	