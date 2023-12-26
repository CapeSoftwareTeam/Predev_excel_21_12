/**
 * 
 */
package com.capeelectric.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "GENERAL_TESTING_FAN")
public class GeneralTestingFan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GENERAL_TESTING_FAN_ID")
	private Integer generalTestingFanId;
	
	@Column(name = "PHN")
	private String phN;
		
	@Column(name = "PHE")
	private String phE;
	
	@Column(name = "NE")
	private String nE;
	
	@Column(name = "IR_CURRENT")
	private String iRCurrent;
	
	@Column(name = "IN_CURRENT")
	private String iNCurrent;
	
	@Column(name = "IPE_CURRENT")
	private String iPECurrent;
	
	@Column(name = "POWER_FACTOR")
	private String powerFactor;
	
	@Column(name = "FREQUENCY")
	private Integer frequency;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FAN_ID")
	private Fan fan;

	public Integer getGeneralTestingFanId() {
		return generalTestingFanId;
	}

	public void setGeneralTestingFanId(Integer generalTestingFanId) {
		this.generalTestingFanId = generalTestingFanId;
	}

	public String getPhN() {
		return phN;
	}

	public void setPhN(String phN) {
		this.phN = phN;
	}

	public String getPhE() {
		return phE;
	}

	public void setPhE(String phE) {
		this.phE = phE;
	}

	public String getnE() {
		return nE;
	}

	public void setnE(String nE) {
		this.nE = nE;
	}

	public String getiRCurrent() {
		return iRCurrent;
	}

	public void setiRCurrent(String iRCurrent) {
		this.iRCurrent = iRCurrent;
	}

    public String getiNCurrent() {
		return iNCurrent;
	}

	public void setiNCurrent(String iNCurrent) {
		this.iNCurrent = iNCurrent;
	}

	public String getiPECurrent() {
		return iPECurrent;
	}

	public void setiPECurrent(String iPECurrent) {
		this.iPECurrent = iPECurrent;
	}
	
	public String getPowerFactor() {
		return powerFactor;
	}

	public void setPowerFactor(String powerFactor) {
		this.powerFactor = powerFactor;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Fan getFan() {
		return fan;
	}

	public void setFan(Fan fan) {
		this.fan = fan;
	}
	
	

}
