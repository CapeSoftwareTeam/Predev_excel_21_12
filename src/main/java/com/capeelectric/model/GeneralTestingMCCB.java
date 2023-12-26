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
@Table(name = "GENERAL_TESTING_MCCB")
public class GeneralTestingMCCB {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GENERAL_TESTING_MCCB_ID")
	private Integer generalTestingMCCBId;
	
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
	
	@Column(name = "NE")
	private String nE;
	
	@Column(name = "ir_current")
	private String iRCurrent;
	
	@Column(name = "iy_current")
	private String iYCurrent;
	
	@Column(name = "ib_current")
	private String iBCurrent;
	
	@Column(name = "in_current")
	private String iNCurrent;
	
	@Column(name = "ipe_current")
	private String iPECurrent;
	
	@Column(name = "POWER_FACTOR")
	private String powerFactor;
		
	@Column(name = "FREQUENCY")
	private String frequency;	
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MCCB_ID")
	private MCCB mccb;

	public Integer getGeneralTestingMCCBId() {
		return generalTestingMCCBId;
	}

	public void setGeneralTestingMCCBId(Integer generalTestingMCCBId) {
		this.generalTestingMCCBId = generalTestingMCCBId;
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

	public String getiYCurrent() {
		return iYCurrent;
	}

	public void setiYCurrent(String iYCurrent) {
		this.iYCurrent = iYCurrent;
	}

	public String getiBCurrent() {
		return iBCurrent;
	}

	public void setiBCurrent(String iBCurrent) {
		this.iBCurrent = iBCurrent;
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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public MCCB getMccb() {
		return mccb;
	}

	public void setMccb(MCCB mccb) {
		this.mccb = mccb;
	}
	
	

}
