/**
 * 
 */
package com.capeelectric.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "TRANSFORMER_DETAILS")
public class TransformerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRANSFORMER_DETAILS_ID")
	private Integer transformerDetailsId;
	
	@Column(name = "NODE_ID")
	private String nodeId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "REFERENCE_NAME")
	private String referenceName;
	
	@Column(name = "CAPACITY_RATING")
	private String capacityRating;
	
	@Column(name = "SIDE_VOLTAGE_HV")
	private String sideVoltageHV;
	
	@Column(name = "SIDE_VOLTAGE_LV")
	private String sideVoltageLV;
	
	@Column(name = "VECTOR_GROUP")
	private String vectorGroup;
	
	@Column(name = "FAULT_CURRENT")
	private Float faultCurrent;
	
	@Column(name = "IMPEDANCE")
	private Float impedance;
	
	@Column(name = "CONNECTED_NEUTRAL")
	private String connectedNeutral;
	
	@Column(name = "TRANSFORMER_TEST")
	private String transformerTest;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	public Integer getTransformerDetailsId() {
		return transformerDetailsId;
	}

	public void setTransformerDetailsId(Integer transformerDetailsId) {
		this.transformerDetailsId = transformerDetailsId;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getCapacityRating() {
		return capacityRating;
	}

	public void setCapacityRating(String capacityRating) {
		this.capacityRating = capacityRating;
	}

	public String getVectorGroup() {
		return vectorGroup;
	}

	public void setVectorGroup(String vectorGroup) {
		this.vectorGroup = vectorGroup;
	}

	public String getConnectedNeutral() {
		return connectedNeutral;
	}

	public void setConnectedNeutral(String connectedNeutral) {
		this.connectedNeutral = connectedNeutral;
	}

	public String getTransformerTest() {
		return transformerTest;
	}

	public void setTransformerTest(String transformerTest) {
		this.transformerTest = transformerTest;
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

	public String getSideVoltageHV() {
		return sideVoltageHV;
	}

	public void setSideVoltageHV(String sideVoltageHV) {
		this.sideVoltageHV = sideVoltageHV;
	}

	public String getSideVoltageLV() {
		return sideVoltageLV;
	}

	public void setSideVoltageLV(String sideVoltageLV) {
		this.sideVoltageLV = sideVoltageLV;
	}

	public Float getFaultCurrent() {
		return faultCurrent;
	}

	public void setFaultCurrent(Float faultCurrent) {
		this.faultCurrent = faultCurrent;
	}

	public Float getImpedance() {
		return impedance;
	}

	public void setImpedance(Float impedance) {
		this.impedance = impedance;
	}
}
