package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.TestExcelData;



public interface TestExcelDataRepo  extends CrudRepository<TestExcelData, Integer> {
	

}
