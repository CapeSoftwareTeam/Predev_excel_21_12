package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licence_table")
public class License {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LICENCE_ID")
	private Integer licenceId;

	// LV
	@Column(name = "LV_NO_OF_LICENCE")
	private String lvNoOfLicence;

	// LPS
	@Column(name = "LPS_NO_OF_LICENCE")
	private String lpsNoOfLicence;
	
	// RISK
	@Column(name = "RISK_NO_OF_LICENCE")
	private String riskNoOfLicence;
	
	// EMC
	@Column(name = "EMC_NO_OF_LICENCE")
	private String emcNoOfLicence;
	
	@Column(name = "USERNAME")
	private String userName;

	public Integer getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(Integer licenceId) {
		this.licenceId = licenceId;
	}

	public String getLvNoOfLicence() {
		return lvNoOfLicence;
	}

	public void setLvNoOfLicence(String lvNoOfLicence) {
		this.lvNoOfLicence = lvNoOfLicence;
	}

	public String getLpsNoOfLicence() {
		return lpsNoOfLicence;
	}

	public void setLpsNoOfLicence(String lpsNoOfLicence) {
		this.lpsNoOfLicence = lpsNoOfLicence;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRiskNoOfLicence() {
		return riskNoOfLicence;
	}

	public void setRiskNoOfLicence(String riskNoOfLicence) {
		this.riskNoOfLicence = riskNoOfLicence;
	}

	public String getEmcNoOfLicence() {
		return emcNoOfLicence;
	}

	public void setEmcNoOfLicence(String emcNoOfLicence) {
		this.emcNoOfLicence = emcNoOfLicence;
	}
	
}
