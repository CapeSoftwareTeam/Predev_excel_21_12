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
@Table(name = "FAN")
public class Fan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FAN_ID")
	private Integer fanId;
	
	@Column(name = "NODE_ID")
	private String nodeId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "REFERENCE_NAME")
	private String referenceName;
		
	@Column(name = "MANUFACTURER_NAME")
	private String manufacturerName;
	
	@Column(name = "POWER_CAPACITY")
	private String powerCapacity;
	
	@Column(name = "CURRENT_RATING")
	private Integer currentRating;
	
	@Column(name = "VOLTAGE")
	private Integer voltage;
	
	@Column(name = "MODEL")
	private String model;
	
//	@Column(name = "INCOMING_SIZE_PHASE")
//	private String incomingSizePhase;
//	
//	@Column(name = "INCOMING_SIZE_NEUTRAL")
//	private String incomingSizeNeutral;
//	
//	@Column(name = "INCOMING_SIZE_PROTECTIVE")
//	private Integer incomingSizeProtective;
//	
//	@Column(name = "INCOMING_LENGTH_PHASE")
//	private String incomingLengthPhase;
//	
//	@Column(name = "INCOMING_LENGTH_NEUTRAL")
//	private Integer incomingLengthNeutral;
//	
//	@Column(name = "INCOMING_LENGTH_PROTECTIVE")
//	private String incomingLengthProtective;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "fan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GeneralTestingFan> generalTestingFan;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "fan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SafetyTestingFan> safetyTestingFan;

	public Integer getFanId() {
		return fanId;
	}

	public void setFanId(Integer fanId) {
		this.fanId = fanId;
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

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public Integer getCurrentRating() {
		return currentRating;
	}

	public void setCurrentRating(Integer currentRating) {
		this.currentRating = currentRating;
	}

	public Integer getVoltage() {
		return voltage;
	}

	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<GeneralTestingFan> getGeneralTestingFan() {
		return generalTestingFan;
	}

	public void setGeneralTestingFan(List<GeneralTestingFan> generalTestingFan) {
		this.generalTestingFan = generalTestingFan;
	}

	public List<SafetyTestingFan> getSafetyTestingFan() {
		return safetyTestingFan;
	}

	public void setSafetyTestingFan(List<SafetyTestingFan> safetyTestingFan) {
		this.safetyTestingFan = safetyTestingFan;
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

	public String getPowerCapacity() {
		return powerCapacity;
	}

	public void setPowerCapacity(String powerCapacity) {
		this.powerCapacity = powerCapacity;
	}
	
	
}
