package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the site_table database table.
 * 
 */
@Entity
@Table(name = "intermsave_table")
public class LVInternSave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "INTERMSAVE_ID")
	private Integer intermsave_id;

	@Column(name = "BASIC_SERVICE")
	private String basicService;

	@Column(name = "SUPPLY_SERVICE")
	private String supplyService;

	@Column(name = "INSPECTION_SERVICE")
	private String inspectionService;

	@Column(name = "TESTING_SERVICE")
	private String testingService;

	@Column(name = "SITE_ID")
	private Integer siteId;
	
	@Column(name = "USERNAME")
	private String userName;

	public Integer getIntermsave_id() {
		return intermsave_id;
	}

	public void setIntermsave_id(Integer intermsave_id) {
		this.intermsave_id = intermsave_id;
	}

	public String getBasicService() {
		return basicService;
	}

	public void setBasicService(String basicService) {
		this.basicService = basicService;
	}

	public String getSupplyService() {
		return supplyService;
	}

	public void setSupplyService(String supplyService) {
		this.supplyService = supplyService;
	}

	public String getInspectionService() {
		return inspectionService;
	}

	public void setInspectionService(String inspectionService) {
		this.inspectionService = inspectionService;
	}

	public String getTestingService() {
		return testingService;
	}

	public void setTestingService(String testingService) {
		this.testingService = testingService;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}