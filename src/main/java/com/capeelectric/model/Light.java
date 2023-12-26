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
@Table(name = "LIGHT")
public class Light {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LIGHT_ID")
	private Integer lightId;
	
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
	private Integer powerCapacity;
	
	@Column(name = "CURRENT_RATING")
	private Integer currentRating;
	
	@Column(name = "VOLTAGE")
	private Integer voltage;
	
	@Column(name = "MODEL")
	private String model;
	
//	@Column(name = "INCOMING_SIZE_PHASE")
//	private Integer incomingSizePhase;
//	
//	@Column(name = "INCOMING_SIZE_NEUTRAL")
//	private Integer incomingSizeNeutral;
//	
//	@Column(name = "INCOMING_SIZE_PROTECTIVE")
//	private Integer incomingSizeProtective;
//	
//	@Column(name = "INCOMING_LENGTH_PHASE")
//	private Integer incomingLengthPhase;
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
	@OneToMany(mappedBy = "light", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GeneralTestingLight> generalTestingLight;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "light", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SafetyTestingLight> safetyTestingLight;

	public Integer getLightId() {
		return lightId;
	}

	public void setLightId(Integer lightId) {
		this.lightId = lightId;
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

	public Integer getPowerCapacity() {
		return powerCapacity;
	}

	public void setPowerCapacity(Integer powerCapacity) {
		this.powerCapacity = powerCapacity;
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

	

	public List<GeneralTestingLight> getGeneralTestingLight() {
		return generalTestingLight;
	}

	public void setGeneralTestingLight(List<GeneralTestingLight> generalTestingLight) {
		this.generalTestingLight = generalTestingLight;
	}

	public List<SafetyTestingLight> getSafetyTestingLight() {
		return safetyTestingLight;
	}

	public void setSafetyTestingLight(List<SafetyTestingLight> safetyTestingLight) {
		this.safetyTestingLight = safetyTestingLight;
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
	
}
