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
@Table(name = "RCBO")
public class RCBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RCBO_ID")
	private Integer rcboId;
	
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
	
	@Column(name = "RATING")
	private Integer rating;
	
	@Column(name = "VOLTAGE")
	private Integer voltage;
	
	@Column(name = "NO_OF_POLES")
	private String noOfPoles;
	
	@Column(name = "CURRENT_CURVE")
	private String currentCurve;
	
	@Column(name = "RESIDUAL_CURRENT_TYPE")
	private String residualCurrentType;

	@Column(name = "RESIDUAL_CURRENT")
	private Integer residualCurrent;
	
//	@Column(name = "OUTGOING_SIZE_PHASE")
//	private Integer outgoingSizePhase;
//	
//	@Column(name = "OUTGOING_SIZE_NEUTRAL")
//	private Integer outgoingSizeNeutral;
//	
//	@Column(name = "OUTGOING_SIZE_PROTECTIVE")
//	private Integer outgoingSizeProtective;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "rcbo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GeneralTestingRCBO> generalTestingRCBO;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "rcbo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SafetyTestingRCBO> safetyTestingRCBO;

	public Integer getRcboId() {
		return rcboId;
	}

	public void setRcboId(Integer rcboId) {
		this.rcboId = rcboId;
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

	public String getNoOfPoles() {
		return noOfPoles;
	}

	public void setNoOfPoles(String noOfPoles) {
		this.noOfPoles = noOfPoles;
	}

	public String getCurrentCurve() {
		return currentCurve;
	}

	public void setCurrentCurve(String currentCurve) {
		this.currentCurve = currentCurve;
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

	public Integer getResidualCurrent() {
		return residualCurrent;
	}

	public void setResidualCurrent(Integer residualCurrent) {
		this.residualCurrent = residualCurrent;
	}

//	public Integer getOutgoingSizePhase() {
//		return outgoingSizePhase;
//	}
//
//	public void setOutgoingSizePhase(Integer outgoingSizePhase) {
//		this.outgoingSizePhase = outgoingSizePhase;
//	}
//
//	public Integer getOutgoingSizeNeutral() {
//		return outgoingSizeNeutral;
//	}
//
//	public void setOutgoingSizeNeutral(Integer outgoingSizeNeutral) {
//		this.outgoingSizeNeutral = outgoingSizeNeutral;
//	}
//
//	public Integer getOutgoingSizeProtective() {
//		return outgoingSizeProtective;
//	}
//
//	public void setOutgoingSizeProtective(Integer outgoingSizeProtective) {
//		this.outgoingSizeProtective = outgoingSizeProtective;
//	}

	public List<GeneralTestingRCBO> getGeneralTestingRCBO() {
		return generalTestingRCBO;
	}

	public void setGeneralTestingRCBO(List<GeneralTestingRCBO> generalTestingRCBO) {
		this.generalTestingRCBO = generalTestingRCBO;
	}

	public List<SafetyTestingRCBO> getSafetyTestingRCBO() {
		return safetyTestingRCBO;
	}

	public void setSafetyTestingRCBO(List<SafetyTestingRCBO> safetyTestingRCBO) {
		this.safetyTestingRCBO = safetyTestingRCBO;
	}

	public String getResidualCurrentType() {
		return residualCurrentType;
	}

	public void setResidualCurrentType(String residualCurrentType) {
		this.residualCurrentType = residualCurrentType;
	}

}
