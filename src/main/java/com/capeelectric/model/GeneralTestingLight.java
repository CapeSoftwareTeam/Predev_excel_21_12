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
@Table(name = "GENERAL_TESTING_LIGHT")
public class GeneralTestingLight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GENERAL_TESTING_LIGHT_ID")
	private Integer generalTestingLightId;
	
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
	@JoinColumn(name = "LIGHT_ID")
	private Light light;

	public Integer getGeneralTestingLightId() {
		return generalTestingLightId;
	}

	public void setGeneralTestingLightId(Integer generalTestingLightId) {
		this.generalTestingLightId = generalTestingLightId;
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

	public Light getLight() {
		return light;
	}

	public void setLight(Light light) {
		this.light = light;
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
	
	
}
