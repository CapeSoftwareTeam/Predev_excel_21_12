/**
 * 
 */
package com.capeelectric.model;

import java.time.LocalDateTime;

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
@Table(name = "protectiveEarthConductor")
public class ProtectiveEarthConductor{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SAFETY_TESTING_PEC_ID")
	private Integer safetyTestingPECId;
	
	@Column(name = "CABLECONNECTOR_ID")
	private String cableConnectorId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "MAKE")
	private String make;
		
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "MATERIAL")
	private String material;
	
	@Column(name = "SIZE")
	private String size;
	
	@Column(name = "CONDUCTOR_LENGTH")
	private String conductorLength;
	
	@Column(name = "CONDUCTOR_RESISTANCE")
	private String conductorResistance;
	
	@Column(name = "MAX_RESISTANCE")
	private String maxResistance;
		
	@Column(name = "REMARKS")
	private String rNRemarks;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	public Integer getSafetyTestingPECId() {
		return safetyTestingPECId;
	}

	public void setSafetyTestingPECId(Integer safetyTestingPECId) {
		this.safetyTestingPECId = safetyTestingPECId;
	}

	public String getCableConnectorId() {
		return cableConnectorId;
	}

	public void setCableConnectorId(String cableConnectorId) {
		this.cableConnectorId = cableConnectorId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getConductorLength() {
		return conductorLength;
	}

	public void setConductorLength(String conductorLength) {
		this.conductorLength = conductorLength;
	}

	public String getConductorResistance() {
		return conductorResistance;
	}

	public void setConductorResistance(String conductorResistance) {
		this.conductorResistance = conductorResistance;
	}

	public String getMaxResistance() {
		return maxResistance;
	}

	public void setMaxResistance(String maxResistance) {
		this.maxResistance = maxResistance;
	}

	public String getrNRemarks() {
		return rNRemarks;
	}

	public void setrNRemarks(String rNRemarks) {
		this.rNRemarks = rNRemarks;
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
