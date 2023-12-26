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
@Table(name = "MCB")

@NamedQueries(value = {
		@NamedQuery(name = "mcbRepository.findByFileNameAndNodeId", query = "select s from MCB s where s.fileName=:fileName and s.nodeId=:nodeId")
		

})
public class MCB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MCB_ID")
	private Integer mcbID;
	
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
	private String rating;
	
	@Column(name = "VOLTAGE")
	private String voltage;
	
	@Column(name = "NO_OF_POLES")
	private String noOfPoles;
	
	@Column(name = "CURRENT_CURVE")
	private String currentCurve;
	
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
	@OneToMany(mappedBy = "mcb", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GeneralTestingMCB> generalTestingMCB;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "mcb", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SafetyTestingMCB> safetyTestingMCB;

	public Integer getMcbID() {
		return mcbID;
	}

	public void setMcbID(Integer mcbID) {
		this.mcbID = mcbID;
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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

	
	public List<GeneralTestingMCB> getGeneralTestingMCB() {
		return generalTestingMCB;
	}

	public void setGeneralTestingMCB(List<GeneralTestingMCB> generalTestingMCB) {
		this.generalTestingMCB = generalTestingMCB;
	}

	public List<SafetyTestingMCB> getSafetyTestingMCB() {
		return safetyTestingMCB;
	}

	public void setSafetyTestingMCB(List<SafetyTestingMCB> safetyTestingMCB) {
		this.safetyTestingMCB = safetyTestingMCB;
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

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	
	
}
