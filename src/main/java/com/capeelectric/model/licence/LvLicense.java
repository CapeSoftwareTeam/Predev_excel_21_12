package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licence_table")
public class LvLicense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LICENCE_ID")
	private Integer licenceId;

	@Column(name = "LV_NO_OF_LICENCE")
	private String lvNoOfLicence;
	
	@Column(name = "USERNAME")
	private String userName;

	public String getLvNoOfLicence() {
		return lvNoOfLicence;
	}

	public void setLvNoOfLicence(String lvNoOfLicence) {
		this.lvNoOfLicence = lvNoOfLicence;
	}

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
 
}
