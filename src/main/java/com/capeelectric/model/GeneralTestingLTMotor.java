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
@Table(name = "GENERAL_TESTING_LT_MOTOR")
public class GeneralTestingLTMotor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GENERAL_TESTING_MOTOR_ID")
	private Integer generalTestingMotorId;
	
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
	
	@Column(name = "IR_CURRENT")
	private String iRCurrent;
	
	@Column(name = "IY_CURRENT")
	private String iYCurrent;
	
	@Column(name = "IB_CURRENT")
	private String iBCurrent;
	
	@Column(name = "IPE_CURRENT")
	private String iPECurrent;
	
	@Column(name = "POLARITY_R")
	private String polarityR;
		
	@Column(name = "POLARITY_Y")
	private String polarityY;
	
	@Column(name = "POLARITY_B")
	private String polarityB;
	
	@Column(name = "POWER_FACTOR")
	private String powerFactor;
		
	@Column(name = "FREQUENCY")
	private String frequency;	
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MOTOR_ID")
	private LTMotor ltMotor;

	public Integer getGeneralTestingMotorId() {
		return generalTestingMotorId;
	}

	public void setGeneralTestingMotorId(Integer generalTestingMotorId) {
		this.generalTestingMotorId = generalTestingMotorId;
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

	public String getiPECurrent() {
		return iPECurrent;
	}

	public void setiPECurrent(String iPECurrent) {
		this.iPECurrent = iPECurrent;
	}
	
	public LTMotor getLtMotor() {
		return ltMotor;
	}

	public void setLtMotor(LTMotor ltMotor) {
		this.ltMotor = ltMotor;
	}

	public String getPolarityR() {
		return polarityR;
	}

	public void setPolarityR(String polarityR) {
		this.polarityR = polarityR;
	}

	public String getPolarityY() {
		return polarityY;
	}

	public void setPolarityY(String polarityY) {
		this.polarityY = polarityY;
	}

	public String getPolarityB() {
		return polarityB;
	}

	public void setPolarityB(String polarityB) {
		this.polarityB = polarityB;
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
	
}
