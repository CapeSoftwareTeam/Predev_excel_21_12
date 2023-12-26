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
@Table(name = "SAFETY_TESTING_RCBO")
public class SafetyTestingRCBO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SAFETY_TESTING_RCBO_ID")
	private Integer safetyTestingRCBOId;
	
	@Column(name = "RN")
	private String rN;
		
	@Column(name = "YN")
	private String yN;
	
	@Column(name = "BN")
	private String bN;
	
	@Column(name = "RE")
	private String rE;
		
	@Column(name = "YE")
	private String yE;
	
	@Column(name = "BE")
	private String bE;
	
	@Column(name = "RY")
	private String rY;
		
	@Column(name = "YB")
	private String yB;
	
	@Column(name = "BR")
	private String bR;
	
	@Column(name = "SHOCK_VOLTAGE")
	private String shockVoltage;
		
	@Column(name = "FLOOR_RESISTANCE")
	private String floorResistance;
	
	@Column(name = "WALL_RESISTANCE")
	private String wallResistance;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RCBO_ID")
	private RCBO rcbo;

	public Integer getSafetyTestingRCBOId() {
		return safetyTestingRCBOId;
	}

	public void setSafetyTestingRCBOId(Integer safetyTestingRCBOId) {
		this.safetyTestingRCBOId = safetyTestingRCBOId;
	}

	public String getrN() {
		return rN;
	}

	public void setrN(String rN) {
		this.rN = rN;
	}

	public String getyN() {
		return yN;
	}

	public void setyN(String yN) {
		this.yN = yN;
	}

	public String getbN() {
		return bN;
	}

	public void setbN(String bN) {
		this.bN = bN;
	}

	public String getrE() {
		return rE;
	}

	public void setrE(String rE) {
		this.rE = rE;
	}

	public String getyE() {
		return yE;
	}

	public void setyE(String yE) {
		this.yE = yE;
	}

	public String getbE() {
		return bE;
	}

	public void setbE(String bE) {
		this.bE = bE;
	}

	public String getrY() {
		return rY;
	}

	public void setrY(String rY) {
		this.rY = rY;
	}

	public String getyB() {
		return yB;
	}

	public void setyB(String yB) {
		this.yB = yB;
	}

	public String getbR() {
		return bR;
	}

	public void setbR(String bR) {
		this.bR = bR;
	}

	public String getShockVoltage() {
		return shockVoltage;
	}

	public void setShockVoltage(String shockVoltage) {
		this.shockVoltage = shockVoltage;
	}

	public String getFloorResistance() {
		return floorResistance;
	}

	public void setFloorResistance(String floorResistance) {
		this.floorResistance = floorResistance;
	}

	public String getWallResistance() {
		return wallResistance;
	}

	public void setWallResistance(String wallResistance) {
		this.wallResistance = wallResistance;
	}

	public RCBO getRcbo() {
		return rcbo;
	}

	public void setRcbo(RCBO rcbo) {
		this.rcbo = rcbo;
	}
	
	
}
