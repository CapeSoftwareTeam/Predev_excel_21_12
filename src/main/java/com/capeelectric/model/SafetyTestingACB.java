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
@Table(name = "SAFETY_TESTING_ACB")
public class SafetyTestingACB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SAFETY_TESTING_ACB_ID")
	private Integer safetyTestingACBId;
	
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
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACB_ID")
	private ACB acb;

	public Integer getSafetyTestingACBId() {
		return safetyTestingACBId;
	}

	public void setSafetyTestingACBId(Integer safetyTestingACBId) {
		this.safetyTestingACBId = safetyTestingACBId;
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

	public ACB getAcb() {
		return acb;
	}

	public void setAcb(ACB acb) {
		this.acb = acb;
	}
	
	
}
