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
@Table(name = "ACB")

@NamedQueries(value = {
		@NamedQuery(name = "acbRepository.findByFileNameAndNodeId", query = "select s from ACB s where s.fileName=:fileName and s.nodeId=:nodeId")
		

})
public class ACB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACB_ID")
	private Integer acbID;
	
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
	
	@Column(name = "OCPD_Type")
	private String ocpdType;
	
	@Column(name = "RATING")
	private Integer rating;
	
	@Column(name = "VOLTAGE")
	private String voltage;
	
	@Column(name = "NO_OF_POLES")
	private String noOfPoles;
	
	@Column(name = "RELAY_MANUFACTURERS")
	private String relayManufacturer;
	
	@Column(name = "MODEL")
	private String model;
	
	@Column(name = "OVERCURRENT")
	private Integer overcurrent ;
	
	@Column(name = "SET_TIMES")
	private Integer setTimes;
	
	@Column(name = "EARTH_FAULT")
	private Integer EarthFault ;
	
	@Column(name = "SET_TIME")
	private Integer setTime ;
	
	@Column(name = "OVERVOLTAGE")
	private String overvoltage ;
	
	@Column(name = "SETVOLTAGE")
	private Integer setVoltage ;
	
	@Column(name = "SETT_TIME")
	private Integer settTime ;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "acb", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GeneralTestingACB> generalTestingACB;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "acb", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SafetyTestingACB> safetyTestingACB;

	public Integer getAcbID() {
		return acbID;
	}

	public void setMccbID(Integer acbID) {
		this.acbID = acbID;
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

	public String getOcpdType() {
		return ocpdType;
	}

	public void setOcpdType(String ocpdType) {
		this.ocpdType = ocpdType;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getNoOfPoles() {
		return noOfPoles;
	}

	public void setNoOfPoles(String noOfPoles) {
		this.noOfPoles = noOfPoles;
	}

	public String getRelayManufacturer() {
		return relayManufacturer;
	}

	public void setRelayManufacturer(String relayManufacturer) {
		this.relayManufacturer = relayManufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public String getOvervoltage() {
		return overvoltage;
	}

	public void setOvervoltage(String overvoltage) {
		this.overvoltage = overvoltage;
	}

	public Integer getSetVoltage() {
		return setVoltage;
	}

	public void setSetVoltage(Integer setVoltage) {
		this.setVoltage = setVoltage;
	}

	public Integer getSettTime() {
		return settTime;
	}

	public void setSettTime(Integer settTime) {
		this.settTime = settTime;
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

	public List<GeneralTestingACB> getGeneralTestingACB() {
		return generalTestingACB;
	}

	public void setGeneralTestingACB(List<GeneralTestingACB> generalTestingACB) {
		this.generalTestingACB = generalTestingACB;
	}

	public List<SafetyTestingACB> getSafetyTestingACB() {
		return safetyTestingACB;
	}

	public void setSafetyTestingACB(List<SafetyTestingACB> safetyTestingACB) {
		this.safetyTestingACB = safetyTestingACB;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	
}
