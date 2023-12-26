package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 */


@Entity
@Table(name = "PROTECTIVE_EARTH_CONDUCTORS")
public class ProtectiveEarthConductors implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTH_PROTECTIVE_CONDUCTOR_ID")
	private Integer earthProductiveConductorID;
	
	@Column(name = "EARTH_PROTECTIVE_CABLE_ID")
	private String earthProductiveCableID ; 
	
	@Column(name = "FILE_NAME")
	private String fileName ; 
	
	@Column(name = "USER_NAME")
	private String userName; 
	
	@Column(name = "MAKE")
	private String make;
	
	@Column(name = "NODE_ID")
	private String nodeId;
	
	@Column(name = "TYPE_FLAT_OR_BASE")
	private String typeFlatOrBase; 
	
	@Column(name = "MATERIAL")
	private String material; 
	
	@Column(name = "SIZE_TWO")
	private String sizeTwo ; 
	
	@Column(name = "CONDUCTOR_LENGTH")
	private Integer conductorLength; 
	
	@Column(name = "CONDUCTOR_RESISTANCE")
	private Integer conductorResistance; 
	
	@Column(name = "MAX_ALLOWABLE_RESISTANCE")
	private String maxAllowableResistance ; 
	
	@Column(name = "REMARKS")
	private String remarks ; 
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	public Integer getEarthProductiveConductorID() {
		return earthProductiveConductorID;
	}

	public void setEarthProductiveConductorID(Integer earthProductiveConductorID) {
		this.earthProductiveConductorID = earthProductiveConductorID;
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

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getTypeFlatOrBase() {
		return typeFlatOrBase;
	}

	public void setTypeFlatOrBase(String typeFlatOrBase) {
		this.typeFlatOrBase = typeFlatOrBase;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getSizeTwo() {
		return sizeTwo;
	}

	public void setSizeTwo(String sizeTwo) {
		this.sizeTwo = sizeTwo;
	}

	public Integer getConductorLength() {
		return conductorLength;
	}

	public void setConductorLength(Integer conductorLength) {
		this.conductorLength = conductorLength;
	}

	public Integer getConductorResistance() {
		return conductorResistance;
	}

	public void setConductorResistance(Integer conductorResistance) {
		this.conductorResistance = conductorResistance;
	}

	public String getMaxAllowableResistance() {
		return maxAllowableResistance;
	}

	public void setMaxAllowableResistance(String maxAllowableResistance) {
		this.maxAllowableResistance = maxAllowableResistance;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getEarthProductiveCableID() {
		return earthProductiveCableID;
	}

	public void setEarthProductiveCableID(String earthProductiveCableID) {
		this.earthProductiveCableID = earthProductiveCableID;
	}

}
