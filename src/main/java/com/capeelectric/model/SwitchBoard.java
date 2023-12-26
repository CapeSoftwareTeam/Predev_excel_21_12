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
@Table(name = "SWITCH_BOARD")
public class SwitchBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SWITCH_BOARD_ID")
	private Integer switchBoardId;
	
	@Column(name = "NODE_ID")
	private String nodeId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "REFERENCE_NAME")
	private String referenceName;
	
	@Column(name = "VOLTAGE")
	private String voltage;
	
	@Column(name = "OVERVOLTAGE")
	private String overvoltage;
	
	@Column(name = "MAKE")
	private String make;
	
	@Column(name = "MODEL")
	private String model;
	
	@Column(name = "RATING")
	private Integer rating;
	
	@Column(name = "CLASSTYPE")
	private String classType;
	
	@Column(name = "SWITCH_BOARD_PHASE")
	private String switchBoardPhase;
	
	@Column(name = "SWITCH_BOARD_NEUTRAL")
	private String switchBoardNeutral;
	
	@Column(name = "SWITCH_BOARD_PROTECTIVE")
	private String switchBoardProtective;
	
	@Column(name = "FAULT_CURRENT")
	private Integer faultCurrent;
	
	@Column(name = "SHOCK_VOLTAGE")
	private Integer shockVoltage;
	
	@Column(name = "FLOOR_RESISTANCE")
	private Integer floorResistance;
	
	@Column(name = "WALL_RESISTANCE")
	private Integer wallResistance;
	
	@Column(name = "CABLE")
	private String cable;
	
	@Column(name = "PROTECTION_TYPE")
	private String protectionType;
	
	@Column(name = "HV_SYSTEM")
	private String hvSystem;
	
	@Column(name = "LV_SYSTEM")
	private String lvSystem;
	
	@Column(name = "SPD")
	private String spd;
	
	@Column(name = "NEUTRAL")
	private String neutral;
	
	@Column(name = "VERIFIED_PROTECTIVE")
	private String verifiedProtective;
	
	@Column(name = "VERIFIED_DOUBLE")
	private String verifiedDouble;
	
	@Column(name = "VERIFIED_ISOLATION")
	private String verifiedIsolation;
	
	@Column(name = "BOARD_REACH")
	private String boardReach;
	
	@Column(name = "ARMS_REACH")
	private String armsReach;
	
	@Column(name = "CAUTION_BARRIER")
	private String cautionBarriers;
	
	@Column(name = "SWITCH_BOARD_TEST")
	private String switchBoardTest;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	public Integer getSwitchBoardId() {
		return switchBoardId;
	}

	public void setSwitchBoardId(Integer switchBoardId) {
		this.switchBoardId = switchBoardId;
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

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getOvervoltage() {
		return overvoltage;
	}

	public void setOvervoltage(String overvoltage) {
		this.overvoltage = overvoltage;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getSwitchBoardPhase() {
		return switchBoardPhase;
	}

	public void setSwitchBoardPhase(String switchBoardPhase) {
		this.switchBoardPhase = switchBoardPhase;
	}

	public String getSwitchBoardNeutral() {
		return switchBoardNeutral;
	}

	public void setSwitchBoardNeutral(String switchBoardNeutral) {
		this.switchBoardNeutral = switchBoardNeutral;
	}

	public String getSwitchBoardProtective() {
		return switchBoardProtective;
	}

	public void setSwitchBoardProtective(String switchBoardProtective) {
		this.switchBoardProtective = switchBoardProtective;
	}

	public Integer getFaultCurrent() {
		return faultCurrent;
	}

	public void setFaultCurrent(Integer faultCurrent) {
		this.faultCurrent = faultCurrent;
	}

	public Integer getShockVoltage() {
		return shockVoltage;
	}

	public void setShockVoltage(Integer shockVoltage) {
		this.shockVoltage = shockVoltage;
	}

	public Integer getFloorResistance() {
		return floorResistance;
	}

	public void setFloorResistance(Integer floorResistance) {
		this.floorResistance = floorResistance;
	}

	public Integer getWallResistance() {
		return wallResistance;
	}

	public void setWallResistance(Integer wallResistance) {
		this.wallResistance = wallResistance;
	}

	public String getCable() {
		return cable;
	}

	public void setCable(String cable) {
		this.cable = cable;
	}

	public String getProtectionType() {
		return protectionType;
	}

	public void setProtectionType(String protectionType) {
		this.protectionType = protectionType;
	}

	
	public String getHvSystem() {
		return hvSystem;
	}

	public void setHvSystem(String hvSystem) {
		this.hvSystem = hvSystem;
	}

	public String getLvSystem() {
		return lvSystem;
	}

	public void setLvSystem(String lvSystem) {
		this.lvSystem = lvSystem;
	}

	public String getSpd() {
		return spd;
	}

	public void setSpd(String spd) {
		this.spd = spd;
	}

	public String getNeutral() {
		return neutral;
	}

	public void setNeutral(String neutral) {
		this.neutral = neutral;
	}

	public String getVerifiedProtective() {
		return verifiedProtective;
	}

	public void setVerifiedProtective(String verifiedProtective) {
		this.verifiedProtective = verifiedProtective;
	}

	public String getVerifiedDouble() {
		return verifiedDouble;
	}

	public void setVerifiedDouble(String verifiedDouble) {
		this.verifiedDouble = verifiedDouble;
	}

	public String getVerifiedIsolation() {
		return verifiedIsolation;
	}

	public void setVerifiedIsolation(String verifiedIsolation) {
		this.verifiedIsolation = verifiedIsolation;
	}

	public String getBoardReach() {
		return boardReach;
	}

	public void setBoardReach(String boardReach) {
		this.boardReach = boardReach;
	}

	public String getArmsReach() {
		return armsReach;
	}

	public void setArmsReach(String armsReach) {
		this.armsReach = armsReach;
	}

	public String getCautionBarriers() {
		return cautionBarriers;
	}

	public void setCautionBarriers(String cautionBarriers) {
		this.cautionBarriers = cautionBarriers;
	}

	public String getSwitchBoardTest() {
		return switchBoardTest;
	}

	public void setSwitchBoardTest(String switchBoardTest) {
		this.switchBoardTest = switchBoardTest;
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
