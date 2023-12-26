/**
 * 
 */
package com.capeelectric.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "PORTABLE_APPLIANCE")
public class PortableAppliance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PORTABLE_APPLIANCE_ID")
	private Integer portableApplianceId;
	
	@Column(name = "NODE_ID")
	private String nodeId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "REFERENCE_NAME")
	private String referenceName;
	
//	@Column(name = "PORTABLE_APPLIANCE_NAME")
//	private String portableApplianceName;
		
	@Column(name = "MANUFACTURER_NAME")
	private String manufacturerName;
	
	@Column(name = "RATING")
	private Integer rating;
	
	@Column(name = "VOLTAGE")
	private Integer voltage;
	
	@Column(name = "INSULATION_CLASS")
	private String insulationClass;
	
//	@Column(name = "OUTGOING_SIZE_PHASE")
//	private Integer outGoingSizePhase;
//	
//	@Column(name = "OUTGOING_SIZE_NEUTRAL")
//	private Integer outGoingSizeNeutral;
//	
//	@Column(name = "OUTGOING_SIZE_PROTECTIVE")
//	private Integer outGoingSizeProtective;	
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "portableAppliance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GeneralTestingPAT> generalTestingPAT;

	public Integer getPortableApplianceId() {
		return portableApplianceId;
	}

	public void setPortableApplianceId(Integer portableApplianceId) {
		this.portableApplianceId = portableApplianceId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

//	public String getPortableApplianceName() {
//		return portableApplianceName;
//	}
//
//	public void setPortableApplianceName(String portableApplianceName) {
//		this.portableApplianceName = portableApplianceName;
//	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getVoltage() {
		return voltage;
	}

	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}

	public String getInsulationClass() {
		return insulationClass;
	}

	public void setInsulationClass(String insulationClass) {
		this.insulationClass = insulationClass;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<GeneralTestingPAT> getGeneralTestingPAT() {
		return generalTestingPAT;
	}

	public void setGeneralTestingPAT(List<GeneralTestingPAT> generalTestingPAT) {
		this.generalTestingPAT = generalTestingPAT;
	}
	
	

}
