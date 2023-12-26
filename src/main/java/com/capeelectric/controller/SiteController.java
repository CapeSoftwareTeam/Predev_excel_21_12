package com.capeelectric.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Site;
import com.capeelectric.service.SiteService;

@RestController()
@RequestMapping("/api/v2")
public class SiteController {        
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private SiteService siteService;

	@PostMapping("/lv/addSite")
	public ResponseEntity<Site> addSite(@RequestBody Site site) throws CompanyDetailsException {
		logger.debug("called addSite function UserName : {}, Site : {}", site.getSite());
		return new ResponseEntity<Site>(siteService.addSite(site), HttpStatus.CREATED);
	}

	@PutMapping("/lv/updateSite")
	public ResponseEntity<Site> updateSite(@RequestBody Site site) throws CompanyDetailsException {
		logger.debug("called updateSite function UserName: {},Site : {}", site.getUserName(), site.getSite());
		return new ResponseEntity<Site>(siteService.updateSite(site),HttpStatus.OK);
	}
	
	@PutMapping("/lv/updateSiteStatus")
	public ResponseEntity<String> updateSiteStatus(@RequestBody Site site) throws CompanyDetailsException {
		logger.debug("called updateSiteStatus function UserName: {},Site : {}", site.getUserName(), site.getSite());
		siteService.updateSiteStatus(site);
		logger.debug("Ended updateSiteStatus function");
		return new ResponseEntity<String>("Site has been successfully deleted", HttpStatus.OK);
	}

	@DeleteMapping("/lv/deleteSite/{siteId}")
	public ResponseEntity<String> deleteSite(@PathVariable Integer siteId) throws CompanyDetailsException {
		logger.debug("called deleteSite function siteId: {}", siteId);
		siteService.deleteSite(siteId);
		logger.debug("Ended deleteSite function");
		return new ResponseEntity<String>("Site Succesfully Deleted", HttpStatus.OK);
	}

	
	@GetMapping("/lv/retriveSite/{userName}")
	public ResponseEntity<List<Site>> retriveSite(@PathVariable String userName) throws CompanyDetailsException {
		logger.debug("called retriveSite function UserName: {}", userName);
		return new ResponseEntity<List<Site>>(siteService.retriveSite(userName), HttpStatus.OK);
	}
	
	@GetMapping("/lv/retrieveSiteByName/{companyName}/{department}/{siteName}")
	public ResponseEntity<Site> retrieveSiteByName(@PathVariable String companyName, 
			@PathVariable String department, @PathVariable String siteName) throws CompanyDetailsException {
		logger.debug("called retriveSiteByName function Company Name: {}, Department: {}, Site Name: {}", companyName,
				department, siteName);
		return new ResponseEntity<Site>(siteService.retrieveSiteByName(companyName, department, siteName),
				HttpStatus.OK);
	}
	
	@GetMapping("/lv/retrieveSiteName/{companyName}/{department}/{siteName}")
	public String retrieveSiteName(@PathVariable String companyName, @PathVariable String department, @PathVariable String siteName) throws CompanyDetailsException {
		logger.debug("called retriveSiteByName function Company Name: {}, Department: {}, Site Name: {}", companyName,
				department, siteName);
		return siteService.retrieveByCompanyNameDepartmentSiteName(companyName, department, siteName);
	}

	@GetMapping("/isSiteActive/{userName}")
	public ResponseEntity<Optional<Site>> isSiteActive(@PathVariable String userName) throws CompanyDetailsException {
		logger.debug("called retriveSite function UserName: {}", userName);
		return new ResponseEntity<Optional<Site>>(siteService.isSiteActive(userName), HttpStatus.OK);
	}
	
	@GetMapping("/lv/isSiteActive/{userName}")
	public ResponseEntity<Optional<Site>> isSiteActiveViewer(@PathVariable String userName) throws CompanyDetailsException {
		logger.debug("called retriveSite function UserName: {}", userName);
		return new ResponseEntity<Optional<Site>>(siteService.isSiteActive(userName), HttpStatus.OK);
	}
}
