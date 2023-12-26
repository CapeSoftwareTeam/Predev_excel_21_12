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
@Table(name = "DIESEL_GENERATOR_DETAILS")
public class DieselGenerator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DG_ID")
	private Integer dieselGeneratorID;
	
	@Column(name = "NODE_ID")
	private String nodeId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "REFERENCE_NAME")
	private String referenceName;
	
	@Column(name = "CAPACITY_RATING")
	private Integer capacityRating;
	
	@Column(name = "VOLTAGE_RATING")
	private Integer voltageRating;
	
	@Column(name = "FAULT_CURRENT")
	private Integer faultCurrent;
	
	@Column(name = "NEUTRAL_CONNECT")
	private String neutralConnect;
	
	@Column(name = "DIESEL_GENERATOR_TEST_REPORT")
	private String dieselGeneratorTestReport;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	public Integer getDieselGeneratorID() {
		return dieselGeneratorID;
	}

	public void setDieselGeneratorID(Integer dieselGeneratorID) {
		this.dieselGeneratorID = dieselGeneratorID;
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

    public Integer getCapacityRating() {
		return capacityRating;
	}

	public void setCapacityRating(Integer capacityRating) {
		this.capacityRating = capacityRating;
	}

	public Integer getVoltageRating() {
		return voltageRating;
	}

	public void setVoltageRating(Integer voltageRating) {
		this.voltageRating = voltageRating;
	}

	public Integer getFaultCurrent() {
		return faultCurrent;
	}

	public void setFaultCurrent(Integer faultCurrent) {
		this.faultCurrent = faultCurrent;
	}

	public String getNeutralConnect() {
		return neutralConnect;
	}

	public void setNeutralConnect(String neutralConnect) {
		this.neutralConnect = neutralConnect;
	}

	public String getDieselGeneratorTestReport() {
		return dieselGeneratorTestReport;
	}

	public void setDieselGeneratorTestReport(String dieselGeneratorTestReport) {
		this.dieselGeneratorTestReport = dieselGeneratorTestReport;
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
