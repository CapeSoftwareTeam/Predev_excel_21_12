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
@Table(name = "GENERAL_TESTING_CABLE_CONNECTOR")
public class GeneralTestingCableConnector {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GENERAL_TESTING_CABLE_CONNECTOR_ID")
	private Integer generalTestingCableConnectorId;

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
	private CableConnector cableConnector;

	public Integer getGeneralTestingCableConnectorId() {
		return generalTestingCableConnectorId;
	}

	public void setGeneralTestingCableConnectorId(Integer generalTestingCableConnectorId) {
		this.generalTestingCableConnectorId = generalTestingCableConnectorId;
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
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public CableConnector getCableConnector() {
		return cableConnector;
	}

	public void setCableConnector(CableConnector cableConnector) {
		this.cableConnector = cableConnector;
	}

}
