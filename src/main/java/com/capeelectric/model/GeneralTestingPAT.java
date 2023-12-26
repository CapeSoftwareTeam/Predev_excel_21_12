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
@Table(name = "GENERAL_TESTING_PAT")
public class GeneralTestingPAT {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GENERAL_TESTING_PAT_ID")
	private Integer generalTestingPATId;
	
	@Column(name = "CLASS_TYPE")
	private String classType;
		
	@Column(name = "REFERANCE")
	private String reference;
	
	@Column(name = "VOLTAGE")
	private String voltage;
	
	@Column(name = "CONTINUITY_RESISTANCE")
	private String continuityResistance;
	
	@Column(name = "INSULATION_RESISTANCE")
	private String insulationResistance;
	
	@Column(name = "LEAKAGE_CURRENT")
	private String leakageCurrent;
	
	@Column(name = "FLAG")
	private String flag;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PORTABLE_APPLIANCE_ID")
	private PortableAppliance portableAppliance;


	public Integer getGeneralTestingPATId() {
		return generalTestingPATId;
	}

	public void setGeneralTestingPATId(Integer generalTestingPATId) {
		this.generalTestingPATId = generalTestingPATId;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}


	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getContinuityResistance() {
		return continuityResistance;
	}

	public void setContinuityResistance(String continuityResistance) {
		this.continuityResistance = continuityResistance;
	}

	public String getInsulationResistance() {
		return insulationResistance;
	}

	public void setInsulationResistance(String insulationResistance) {
		this.insulationResistance = insulationResistance;
	}

	public String getLeakageCurrent() {
		return leakageCurrent;
	}

	public void setLeakageCurrent(String leakageCurrent) {
		this.leakageCurrent = leakageCurrent;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public PortableAppliance getPortableAppliance() {
		return portableAppliance;
	}

	public void setPortableAppliance(PortableAppliance portableAppliance) {
		this.portableAppliance = portableAppliance;
	}
	
	
	
	
}
