package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "ins_circuit_table")
public class Circuit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CIRCUIT_ID")
	private Integer circuitId;
	
	@Column(name = "IDENTIFICATION_CONDUCTORS")
	private String identificationConductors;

	@Column(name = "CABLE_INSTALLATION")
	private String cableInstallation;

	@Column(name = "EXAMINATION_CABLES")
	private String examinationCables;

	@Column(name = "EXAMINATION_INSULATION")
	private String examinationInsulation;

	@Column(name = "NON_SHEATHED_CABLES")
	private String nonSheathedCables;

	@Column(name = "CONTAINMENT_SYSTEMS")
	private String containmentSystems;

	@Column(name = "TEMPERATURE_RATING")
	private String temperatureRating;

	@Column(name = "CABLES_TERMINATED")
	private String cablesTerminated;

	@Column(name = "CURRENT_CARRY_CAPACITY")
	private String currentCarryCapacity;

	@Column(name = "ADEQUACY_PROTECT_DEVICES")
	private String adequacyProtectDevices;

	@Column(name = "PRESENCE_PROTECT_CONDUCTORS")
	private String presenceProtectConductors;

	@Column(name = "CO_ORDINATION")
	private String coOrdination;

	@Column(name = "WIRING_SYSTEMS")
	private String wiringSystems;

	@Column(name = "CABLES_CONCEAL_UNDER_FLOORS")
	private String cablesConcealUnderFloors;

	@Column(name = "OPERATING_CURRENT_CIRCUITS")
	private String operatingCurrentCircuits;

	@Column(name = "OPERATING_CURRENT_SOCKET")
	private String operatingCurrentSocket;

	@Column(name = "CABLES_CONC_DEPTH")
	private String cablesConcDepth;

	@Column(name = "SECTIONS_REGARDLESS_DEPTH")
	private String sectionsRegardlessDepth;

	@Column(name = "PROVISION_FIRE_BARRIERS")
	private String provisionFireBarriers;

	@Column(name = "SEPARATION_BAND")
	private String separationBand;

	@Column(name = "SEPARATION_ELECTRICAL")
	private String separationElectrical;

	@Column(name = "CONDITION_CIRCUIT_ACCESSORIES")
	private String conditionCircuitAccessories;

	@Column(name = "CONDUCTOR_CORRECT_TERMINATED")
	private String conductorCorrectTerminated;

	@Column(name = "CONDUCTOR_VISIBLE_OUTSIDE")
	private String conductorVisibleOutside;

	@Column(name = "CONN_LIVE_CONDUCTORS")
	private String connLiveConductors;

	@Column(name = "ADEQUATELY_CONNECTED_ENCLOSURE")
	private String adequatelyConnectedEnclosure;

	@Column(name = "SUITABILITY_CIRCUIT_ACCESSORIES")
	private String suitabilityCircuitAccessories;

	@Column(name = "CONDITION_ACCESSORIES")
	private String conditionAccessories;

	@Column(name = "ADEQUACY_CONNECTIONS")
	private String adequacyConnections;

	@Column(name = "ISOLATION_AND_SWITCHING")
	private String isolationSwitching;
	
	@Column(name = "CIRCUIT_STATUS")
	private String circuitStatus;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "DB_PARENT_ID")
	private DbParentArray dbParentArray;
	
	@Column(name = "CABLESA")
	private String cablesA;
	
	@Column(name = "PERMANENTLY_CONNECTEDA")
	private String permanentlyConnectedA;
	
	@Column(name = "PERMANENTLY_CONNECTEDB")
	private String permanentlyConnectedB;
	
	@Column(name = "PERMANENTLY_CONNECTEDC")
	private String permanentlyConnectedC;
	
	@Column(name = "PERMANENTLY_CONNECTEDD")
	private String permanentlyConnectedD;
	
	@Column(name = "PERMANENTLY_CONNECTEDE")
	private String permanentlyConnectedE;
	
	@Column(name = "PERMANENTLY_CONNECTEDF")
	private String permanentlyConnectedF;
	
	@Column(name = "PERMANENTLY_CONNECTEDG")
	private String permanentlyConnectedG;
	
	@Column(name = "PERMANENTLY_CONNECTEDH")
	private String permanentlyConnectedH;
	
	@Column(name = "PERMANENTLY_CONNECTEDI")
	private String permanentlyConnectedI;
	
	@Column(name = "PERMANENTLY_CONNECTEDJ")
	private String permanentlyConnectedJ;

	public Integer getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(Integer circuitId) {
		this.circuitId = circuitId;
	}

	public String getIdentificationConductors() {
		return identificationConductors;
	}

	public void setIdentificationConductors(String identificationConductors) {
		this.identificationConductors = identificationConductors;
	}
	
	public String getCableInstallation() {
		return cableInstallation;
	}

	public void setCableInstallation(String cableInstallation) {
		this.cableInstallation = cableInstallation;
	}

	public String getExaminationCables() {
		return examinationCables;
	}

	public void setExaminationCables(String examinationCables) {
		this.examinationCables = examinationCables;
	}

	public String getExaminationInsulation() {
		return examinationInsulation;
	}

	public void setExaminationInsulation(String examinationInsulation) {
		this.examinationInsulation = examinationInsulation;
	}

	public String getNonSheathedCables() {
		return nonSheathedCables;
	}

	public void setNonSheathedCables(String nonSheathedCables) {
		this.nonSheathedCables = nonSheathedCables;
	}

	public String getContainmentSystems() {
		return containmentSystems;
	}

	public void setContainmentSystems(String containmentSystems) {
		this.containmentSystems = containmentSystems;
	}

	public String getTemperatureRating() {
		return temperatureRating;
	}

	public void setTemperatureRating(String temperatureRating) {
		this.temperatureRating = temperatureRating;
	}

	public String getCablesTerminated() {
		return cablesTerminated;
	}

	public void setCablesTerminated(String cablesTerminated) {
		this.cablesTerminated = cablesTerminated;
	}

	public String getCurrentCarryCapacity() {
		return currentCarryCapacity;
	}

	public void setCurrentCarryCapacity(String currentCarryCapacity) {
		this.currentCarryCapacity = currentCarryCapacity;
	}

	public String getAdequacyProtectDevices() {
		return adequacyProtectDevices;
	}

	public void setAdequacyProtectDevices(String adequacyProtectDevices) {
		this.adequacyProtectDevices = adequacyProtectDevices;
	}

	public String getPresenceProtectConductors() {
		return presenceProtectConductors;
	}

	public void setPresenceProtectConductors(String presenceProtectConductors) {
		this.presenceProtectConductors = presenceProtectConductors;
	}

	public String getCoOrdination() {
		return coOrdination;
	}

	public void setCoOrdination(String coOrdination) {
		this.coOrdination = coOrdination;
	}

	public String getWiringSystems() {
		return wiringSystems;
	}

	public void setWiringSystems(String wiringSystems) {
		this.wiringSystems = wiringSystems;
	}

	public String getCablesConcealUnderFloors() {
		return cablesConcealUnderFloors;
	}

	public void setCablesConcealUnderFloors(String cablesConcealUnderFloors) {
		this.cablesConcealUnderFloors = cablesConcealUnderFloors;
	}

	public String getOperatingCurrentCircuits() {
		return operatingCurrentCircuits;
	}

	public void setOperatingCurrentCircuits(String operatingCurrentCircuits) {
		this.operatingCurrentCircuits = operatingCurrentCircuits;
	}

	public String getOperatingCurrentSocket() {
		return operatingCurrentSocket;
	}

	public void setOperatingCurrentSocket(String operatingCurrentSocket) {
		this.operatingCurrentSocket = operatingCurrentSocket;
	}

	public String getCablesConcDepth() {
		return cablesConcDepth;
	}

	public void setCablesConcDepth(String cablesConcDepth) {
		this.cablesConcDepth = cablesConcDepth;
	}

	public String getSectionsRegardlessDepth() {
		return sectionsRegardlessDepth;
	}

	public void setSectionsRegardlessDepth(String sectionsRegardlessDepth) {
		this.sectionsRegardlessDepth = sectionsRegardlessDepth;
	}

	public String getProvisionFireBarriers() {
		return provisionFireBarriers;
	}

	public void setProvisionFireBarriers(String provisionFireBarriers) {
		this.provisionFireBarriers = provisionFireBarriers;
	}

	public String getSeparationBand() {
		return separationBand;
	}

	public void setSeparationBand(String separationBand) {
		this.separationBand = separationBand;
	}

	public String getSeparationElectrical() {
		return separationElectrical;
	}

	public void setSeparationElectrical(String separationElectrical) {
		this.separationElectrical = separationElectrical;
	}

	public String getConditionCircuitAccessories() {
		return conditionCircuitAccessories;
	}

	public void setConditionCircuitAccessories(String conditionCircuitAccessories) {
		this.conditionCircuitAccessories = conditionCircuitAccessories;
	}

	public String getConductorCorrectTerminated() {
		return conductorCorrectTerminated;
	}

	public void setConductorCorrectTerminated(String conductorCorrectTerminated) {
		this.conductorCorrectTerminated = conductorCorrectTerminated;
	}

	public String getConductorVisibleOutside() {
		return conductorVisibleOutside;
	}

	public void setConductorVisibleOutside(String conductorVisibleOutside) {
		this.conductorVisibleOutside = conductorVisibleOutside;
	}

	public String getConnLiveConductors() {
		return connLiveConductors;
	}

	public void setConnLiveConductors(String connLiveConductors) {
		this.connLiveConductors = connLiveConductors;
	}

	public String getAdequatelyConnectedEnclosure() {
		return adequatelyConnectedEnclosure;
	}

	public void setAdequatelyConnectedEnclosure(String adequatelyConnectedEnclosure) {
		this.adequatelyConnectedEnclosure = adequatelyConnectedEnclosure;
	}

	public String getSuitabilityCircuitAccessories() {
		return suitabilityCircuitAccessories;
	}

	public void setSuitabilityCircuitAccessories(String suitabilityCircuitAccessories) {
		this.suitabilityCircuitAccessories = suitabilityCircuitAccessories;
	}

	public String getConditionAccessories() {
		return conditionAccessories;
	}

	public void setConditionAccessories(String conditionAccessories) {
		this.conditionAccessories = conditionAccessories;
	}

	public String getAdequacyConnections() {
		return adequacyConnections;
	}

	public void setAdequacyConnections(String adequacyConnections) {
		this.adequacyConnections = adequacyConnections;
	}

	public String getIsolationSwitching() {
		return isolationSwitching;
	}

	public void setIsolationSwitching(String isolationSwitching) {
		this.isolationSwitching = isolationSwitching;
	}
	
	public DbParentArray getDbParentArray() {
		return dbParentArray;
	}

	public void setDbParentArray(DbParentArray dbParentArray) {
		this.dbParentArray = dbParentArray;
	}

	public String getCircuitStatus() {
		return circuitStatus;
	}

	public void setCircuitStatus(String circuitStatus) {
		this.circuitStatus = circuitStatus;
	}

	public String getCablesA() {
		return cablesA;
	}
	
	public void setCablesA(String cablesA) {
		this.cablesA = cablesA;
	}

	public String getPermanentlyConnectedA() {
		return permanentlyConnectedA;
	}

	public void setPermanentlyConnectedA(String permanentlyConnectedA) {
		this.permanentlyConnectedA = permanentlyConnectedA;
	}

	public String getPermanentlyConnectedB() {
		return permanentlyConnectedB;
	}

	public void setPermanentlyConnectedB(String permanentlyConnectedB) {
		this.permanentlyConnectedB = permanentlyConnectedB;
	}

	public String getPermanentlyConnectedC() {
		return permanentlyConnectedC;
	}

	public void setPermanentlyConnectedC(String permanentlyConnectedC) {
		this.permanentlyConnectedC = permanentlyConnectedC;
	}

	public String getPermanentlyConnectedD() {
		return permanentlyConnectedD;
	}

	public void setPermanentlyConnectedD(String permanentlyConnectedD) {
		this.permanentlyConnectedD = permanentlyConnectedD;
	}

	public String getPermanentlyConnectedE() {
		return permanentlyConnectedE;
	}

	public void setPermanentlyConnectedE(String permanentlyConnectedE) {
		this.permanentlyConnectedE = permanentlyConnectedE;
	}

	public String getPermanentlyConnectedF() {
		return permanentlyConnectedF;
	}

	public void setPermanentlyConnectedF(String permanentlyConnectedF) {
		this.permanentlyConnectedF = permanentlyConnectedF;
	}

	public String getPermanentlyConnectedG() {
		return permanentlyConnectedG;
	}

	public void setPermanentlyConnectedG(String permanentlyConnectedG) {
		this.permanentlyConnectedG = permanentlyConnectedG;
	}

	public String getPermanentlyConnectedH() {
		return permanentlyConnectedH;
	}

	public void setPermanentlyConnectedH(String permanentlyConnectedH) {
		this.permanentlyConnectedH = permanentlyConnectedH;
	}

	public String getPermanentlyConnectedI() {
		return permanentlyConnectedI;
	}

	public void setPermanentlyConnectedI(String permanentlyConnectedI) {
		this.permanentlyConnectedI = permanentlyConnectedI;
	}

	public String getPermanentlyConnectedJ() {
		return permanentlyConnectedJ;
	}

	public void setPermanentlyConnectedJ(String permanentlyConnectedJ) {
		this.permanentlyConnectedJ = permanentlyConnectedJ;
	}
	
}
