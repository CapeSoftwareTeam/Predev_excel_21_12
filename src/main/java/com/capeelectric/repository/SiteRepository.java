package com.capeelectric.repository;

 
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {
	public Optional<Site> findByUserNameAndSite(String userName, String site);

	public List<Site> findByUserName(String userName);
	
	public List<Site> findBysiteId(Integer siteId);
	 
	public Site findByCompanyNameAndDepartmentNameAndSite(String companyName, String departmentName, String site);

	public Site findByUserNameAndStatus(String userName, String string);

	public Optional<Site> findByAssignedToAndStatus(String userName, String string);

}
