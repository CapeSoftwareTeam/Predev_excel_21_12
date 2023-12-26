package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.DbParentArray;

@Repository
public interface DbParentRepository extends CrudRepository<DbParentArray, Integer>{

	public DbParentArray findByDistributionBoardAndDistributionLocationAndDistributionSourceDetailsAndDbParentFlag(String distributionBoard,
			String distributionLocation, String distributionSourceDetails,String dbParentFlag);

}
