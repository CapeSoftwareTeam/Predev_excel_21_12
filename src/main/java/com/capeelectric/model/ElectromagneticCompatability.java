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
@Table(name = "ELECTROMAGNETIC_COMPATABILITY_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "ElectromagneticCompatabilityRepository.findByUserNameAndEmcId", query = "select r from ElectromagneticCompatability r where r.userName=:userName and r.emcId=:emcId"),
		@NamedQuery(name = "ElectromagneticCompatabilityRepository.findByEmcId", query = "select r from ElectromagneticCompatability r where r.emcId=:emcId")})
public class ElectromagneticCompatability implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ELECTROMAGNETIC_COMPATIBILITY_ID")
	private Integer electromagneticCompatabilityId;

	@Column(name = "SE_SINGLE_POINT")
	private String seSinglePoint;

	@Column(name = "SE_MESHED_ARRANGEMENT")
	private String seMeshedArrangment;

	@Column(name = "SE_DESC")
	private String seDescription;

	@Column(name = "EQUIPOTENTIAL_BONDING")
	private String equiptentialBonding;

	@Column(name = "RESISTANT_CABINET")
	private String resistanceCabinet;

	@Column(name = "RESISTANT_CABINET_DESC")
	private String resistanceCabinetDesc;

	@Column(name = "ROOM_SHIELD")
	private String roomShield;

	@Column(name = "ROOM_SHIELD_DESC")
	private String roomShieldDesc;

	@Column(name = "SHIELDING_OTHER_DESC")
	private String shieldingOtherDesc;

	@Column(name = "EQUIPMENT_HIGH_FREQUENCY")
	private String equipmentHighFrequency;

	@Column(name = "EQUIPMENT_HIGH_FREQUENCY_DESC")
	private String equipmentHighFrequencyDesc;

	@Column(name = "APPROXIMATE_DISTANCE")
	private String approximateDistance;

	@Column(name = "EQUIPMENT_MAINTENANCE")
	private String equipmentMaintence;

	@Column(name = "EQUIPMENT_MAINTENANCE_DESC")
	private String equipmentMaintenceDesc;

	@Column(name = "OPERATIONAL_FREQUENCY")
	private String operationFrequency;

	@Column(name = "RADIATED_POWER")
	private String radiatedPower;

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
	@OneToMany(mappedBy = "electromagneticCompatability", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ExternalCompatibility> externalCompatibility;

	public Integer getElectromagneticCompatabilityId() {
		return electromagneticCompatabilityId;
	}

	public void setElectromagneticCompatabilityId(Integer electromagneticCompatabilityId) {
		this.electromagneticCompatabilityId = electromagneticCompatabilityId;
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

	public String getSeSinglePoint() {
		return seSinglePoint;
	}

	public void setSeSinglePoint(String seSinglePoint) {
		this.seSinglePoint = seSinglePoint;
	}

	public String getSeMeshedArrangment() {
		return seMeshedArrangment;
	}

	public void setSeMeshedArrangment(String seMeshedArrangment) {
		this.seMeshedArrangment = seMeshedArrangment;
	}

	public String getSeDescription() {
		return seDescription;
	}

	public void setSeDescription(String seDescription) {
		this.seDescription = seDescription;
	}

	public String getEquiptentialBonding() {
		return equiptentialBonding;
	}

	public void setEquiptentialBonding(String equiptentialBonding) {
		this.equiptentialBonding = equiptentialBonding;
	}

	public String getResistanceCabinet() {
		return resistanceCabinet;
	}

	public void setResistanceCabinet(String resistanceCabinet) {
		this.resistanceCabinet = resistanceCabinet;
	}

	public String getResistanceCabinetDesc() {
		return resistanceCabinetDesc;
	}

	public void setResistanceCabinetDesc(String resistanceCabinetDesc) {
		this.resistanceCabinetDesc = resistanceCabinetDesc;
	}

	public String getRoomShield() {
		return roomShield;
	}

	public void setRoomShield(String roomShield) {
		this.roomShield = roomShield;
	}

	public String getRoomShieldDesc() {
		return roomShieldDesc;
	}

	public void setRoomShieldDesc(String roomShieldDesc) {
		this.roomShieldDesc = roomShieldDesc;
	}

	public String getShieldingOtherDesc() {
		return shieldingOtherDesc;
	}

	public void setShieldingOtherDesc(String shieldingOtherDesc) {
		this.shieldingOtherDesc = shieldingOtherDesc;
	}

	public String getEquipmentHighFrequency() {
		return equipmentHighFrequency;
	}

	public void setEquipmentHighFrequency(String equipmentHighFrequency) {
		this.equipmentHighFrequency = equipmentHighFrequency;
	}

	public String getEquipmentHighFrequencyDesc() {
		return equipmentHighFrequencyDesc;
	}

	public void setEquipmentHighFrequencyDesc(String equipmentHighFrequencyDesc) {
		this.equipmentHighFrequencyDesc = equipmentHighFrequencyDesc;
	}

	public String getApproximateDistance() {
		return approximateDistance;
	}

	public void setApproximateDistance(String approximateDistance) {
		this.approximateDistance = approximateDistance;
	}

	public String getEquipmentMaintence() {
		return equipmentMaintence;
	}

	public void setEquipmentMaintence(String equipmentMaintence) {
		this.equipmentMaintence = equipmentMaintence;
	}

	public String getEquipmentMaintenceDesc() {
		return equipmentMaintenceDesc;
	}

	public void setEquipmentMaintenceDesc(String equipmentMaintenceDesc) {
		this.equipmentMaintenceDesc = equipmentMaintenceDesc;
	}

	public String getOperationFrequency() {
		return operationFrequency;
	}

	public void setOperationFrequency(String operationFrequency) {
		this.operationFrequency = operationFrequency;
	}

	public String getRadiatedPower() {
		return radiatedPower;
	}

	public void setRadiatedPower(String radiatedPower) {
		this.radiatedPower = radiatedPower;
	}

	public List<ExternalCompatibility> getExternalCompatibility() {
		return externalCompatibility;
	}

	public void setExternalCompatibility(List<ExternalCompatibility> externalCompatibility) {
		this.externalCompatibility = externalCompatibility;
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
