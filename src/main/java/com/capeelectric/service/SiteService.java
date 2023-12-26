package com.capeelectric.service;

import java.util.List;
import java.util.Optional;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Site;

public interface SiteService {
	public Site addSite(Site site) throws CompanyDetailsException;

	public Site updateSite(Site site) throws CompanyDetailsException;
	
	public void updateSiteStatus(Site site) throws CompanyDetailsException;

	public void deleteSite(Integer site) throws CompanyDetailsException;

	public List<Site> retriveSite(String userName) throws CompanyDetailsException;
	
	public Optional<Site> isSiteActive(String userName) throws CompanyDetailsException;
	
	public Site retrieveSiteByName(String companyName, String departmentName, String siteName) throws CompanyDetailsException;
	
	public String retrieveByCompanyNameDepartmentSiteName(String companyName, String department, String siteName);
}