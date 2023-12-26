package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "POWEREARTHINGDATA_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "PowerEarthingDataRepository.findByUserNameAndEmcId", query = "select r from PowerEarthingData r where r.userName=:userName and r.emcId=:emcId"),
		@NamedQuery(name = "PowerEarthingDataRepository.findByEmcId", query = "select r from PowerEarthingData r where r.emcId=:emcId")})
public class PowerEarthingData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POWEREARTHINGDATA_ID")
	private Integer powerEarthingDataId;

	@Column(name = "POWER_ELECTRICAL_UTILITY")
	private String powerElectricalUtility;

	@Column(name = "POWER_BACKUP_SOURCE")
	private String powerBackupSource;

	@Column(name = "POWER_DISTANCE_HVLV")
	private String powerDistanceHvLv;

	@Column(name = "POWER_CABLE_HVLV")
	private String powerCableHvLv;

	@Column(name = "POWER_DISC_SUPPLY")
	private String powerDiscSupply;

	@Column(name = "POWER_TRANSFORMATION_KVA")
	private String powerTransformationKVA;

//	@Column(name = "POWER_INPUT")
//	private String powerInput;

	@Column(name = "POWER_INPUT_VOLTS")
	private String powerInputVolts;

	@Column(name = "POWER_INPUT_PHASE")
	private String powerInputPhase;

	@Column(name = "POWER_INPUT_WIRES ")
	private String powerInputWires;

	@Column(name = "POWER_INPUT_FEED")
	private String powerInputFeed;

	@Column(name = "POWER_INPUT_DESCRIBE")
	private String powerInputDesc;

//	@Column(name = "POWER_OUTPUT")
//	private String powerOutput;

	@Column(name = "POWER_OUTPUT_VOLTS")
	private String powerOutputVolts;

	@Column(name = "POWER_OUTPUT_PHASE")
	private String powerOutputPhase;

	@Column(name = "POWER_OUTPUT_WIRES")
	private String powerOutputWires;

	@Column(name = "POWER_OUTPUT_FEED")
	private String powerOutputFeed;

	@Column(name = "POWER_OUTPUT_INCOMING_AMPS")
	private String powerIncomingAmps;

	@Column(name = "POWER_OUTPUT_NEUTRAL")
	private String powerNeutral;

	@Column(name = "PS_EARTHING")
	private String psEarthing;

	@Column(name = "PE_ATTACHEMENT")
	private String peAttachement;

//	@Column(name = "BS_ENTRANCE")
//	private String bsEntrance;

	@Column(name = "DEDICATED_TRANSFERMATION")
	private String dedicatedTransfermation;

	@Column(name = "DEDICATED_TRANSFERMATION_OTHERBUILDING ")
	private String dedicatedTransfermationOtherBuilding;

	@Column(name = "TYPEOF_INCOMING")
	private String typeOFIncoming;

	@Column(name = "DESCRIPTION_OF_SERVICE")
	private String descOfService;

	@Column(name = "DESCRIPTION_OFTESTING_SERVICE")
	private String descOfTestingService;

	@Column(name = "DESCRIPTION_OF_EQUIPOTENTIALBONDING")
	private String descOfEquipotentilaBonding;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "EMC_ID")
	private Integer emcId;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	@JsonManagedReference
	@OneToMany(mappedBy = "powerEarthingData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ElectronicSystem> electronicSystem;

	@JsonManagedReference
	@OneToMany(mappedBy = "powerEarthingData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DistrubutionPannel> distrubutionPannel;

	public Integer getPowerEarthingDataId() {
		return powerEarthingDataId;
	}

	public List<ElectronicSystem> getElectronicSystem() {
		return electronicSystem;
	}

	public void setElectronicSystem(List<ElectronicSystem> electronicSystem) {
		this.electronicSystem = electronicSystem;
	}

	public void setPowerEarthingDataId(Integer powerEarthingDataId) {
		this.powerEarthingDataId = powerEarthingDataId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getEmcId() {
		return emcId;
	}

	public void setEmcId(Integer emcId) {
		this.emcId = emcId;
	}

	public String getPowerElectricalUtility() {
		return powerElectricalUtility;
	}

	public void setPowerElectricalUtility(String powerElectricalUtility) {
		this.powerElectricalUtility = powerElectricalUtility;
	}

	public String getPowerBackupSource() {
		return powerBackupSource;
	}

	public void setPowerBackupSource(String powerBackupSource) {
		this.powerBackupSource = powerBackupSource;
	}

	public String getPowerDistanceHvLv() {
		return powerDistanceHvLv;
	}

	public void setPowerDistanceHvLv(String powerDistanceHvLv) {
		this.powerDistanceHvLv = powerDistanceHvLv;
	}

	public String getPowerCableHvLv() {
		return powerCableHvLv;
	}

	public void setPowerCableHvLv(String powerCableHvLv) {
		this.powerCableHvLv = powerCableHvLv;
	}

	public String getPowerDiscSupply() {
		return powerDiscSupply;
	}

	public void setPowerDiscSupply(String powerDiscSupply) {
		this.powerDiscSupply = powerDiscSupply;
	}

//	public String getPowerInput() {
//		return powerInput;
//	}
//
//	public void setPowerInput(String powerInput) {
//		this.powerInput = powerInput;
//	}

	public String getPowerTransformationKVA() {
		return powerTransformationKVA;
	}

	public void setPowerTransformationKVA(String powerTransformationKVA) {
		this.powerTransformationKVA = powerTransformationKVA;
	}

	public String getPowerInputVolts() {
		return powerInputVolts;
	}

	public void setPowerInputVolts(String powerInputVolts) {
		this.powerInputVolts = powerInputVolts;
	}

	public String getPowerInputPhase() {
		return powerInputPhase;
	}

	public void setPowerInputPhase(String powerInputPhase) {
		this.powerInputPhase = powerInputPhase;
	}

	public String getPowerInputWires() {
		return powerInputWires;
	}

	public void setPowerInputWires(String powerInputWires) {
		this.powerInputWires = powerInputWires;
	}

	public String getPowerInputFeed() {
		return powerInputFeed;
	}

	public void setPowerInputFeed(String powerInputFeed) {
		this.powerInputFeed = powerInputFeed;
	}

	public String getPowerInputDesc() {
		return powerInputDesc;
	}

	public void setPowerInputDesc(String powerInputDesc) {
		this.powerInputDesc = powerInputDesc;
	}

//	public String getPowerOutput() {
//		return powerOutput;
//	}
//
//	public void setPowerOutput(String powerOutput) {
//		this.powerOutput = powerOutput;
//	}

	public String getPowerOutputVolts() {
		return powerOutputVolts;
	}

	public void setPowerOutputVolts(String powerOutputVolts) {
		this.powerOutputVolts = powerOutputVolts;
	}

	public String getPowerOutputPhase() {
		return powerOutputPhase;
	}

	public void setPowerOutputPhase(String powerOutputPhase) {
		this.powerOutputPhase = powerOutputPhase;
	}

	public String getPowerOutputWires() {
		return powerOutputWires;
	}

	public void setPowerOutputWires(String powerOutputWires) {
		this.powerOutputWires = powerOutputWires;
	}

	public String getPowerOutputFeed() {
		return powerOutputFeed;
	}

	public void setPowerOutputFeed(String powerOutputFeed) {
		this.powerOutputFeed = powerOutputFeed;
	}

	public String getPsEarthing() {
		return psEarthing;
	}

	public void setPsEarthing(String psEarthing) {
		this.psEarthing = psEarthing;
	}

	public String getPeAttachement() {
		return peAttachement;
	}

	public void setPeAttachement(String peAttachement) {
		this.peAttachement = peAttachement;
	}

//	public String getBsEntrance() {
//		return bsEntrance;
//	}
//
//	public void setBsEntrance(String bsEntrance) {
//		this.bsEntrance = bsEntrance;
//	}

	public String getDedicatedTransfermation() {
		return dedicatedTransfermation;
	}

	public void setDedicatedTransfermation(String dedicatedTransfermation) {
		this.dedicatedTransfermation = dedicatedTransfermation;
	}

	public String getDedicatedTransfermationOtherBuilding() {
		return dedicatedTransfermationOtherBuilding;
	}

	public void setDedicatedTransfermationOtherBuilding(String dedicatedTransfermationOtherBuilding) {
		this.dedicatedTransfermationOtherBuilding = dedicatedTransfermationOtherBuilding;
	}

	public String getTypeOFIncoming() {
		return typeOFIncoming;
	}

	public void setTypeOFIncoming(String typeOFIncoming) {
		this.typeOFIncoming = typeOFIncoming;
	}

	public String getDescOfService() {
		return descOfService;
	}

	public void setDescOfService(String descOfService) {
		this.descOfService = descOfService;
	}

	public String getDescOfTestingService() {
		return descOfTestingService;
	}

	public void setDescOfTestingService(String descOfTestingService) {
		this.descOfTestingService = descOfTestingService;
	}

	public String getDescOfEquipotentilaBonding() {
		return descOfEquipotentilaBonding;
	}

	public void setDescOfEquipotentilaBonding(String descOfEquipotentilaBonding) {
		this.descOfEquipotentilaBonding = descOfEquipotentilaBonding;
	}

	public List<DistrubutionPannel> getDistrubutionPannel() {
		return distrubutionPannel;
	}

	public void setDistrubutionPannel(List<DistrubutionPannel> distrubutionPannel) {
		this.distrubutionPannel = distrubutionPannel;
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

	public String getPowerIncomingAmps() {
		return powerIncomingAmps;
	}

	public void setPowerIncomingAmps(String powerIncomingAmps) {
		this.powerIncomingAmps = powerIncomingAmps;
	}

	public String getPowerNeutral() {
		return powerNeutral;
	}

	public void setPowerNeutral(String powerNeutral) {
		this.powerNeutral = powerNeutral;
	}

}
