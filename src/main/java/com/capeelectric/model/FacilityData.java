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
@Table(name = "FACILITYDATA_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "FacilityDataRepository.findByUserNameAndEmcId", query = "select r from FacilityData r where r.userName=:userName and r.emcId=:emcId"),
		@NamedQuery(name = "FacilityDataRepository.findByEmcId", query = "select r from FacilityData r where r.emcId=:emcId") })
public class FacilityData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FACILITYDATA_ID")
	private Integer facilityDataId;

	@Column(name = "BL_TYPE")
	private String blType;

	@Column(name = "EMC_ID")
	private Integer emcId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "BL_OTHER_DESC")
	private String blOtherDescription;

	@Column(name = "BC_TYPE")
	private String bcType;

	@Column(name = "BC_NO_FLOORS")
	private String bcNoOfFloors;

	@Column(name = "BC_ROOM_FLOOR_LOCATION")
	private String bcRoomFloorLocation;

	@Column(name = "BC_PRIMARY_USE")
	private String bcBuildingPrimaryUse;

	@Column(name = "BC_OTHER_USES")
	private String bcOtherUses;

	@Column(name = "RL_INTERIOR_ROOM")
	private String rlInteriorRoom;

	@Column(name = "RL_EXTERIOR_ROOM")
	private String rlExteriorRoom;

	@Column(name = "RL_SOLID_EXTERIOR")
	private String rlSolidExterior;

	@Column(name = "RL_WINDOWED_EXTERIOR")
	private String rlWindowedExterior;

	@Column(name = "RL_WINDOWS_FACE")
	private String rlWindsFace;

	@Column(name = "RU_DEDICATED")
	private String ruDedicated;

//	@Column(name = "RU_NON_DEDICATED")
//	private String ruNonDedicated;

	@Column(name = "RU_OTHER_DESC")
	private String ruOtherDesc;

	@Column(name = "RM_HEIGHT_TF")
	private String rmHeightTrueFloor;

	@Column(name = "RM_HEIGHT_FF")
	private String rmHeightFalseFloor;

	@Column(name = "RM_WIDTH")
	private String rmWidth;

	@Column(name = "RM_LENGTH")
	private String rmLength;

	@Column(name = "RM_MAX_FLOOR")
	private String rmMaxFloor;

	@Column(name = "FT_RAISED_FLOOR")
	private String ftRaisedFloor;

	@Column(name = "FT_AIR_SUPPLY")
	private String ftAirSupply;

	@Column(name = "FT_HEIGHT")
	private String ftHeight;

	@Column(name = "FT_AIRFLOW_OBSTRUCTION")
	private String ftAirFlowObservation;

	@Column(name = "FT_DESCRIPTION")
	private String ftDescription;

	@Column(name = "FT_AIRGRILL_DAMPERS")
	private String ftAirGrillDampers;

	@Column(name = "FT_CABLE_HOLE")
	private String ftCableHole;

	@Column(name = "FT_PEDESTALS")
	private String ftPedestals;

	@Column(name = "FT_GRIDS")
	private String ftGrids;

	@Column(name = "FT_BOLTED")
	private String ftBolted;

	@Column(name = "FT_WELDED")
	private String ftWelded;

	@Column(name = "FT_EARTHING_DESCRIPTION")
	private String ftEarthingDesc;

	@Column(name = "FT_TRUEFLOOR_MATERIAL")
	private String ftTrueFloorMaterial;

	@Column(name = "FT_DESCRIBE")
	private String ftDescribe;

	@Column(name = "FT_CLEANLINESS")
	private String ftCleanliness;

	@Column(name = "FT_OTHER_DESCRIPTION")
	private String ftOtherDescription;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	@JsonManagedReference
	@OneToMany(mappedBy = "facilityData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FloorCovering> floorCovering;

	public Integer getFacilityDataId() {
		return facilityDataId;
	}

	public void setFacilityDataId(Integer facilityDataId) {
		this.facilityDataId = facilityDataId;
	}

	public Integer getEmcId() {
		return emcId;
	}

	public void setEmcId(Integer emcId) {
		this.emcId = emcId;
	}

	public String getBlType() {
		return blType;
	}

	public void setBlType(String blType) {
		this.blType = blType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBlOtherDescription() {
		return blOtherDescription;
	}

	public void setBlOtherDescription(String blOtherDescription) {
		this.blOtherDescription = blOtherDescription;
	}

	public String getBcType() {
		return bcType;
	}

	public void setBcType(String bcType) {
		this.bcType = bcType;
	}

	public String getBcNoOfFloors() {
		return bcNoOfFloors;
	}

	public void setBcNoOfFloors(String bcNoOfFloors) {
		this.bcNoOfFloors = bcNoOfFloors;
	}

	public String getBcRoomFloorLocation() {
		return bcRoomFloorLocation;
	}

	public void setBcRoomFloorLocation(String bcRoomFloorLocation) {
		this.bcRoomFloorLocation = bcRoomFloorLocation;
	}

	public String getBcBuildingPrimaryUse() {
		return bcBuildingPrimaryUse;
	}

	public void setBcBuildingPrimaryUse(String bcBuildingPrimaryUse) {
		this.bcBuildingPrimaryUse = bcBuildingPrimaryUse;
	}

	public String getBcOtherUses() {
		return bcOtherUses;
	}

	public void setBcOtherUses(String bcOtherUses) {
		this.bcOtherUses = bcOtherUses;
	}

	public String getRlInteriorRoom() {
		return rlInteriorRoom;
	}

	public void setRlInteriorRoom(String rlInteriorRoom) {
		this.rlInteriorRoom = rlInteriorRoom;
	}

	public String getRlExteriorRoom() {
		return rlExteriorRoom;
	}

	public void setRlExteriorRoom(String rlExteriorRoom) {
		this.rlExteriorRoom = rlExteriorRoom;
	}

	public String getRlSolidExterior() {
		return rlSolidExterior;
	}

	public void setRlSolidExterior(String rlSolidExterior) {
		this.rlSolidExterior = rlSolidExterior;
	}

	public String getRlWindowedExterior() {
		return rlWindowedExterior;
	}

	public void setRlWindowedExterior(String rlWindowedExterior) {
		this.rlWindowedExterior = rlWindowedExterior;
	}

	public String getRlWindsFace() {
		return rlWindsFace;
	}

	public void setRlWindsFace(String rlWindsFace) {
		this.rlWindsFace = rlWindsFace;
	}

	public String getRuDedicated() {
		return ruDedicated;
	}

	public void setRuDedicated(String ruDedicated) {
		this.ruDedicated = ruDedicated;
	}

//	public String getRuNonDedicated() {
//		return ruNonDedicated;
//	}
//
//	public void setRuNonDedicated(String ruNonDedicated) {
//		this.ruNonDedicated = ruNonDedicated;
//	}

	public String getRuOtherDesc() {
		return ruOtherDesc;
	}

	public void setRuOtherDesc(String ruOtherDesc) {
		this.ruOtherDesc = ruOtherDesc;
	}

	public String getRmHeightTrueFloor() {
		return rmHeightTrueFloor;
	}

	public void setRmHeightTrueFloor(String rmHeightTrueFloor) {
		this.rmHeightTrueFloor = rmHeightTrueFloor;
	}

	public String getRmHeightFalseFloor() {
		return rmHeightFalseFloor;
	}

	public void setRmHeightFalseFloor(String rmHeightFalseFloor) {
		this.rmHeightFalseFloor = rmHeightFalseFloor;
	}

	public String getRmWidth() {
		return rmWidth;
	}

	public void setRmWidth(String rmWidth) {
		this.rmWidth = rmWidth;
	}

	public String getRmLength() {
		return rmLength;
	}

	public void setRmLength(String rmLength) {
		this.rmLength = rmLength;
	}

	public String getRmMaxFloor() {
		return rmMaxFloor;
	}

	public void setRmMaxFloor(String rmMaxFloor) {
		this.rmMaxFloor = rmMaxFloor;
	}

	public String getFtRaisedFloor() {
		return ftRaisedFloor;
	}

	public void setFtRaisedFloor(String ftRaisedFloor) {
		this.ftRaisedFloor = ftRaisedFloor;
	}

	public String getFtAirSupply() {
		return ftAirSupply;
	}

	public void setFtAirSupply(String ftAirSupply) {
		this.ftAirSupply = ftAirSupply;
	}

	public String getFtHeight() {
		return ftHeight;
	}

	public void setFtHeight(String ftHeight) {
		this.ftHeight = ftHeight;
	}

	public String getFtAirFlowObservation() {
		return ftAirFlowObservation;
	}

	public void setFtAirFlowObservation(String ftAirFlowObservation) {
		this.ftAirFlowObservation = ftAirFlowObservation;
	}

	public String getFtDescription() {
		return ftDescription;
	}

	public void setFtDescription(String ftDescription) {
		this.ftDescription = ftDescription;
	}

	public String getFtAirGrillDampers() {
		return ftAirGrillDampers;
	}

	public void setFtAirGrillDampers(String ftAirGrillDampers) {
		this.ftAirGrillDampers = ftAirGrillDampers;
	}

	public String getFtCableHole() {
		return ftCableHole;
	}

	public void setFtCableHole(String ftCableHole) {
		this.ftCableHole = ftCableHole;
	}

	public String getFtPedestals() {
		return ftPedestals;
	}

	public void setFtPedestals(String ftPedestals) {
		this.ftPedestals = ftPedestals;
	}

	public String getFtGrids() {
		return ftGrids;
	}

	public void setFtGrids(String ftGrids) {
		this.ftGrids = ftGrids;
	}

	public String getFtBolted() {
		return ftBolted;
	}

	public void setFtBolted(String ftBolted) {
		this.ftBolted = ftBolted;
	}

	public String getFtWelded() {
		return ftWelded;
	}

	public void setFtWelded(String ftWelded) {
		this.ftWelded = ftWelded;
	}

	public String getFtEarthingDesc() {
		return ftEarthingDesc;
	}

	public void setFtEarthingDesc(String ftEarthingDesc) {
		this.ftEarthingDesc = ftEarthingDesc;
	}

	public String getFtTrueFloorMaterial() {
		return ftTrueFloorMaterial;
	}

	public void setFtTrueFloorMaterial(String ftTrueFloorMaterial) {
		this.ftTrueFloorMaterial = ftTrueFloorMaterial;
	}

	public String getFtDescribe() {
		return ftDescribe;
	}

	public void setFtDescribe(String ftDescribe) {
		this.ftDescribe = ftDescribe;
	}

	public String getFtCleanliness() {
		return ftCleanliness;
	}

	public void setFtCleanliness(String ftCleanliness) {
		this.ftCleanliness = ftCleanliness;
	}

	public String getFtOtherDescription() {
		return ftOtherDescription;
	}

	public void setFtOtherDescription(String ftOtherDescription) {
		this.ftOtherDescription = ftOtherDescription;
	}

	public List<FloorCovering> getFloorCovering() {
		return floorCovering;
	}

	public void setFloorCovering(List<FloorCovering> floorCovering) {
		this.floorCovering = floorCovering;
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
