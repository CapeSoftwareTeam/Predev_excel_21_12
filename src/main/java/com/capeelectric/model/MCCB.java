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
@Table(name = "MCCB")

@NamedQueries(value = {
		@NamedQuery(name = "mccbRepository.findByFileNameAndNodeId", query = "select s from MCCB s where s.fileName=:fileName and s.nodeId=:nodeId")
		

})
public class MCCB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MCCB_ID")
	private Integer mccbID;
	
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
	
//	@Column(name = "RELAY_MANUFACTURERS")
//	private String relayManufacturer;
	
	@Column(name = "MODEL")
	private String model;
	
	@Column(name = "CURRENT_SETTING")
	private Integer currenSetting;
	
	@Column(name = "OVERCURRENT")
	private Integer overcurrent ;
	
	@Column(name = "SET_TIMES")
	private Integer setTimes;
	
	@Column(name = "EARTH_FAULT")
	private Integer EarthFault ;
	
	@Column(name = "SET_TIME")
	private Integer setTime ;
	
//	@Column(name = "OUTGOING_SIZE_PHASE")
//	private Integer outgoingSizePhase ;
//	
//	@Column(name = "OUTGOING_SIZE_NEUTRAL")
//	private Integer outgoingSizeNeutral ;
//	
//	@Column(name = "OUTGOING_SIZE_PROTECTIVE")
//	private Integer outgoingSizeProtective ;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "mccb", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GeneralTestingMCCB> generalTestingMCCB;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "mccb", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SafetyTestingMCCB> safetyTestingMCCB;

	public Integer getMccbID() {
		return mccbID;
	}

	public void setMccbID(Integer mccbID) {
		this.mccbID = mccbID;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getCurrenSetting() {
		return currenSetting;
	}

	public void setCurrenSetting(Integer currenSetting) {
		this.currenSetting = currenSetting;
	}

	public Integer getOvercurrent() {
		return overcurrent;
	}

	public void setOvercurrent(Integer overcurrent) {
		this.overcurrent = overcurrent;
	}

	public Integer getSetTimes() {
		return setTimes;
	}

	public void setSetTimes(Integer setTimes) {
		this.setTimes = setTimes;
	}

	public Integer getEarthFault() {
		return EarthFault;
	}

	public void setEarthFault(Integer earthFault) {
		EarthFault = earthFault;
	}

	public Integer getSetTime() {
		return setTime;
	}

	public void setSetTime(Integer setTime) {
		this.setTime = setTime;
	}

	public String getNoOfPoles() {
		return noOfPoles;
	}

	public void setNoOfPoles(String noOfPoles) {
		this.noOfPoles = noOfPoles;
	}


	public List<GeneralTestingMCCB> getGeneralTestingMCCB() {
		return generalTestingMCCB;
	}

	public void setGeneralTestingMCCB(List<GeneralTestingMCCB> generalTestingMCCB) {
		this.generalTestingMCCB = generalTestingMCCB;
	}

	public List<SafetyTestingMCCB> getSafetyTestingMCCB() {
		return safetyTestingMCCB;
	}

	public void setSafetyTestingMCCB(List<SafetyTestingMCCB> safetyTestingMCCB) {
		this.safetyTestingMCCB = safetyTestingMCCB;
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	
	
}
