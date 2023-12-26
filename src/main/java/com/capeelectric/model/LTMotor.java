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

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "LT_MOTOR")
@NamedQueries(value = {
		@NamedQuery(name = "ltMotorRepository.findByFileNameAndNodeId", query = "select s from LTMotor s where s.fileName=:fileName and s.nodeId=:nodeId")
		

})
public class LTMotor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MOTOR_ID")
	private Integer motorId;
	
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
	
	@Column(name = "WINDING_CONNECTION")
	private String windingConnection;
	
	@Column(name = "POWER_CAPACITY")
	private Integer powerCapacity;
	
	@Column(name = "CURRENT_RATING")
	private Integer currentRating;
	
	@Column(name = "VOLTAGE")
	private Integer voltage;
	
	@Column(name = "NO_OF_PHASE")
	private String noOfPhase;
	
	@Column(name = "Model")
	private Integer model;
	
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
//	private Integer incomingLengthProtective;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "ltMotor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GeneralTestingLTMotor> generalTestingLTMotor;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "ltMotor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SafetyTestingLTMotor> safetyTestingLTMotor;

	public Integer getMotorId() {
		return motorId;
	}

	public void setMotorId(Integer motorId) {
		this.motorId = motorId;
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

	public String getWindingConnection() {
		return windingConnection;
	}

	public void setWindingConnection(String windingConnection) {
		this.windingConnection = windingConnection;
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

	public String getNoOfPhase() {
		return noOfPhase;
	}

	public void setNoOfPhase(String noOfPhase) {
		this.noOfPhase = noOfPhase;
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

//	public Integer getIncomingSizePhase() {
//		return incomingSizePhase;
//	}
//
//	public void setIncomingSizePhase(Integer incomingSizePhase) {
//		this.incomingSizePhase = incomingSizePhase;
//	}
//
//	public Integer getIncomingSizeNeutral() {
//		return incomingSizeNeutral;
//	}
//
//	public void setIncomingSizeNeutral(Integer incomingSizeNeutral) {
//		this.incomingSizeNeutral = incomingSizeNeutral;
//	}
//
//	public Integer getIncomingSizeProtective() {
//		return incomingSizeProtective;
//	}
//
//	public void setIncomingSizeProtective(Integer incomingSizeProtective) {
//		this.incomingSizeProtective = incomingSizeProtective;
//	}
//
//	public Integer getIncomingLengthPhase() {
//		return incomingLengthPhase;
//	}
//
//	public void setIncomingLengthPhase(Integer incomingLengthPhase) {
//		this.incomingLengthPhase = incomingLengthPhase;
//	}
//
//	public Integer getIncomingLengthNeutral() {
//		return incomingLengthNeutral;
//	}
//
//	public void setIncomingLengthNeutral(Integer incomingLengthNeutral) {
//		this.incomingLengthNeutral = incomingLengthNeutral;
//	}
//
//	public Integer getIncomingLengthProtective() {
//		return incomingLengthProtective;
//	}
//
//	public void setIncomingLengthProtective(Integer incomingLengthProtective) {
//		this.incomingLengthProtective = incomingLengthProtective;
//	}

	public List<GeneralTestingLTMotor> getGeneralTestingLTMotor() {
		return generalTestingLTMotor;
	}

	public void setGeneralTestingLTMotor(List<GeneralTestingLTMotor> generalTestingLTMotor) {
		this.generalTestingLTMotor = generalTestingLTMotor;
	}

	public List<SafetyTestingLTMotor> getSafetyTestingLTMotor() {
		return safetyTestingLTMotor;
	}

	public void setSafetyTestingLTMotor(List<SafetyTestingLTMotor> safetyTestingLTMotor) {
		this.safetyTestingLTMotor = safetyTestingLTMotor;
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
