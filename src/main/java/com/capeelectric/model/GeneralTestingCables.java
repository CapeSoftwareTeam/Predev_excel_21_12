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
@Table(name = "GENERAL_TESTING_CABLES")
public class GeneralTestingCables {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GENERAL_TESTING_CABLE_ID")
	private Integer generalTestingCableId;
	
	@Column(name = "POTENTIAL_REPORT")
	private String potentialReport;
	
	@Column(name = "PHN")
	private String phN;

	@Column(name = "PHNI_RESISTANCE")
	private String phNIResistance;
	
	@Column(name = "PHNC_RESISTANCE")
	private String phNCResistance;
	
	@Column(name = "FLAG")
	private String flag;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CABLE_ID")
	private Cables cables;

	public Integer getGeneralTestingCableId() {
		return generalTestingCableId;
	}

	public void setGeneralTestingCableId(Integer generalTestingCableId) {
		this.generalTestingCableId = generalTestingCableId;
	}

	public String getPotentialReport() {
		return potentialReport;
	}

	public void setPotentialReport(String potentialReport) {
		this.potentialReport = potentialReport;
	}
	
	public String getPhN() {
		return phN;
	}

	public void setPhN(String phN) {
		this.phN = phN;
	}

	public String getPhNIResistance() {
		return phNIResistance;
	}

	public void setPhNIResistance(String phNIResistance) {
		this.phNIResistance = phNIResistance;
	}

	public String getPhNCResistance() {
		return phNCResistance;
	}

	public void setPhNCResistance(String phNCResistance) {
		this.phNCResistance = phNCResistance;
	}
	
	public Cables getCables() {
		return cables;
	}

	public void setCables(Cables cables) {
		this.cables = cables;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
