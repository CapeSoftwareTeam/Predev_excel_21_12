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
@Table(name = "ELECTRONICSYSTEM_TABLE")
public class ElectronicSystem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ELECTRONICSYSTEM_ID ")
	private Integer electronicSystemId;
		
	@Column(name = "BD_SLD")
	private String bDSld;
	
	@Column(name = "BD_RECORD_DATA")
	private String bDRecordData;
	
	@Column(name = "BD_EARTHING")
	private String bDEarthing;
		
	@Column(name = "ELECTRONIC_DESC")
	private String electronicDesc;
	
	@Column(name = "PANEL_ID")
	private Integer panelId;

	@Column(name = "NAMEPLATE_DATA")
	private String namePlateData;

	@Column(name = "MAIN_CIRCUTE_BRAKER")
	private String mainCircuteBraker;

	@Column(name = "MAINCIRCUTE_BRAKER_RATING")
	private String mainCircuteBrakerRating;

	@Column(name = "EMERGENCY_TRIP_REMOTE")
	private String emergencyTripRemote;

	@Column(name = "EMERGENCY_TRIP_LOCAL")
	private String emergencyTripLocal;

	@Column(name = "OHTHER_TRIP")
	private String otherTrip;

	@Column(name = "DIFFERENTAL_PROTECTION")
	private String differentalProtection;

	@Column(name = "BUODING_STELL")
	private String bouodingStell;

	@Column(name = "PANEL_FEED")
	private String panelFeed;

	@Column(name = "PHASE_WIRES")
	private String phaseWires;

	@Column(name = "NEUTRAL_WIRE")
	private String neutralWire;

	@Column(name = "PE_WIRE_SIZE")
	private String peWireSize;

	@Column(name = "PANNEL_CONNECTORS")
	private String pannelConnectors;

	@Column(name = "NEUTRAL_BUS")
	private String neutralBus;

	@Column(name = "EARTH_BUS")
	private String earthBus;

	@Column(name = "LIST_OF_NONELECTRONICLOAD")
	private String listOfNonElectronicLoad;

	@Column(name = "DEDICATED_ELECTRONIC_SYSTEM")
	private String dedicatedElectronicSystem;

	@Column(name = "NON_COMPUTER_LOADS")
	private String nonComputerLoads;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "POWEREARTHINGDATA_ID")
	private PowerEarthingData powerEarthingData;

	public Integer getElectronicSystemId() {
		return electronicSystemId;
	}

	public void setElectronicSystemId(Integer electronicSystemId) {
		this.electronicSystemId = electronicSystemId;
	}

	public String getbDSld() {
		return bDSld;
	}

	public void setbDSld(String bDSld) {
		this.bDSld = bDSld;
	}

	public String getbDRecordData() {
		return bDRecordData;
	}

	public void setbDRecordData(String bDRecordData) {
		this.bDRecordData = bDRecordData;
	}

	public String getbDEarthing() {
		return bDEarthing;
	}

	public void setbDEarthing(String bDEarthing) {
		this.bDEarthing = bDEarthing;
	}

	public String getElectronicDesc() {
		return electronicDesc;
	}

	public void setElectronicDesc(String electronicDesc) {
		this.electronicDesc = electronicDesc;
	}

	public Integer getPanelId() {
		return panelId;
	}

	public void setPanelId(Integer panelId) {
		this.panelId = panelId;
	}

	public String getNamePlateData() {
		return namePlateData;
	}

	public void setNamePlateData(String namePlateData) {
		this.namePlateData = namePlateData;
	}

	public String getMainCircuteBraker() {
		return mainCircuteBraker;
	}

	public void setMainCircuteBraker(String mainCircuteBraker) {
		this.mainCircuteBraker = mainCircuteBraker;
	}

	public String getMainCircuteBrakerRating() {
		return mainCircuteBrakerRating;
	}

	public void setMainCircuteBrakerRating(String mainCircuteBrakerRating) {
		this.mainCircuteBrakerRating = mainCircuteBrakerRating;
	}

	public String getEmergencyTripRemote() {
		return emergencyTripRemote;
	}

	public void setEmergencyTripRemote(String emergencyTripRemote) {
		this.emergencyTripRemote = emergencyTripRemote;
	}

	public String getEmergencyTripLocal() {
		return emergencyTripLocal;
	}

	public void setEmergencyTripLocal(String emergencyTripLocal) {
		this.emergencyTripLocal = emergencyTripLocal;
	}

	public String getOtherTrip() {
		return otherTrip;
	}

	public void setOtherTrip(String otherTrip) {
		this.otherTrip = otherTrip;
	}

	public String getDifferentalProtection() {
		return differentalProtection;
	}

	public void setDifferentalProtection(String differentalProtection) {
		this.differentalProtection = differentalProtection;
	}

	public String getBouodingStell() {
		return bouodingStell;
	}

	public void setBouodingStell(String bouodingStell) {
		this.bouodingStell = bouodingStell;
	}

	public String getPanelFeed() {
		return panelFeed;
	}

	public void setPanelFeed(String panelFeed) {
		this.panelFeed = panelFeed;
	}

	public String getPhaseWires() {
		return phaseWires;
	}

	public void setPhaseWires(String phaseWires) {
		this.phaseWires = phaseWires;
	}

	public String getNeutralWire() {
		return neutralWire;
	}

	public void setNeutralWire(String neutralWire) {
		this.neutralWire = neutralWire;
	}

	public String getPeWireSize() {
		return peWireSize;
	}

	public void setPeWireSize(String peWireSize) {
		this.peWireSize = peWireSize;
	}

	public String getPannelConnectors() {
		return pannelConnectors;
	}

	public void setPannelConnectors(String pannelConnectors) {
		this.pannelConnectors = pannelConnectors;
	}

	public String getNeutralBus() {
		return neutralBus;
	}

	public void setNeutralBus(String neutralBus) {
		this.neutralBus = neutralBus;
	}

	public String getEarthBus() {
		return earthBus;
	}

	public void setEarthBus(String earthBus) {
		this.earthBus = earthBus;
	}

	public String getListOfNonElectronicLoad() {
		return listOfNonElectronicLoad;
	}

	public void setListOfNonElectronicLoad(String listOfNonElectronicLoad) {
		this.listOfNonElectronicLoad = listOfNonElectronicLoad;
	}

	public String getDedicatedElectronicSystem() {
		return dedicatedElectronicSystem;
	}

	public void setDedicatedElectronicSystem(String dedicatedElectronicSystem) {
		this.dedicatedElectronicSystem = dedicatedElectronicSystem;
	}

	public String getNonComputerLoads() {
		return nonComputerLoads;
	}

	public void setNonComputerLoads(String nonComputerLoads) {
		this.nonComputerLoads = nonComputerLoads;
	}

	public PowerEarthingData getPowerEarthingData() {
		return powerEarthingData;
	}

	public void setPowerEarthingData(PowerEarthingData powerEarthingData) {
		this.powerEarthingData = powerEarthingData;
	}

}
