package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licence_table")
public class EmcLicense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LICENCE_ID")
	private Integer licenceId;

	@Column(name = "EMC_NO_OF_LICENCE")
	private String emcNoOfLicence;
	
	@Column(name = "USERNAME")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(Integer licenceId) {
		this.licenceId = licenceId;
	}

	public String getEmcNoOfLicence() {
		return emcNoOfLicence;
	}

	public void setEmcNoOfLicence(String emcNoOfLicence) {
		this.emcNoOfLicence = emcNoOfLicence;
	}
 
}
