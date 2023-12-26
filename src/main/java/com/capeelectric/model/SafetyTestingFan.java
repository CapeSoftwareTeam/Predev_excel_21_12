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
@Table(name = "SAFETY_TESTING_FAN")
public class SafetyTestingFan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SAFETY_TESTING_FAN_ID")
	private Integer safetyTestingFanId;
	
	@Column(name = "PHN")
	private String phN;
		
	@Column(name = "PHE")
	private String phE;
	
	@Column(name = "NE")
	private String nE;
	
//	@Column(name = "SHOCK_VOLTAGE")
//	private String shockVoltage;
//	
//	@Column(name = "FLOOR_RESISTANCE")
//	private Integer floorResistance;
//	
//	@Column(name = "WALL_RESISTANCE")
//	private Integer wallResistance;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FAN_ID")
	private Fan fan;

	public Integer getSafetyTestingFanId() {
		return safetyTestingFanId;
	}

	public void setSafetyTestingFanId(Integer safetyTestingFanId) {
		this.safetyTestingFanId = safetyTestingFanId;
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

	public Fan getFan() {
		return fan;
	}

	public void setFan(Fan fan) {
		this.fan = fan;
	}
	
	
	
	
}
