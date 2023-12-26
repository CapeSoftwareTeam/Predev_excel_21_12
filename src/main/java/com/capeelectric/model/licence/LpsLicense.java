package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licence_table")
public class LpsLicense{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LICENCE_ID")
	private Integer licenceId;

	@Column(name = "LPS_NO_OF_LICENCE")
	private String lpsNoOfLicence;
	
	@Column(name = "USERNAME")
	private String userName;
	
	public Integer getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(Integer licenceId) {
		this.licenceId = licenceId;
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
	
}
