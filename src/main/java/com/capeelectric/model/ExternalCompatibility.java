package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "EXTERNAL_COMPATIBILITY_TABLE")
public class ExternalCompatibility implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EXTERNAL_COMPATIBILITY_ID")
	private Integer externalCompatibilityId;

	@Column(name = "COMMUNICATION")
	private String communication;

	@Column(name = "VISIBILITY_OF_ANTENNAS")
	private String visibilityOfAntennas;

	@Column(name = "TYPES_OF_TRANSMISSION")
	private String typeOfTransmission;

	@Column(name = "TRANSMISSION_DESC")
	private String transmissionDesc;

	@Column(name = "ANTENNA_DISTANCE")
	private String antennaDistance;

	@Column(name = "NO_OF_WALLS")
	private String noOfWalls;

	@Column(name = "LOS_DESC")
	private String losDesc;

	@Column(name = "ELECTRONIC_SYSTEM_DISTANCE")
	private String electronicSystemDistance;

	@Column(name = "TRANSMITTER_POWER")
	private String transmitterPower;

	@Column(name = "FREQUENCY")
	private String frequency;

	@Column(name = "ORIENTATION_ANTENNA")
	private String orientationAntinna;

	@Column(name = "SYSTEM_SITE")
	private String systemSite;

	@Column(name = "SYSTEM_SITE_DESC")
	private String systemSiteDesc;

	@Column(name = "CONTROLLED_LOADS")
	private String controlledLoads;

	@Column(name = "CONTROLLED_LOADS_DESC")
	private String controlledLoadsDesc;

	@Column(name = "ELECTRIC_RAILWAY")
	private String electricRailway;

	@Column(name = "ELECTRIC_RAILWAY_DESC")
	private String electricRailwayDesc;

	@Column(name = "HV_TRANSMISSION")
	private String hvTransmission;

	@Column(name = "HV_TRANSMISSION_DESC")
	private String hvTransmissionDesc;

	@Column(name = "HP_AC_MAGNETS")
	private String hpAcMangets;

	@Column(name = "HP_AC_MAGNETS_DESC")
	private String hpAcMangetsDesc;

	@Column(name = "APPROXIMATE_DISTANCE")
	private String approximateDistance;

	@Column(name = "RFI_SURVEY")
	private String rfiSurvey;

	@Column(name = "NEW_RFI_SURVEY")
	private String newRfiSurvey;

	@Column(name = "NEW_RFI_SURVEY_DESC")
	private String newRfiSurveyDesc;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "ELECTROMAGNETIC_COMPATIBILITY_ID")
	private ElectromagneticCompatability electromagneticCompatability;

	public Integer getExternalCompatibilityId() {
		return externalCompatibilityId;
	}

	public void setExternalCompatibilityId(Integer externalCompatibilityId) {
		this.externalCompatibilityId = externalCompatibilityId;
	}

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public String getVisibilityOfAntennas() {
		return visibilityOfAntennas;
	}

	public void setVisibilityOfAntennas(String visibilityOfAntennas) {
		this.visibilityOfAntennas = visibilityOfAntennas;
	}

	public String getTypeOfTransmission() {
		return typeOfTransmission;
	}

	public void setTypeOfTransmission(String typeOfTransmission) {
		this.typeOfTransmission = typeOfTransmission;
	}

	public String getTransmissionDesc() {
		return transmissionDesc;
	}

	public void setTransmissionDesc(String transmissionDesc) {
		this.transmissionDesc = transmissionDesc;
	}

	public String getAntennaDistance() {
		return antennaDistance;
	}

	public void setAntennaDistance(String antennaDistance) {
		this.antennaDistance = antennaDistance;
	}

	public String getNoOfWalls() {
		return noOfWalls;
	}

	public void setNoOfWalls(String noOfWalls) {
		this.noOfWalls = noOfWalls;
	}

	public String getLosDesc() {
		return losDesc;
	}

	public void setLosDesc(String losDesc) {
		this.losDesc = losDesc;
	}

	public String getElectronicSystemDistance() {
		return electronicSystemDistance;
	}

	public void setElectronicSystemDistance(String electronicSystemDistance) {
		this.electronicSystemDistance = electronicSystemDistance;
	}

	public String getTransmitterPower() {
		return transmitterPower;
	}

	public void setTransmitterPower(String transmitterPower) {
		this.transmitterPower = transmitterPower;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getOrientationAntinna() {
		return orientationAntinna;
	}

	public void setOrientationAntinna(String orientationAntinna) {
		this.orientationAntinna = orientationAntinna;
	}

	public String getSystemSite() {
		return systemSite;
	}

	public void setSystemSite(String systemSite) {
		this.systemSite = systemSite;
	}

	public String getSystemSiteDesc() {
		return systemSiteDesc;
	}

	public void setSystemSiteDesc(String systemSiteDesc) {
		this.systemSiteDesc = systemSiteDesc;
	}

	public String getControlledLoads() {
		return controlledLoads;
	}

	public void setControlledLoads(String controlledLoads) {
		this.controlledLoads = controlledLoads;
	}

	public String getControlledLoadsDesc() {
		return controlledLoadsDesc;
	}

	public void setControlledLoadsDesc(String controlledLoadsDesc) {
		this.controlledLoadsDesc = controlledLoadsDesc;
	}

	public String getElectricRailway() {
		return electricRailway;
	}

	public void setElectricRailway(String electricRailway) {
		this.electricRailway = electricRailway;
	}

	public String getElectricRailwayDesc() {
		return electricRailwayDesc;
	}

	public void setElectricRailwayDesc(String electricRailwayDesc) {
		this.electricRailwayDesc = electricRailwayDesc;
	}

	public String getHvTransmission() {
		return hvTransmission;
	}

	public void setHvTransmission(String hvTransmission) {
		this.hvTransmission = hvTransmission;
	}

	public String getHvTransmissionDesc() {
		return hvTransmissionDesc;
	}

	public void setHvTransmissionDesc(String hvTransmissionDesc) {
		this.hvTransmissionDesc = hvTransmissionDesc;
	}

	public String getHpAcMangets() {
		return hpAcMangets;
	}

	public void setHpAcMangets(String hpAcMangets) {
		this.hpAcMangets = hpAcMangets;
	}

	public String getHpAcMangetsDesc() {
		return hpAcMangetsDesc;
	}

	public void setHpAcMangetsDesc(String hpAcMangetsDesc) {
		this.hpAcMangetsDesc = hpAcMangetsDesc;
	}

	public String getApproximateDistance() {
		return approximateDistance;
	}

	public void setApproximateDistance(String approximateDistance) {
		this.approximateDistance = approximateDistance;
	}

	public String getRfiSurvey() {
		return rfiSurvey;
	}

	public void setRfiSurvey(String rfiSurvey) {
		this.rfiSurvey = rfiSurvey;
	}

	public String getNewRfiSurvey() {
		return newRfiSurvey;
	}

	public void setNewRfiSurvey(String newRfiSurvey) {
		this.newRfiSurvey = newRfiSurvey;
	}

	public String getNewRfiSurveyDesc() {
		return newRfiSurveyDesc;
	}

	public void setNewRfiSurveyDesc(String newRfiSurveyDesc) {
		this.newRfiSurveyDesc = newRfiSurveyDesc;
	}

	public ElectromagneticCompatability getElectromagneticCompatability() {
		return electromagneticCompatability;
	}

	public void setElectromagneticCompatability(ElectromagneticCompatability electromagneticCompatability) {
		this.electromagneticCompatability = electromagneticCompatability;
	}

}
