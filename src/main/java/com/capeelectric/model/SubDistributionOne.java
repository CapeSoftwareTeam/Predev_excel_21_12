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
@Table(name = "ins_sub_distribution_one")
public class SubDistributionOne implements Serializable{
	
	private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUB_DISTRIBUTION_ID")
	private Integer subDistributionOneId;

    @Column(name = "CIRCUIT_CONDUCTORSA")
    private String circuitConductorsA;
    
    @Column(name = "CIRCUIT_CONDUCTORSB")
    private String circuitConductorsB;
    
    @Column(name = "CIRCUIT_CONDUCTORSC")
    private String circuitConductorsC;
    
    @Column(name = "CIRCUIT_CONDUCTORSD")
    private String circuitConductorsD;
    
    @Column(name = "CIRCUIT_CONDUCTORSE")
    private String circuitConductorsE;
    
    @Column(name = "CIRCUIT_CONDUCTORSF")
    private String circuitConductorsF;
    
    @Column(name = "CIRCUIT_CONDUCTORSG")
    private String circuitConductorsG;
    
    @Column(name = "CIRCUIT_CONDUCTORSH")
    private String circuitConductorsH;
    
    @Column(name = "CIRCUIT_CONDUCTORSI")
    private String circuitConductorsI;
    
    @Column(name = "CIRCUIT_CONDUCTORSJ")
    private String circuitConductorsJ;
    
    @Column(name = "CIRCUIT_CONDUCTORSK")
    private String circuitConductorsK;
    
    @Column(name = "CIRCUIT_EQUIPMENTSA")
    private String circuitEquipmentsA;
    
    @Column(name = "CIRCUIT_EQUIPMENTSB")
    private String circuitEquipmentsB;
    
    @Column(name = "CIRCUIT_EQUIPMENTSC")
    private String circuitEquipmentsC;
    
    @Column(name = "CIRCUIT_EQUIPMENTSD")
    private String circuitEquipmentsD;
    
    @Column(name = "CIRCUIT_EQUIPMENTSE")
    private String circuitEquipmentsE;
    
    @Column(name = "CIRCUIT_EQUIPMENTSF")
    private String circuitEquipmentsF;
    
    @Column(name = "CIRCUIT_EQUIPMENTSG")
    private String circuitEquipmentsG;
    
    @Column(name = "CIRCUIT_EQUIPMENTSH")
    private String circuitEquipmentsH;
    
    @Column(name = "CIRCUIT_EQUIPMENTSI")
    private String circuitEquipmentsI;
    
	@Column(name = "SUB_CABLESA")
	private String subCablesA;
	
	@Column(name = "SUB_CABLESB")
	private String subCablesB;
	
	@Column(name = "SUB_CABLESC")
	private String subCablesC;

	@Column(name = "SUB_CABLESD")
	private String subCablesD;
	
	@Column(name = "SUB_CABLESE")
	private String subCablesE;
	
	@Column(name = "SUB_CABLESF")
	private String subCablesF;
	
	@Column(name = "SUB_CABLESG")
	private String subCablesG;
	
	@Column(name = "SUB_CABLESH")
	private String subCablesH;
	
	@Column(name = "SUB_CABLESI")
	private String subCablesI;
	
	@Column(name = "SUB_CABLESJ")
	private String subCablesJ;
	
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
	
	@Column(name = "PERMANENTLY_CONNECTEDK")
	private String permanentlyConnectedK;
	
	@Column(name = "PERMANENTLY_CONNECTEDL")
	private String permanentlyConnectedL;
	
	@Column(name = "PERMANENTLY_CONNECTEDM")
	private String permanentlyConnectedM;
	
	@Column(name = "PERMANENTLY_CONNECTEDN")
	private String permanentlyConnectedN;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SUB_DB_PARENT_ID")
	private SubDbParent subDbParentSubOne;

	public Integer getSubDistributionOneId() {
		return subDistributionOneId;
	}

	public void setSubDistributionOneId(Integer subDistributionOneId) {
		this.subDistributionOneId = subDistributionOneId;
	}

	public String getCircuitConductorsA() {
		return circuitConductorsA;
	}

	public void setCircuitConductorsA(String circuitConductorsA) {
		this.circuitConductorsA = circuitConductorsA;
	}

	public String getCircuitConductorsB() {
		return circuitConductorsB;
	}

	public void setCircuitConductorsB(String circuitConductorsB) {
		this.circuitConductorsB = circuitConductorsB;
	}

	public String getCircuitConductorsC() {
		return circuitConductorsC;
	}

	public void setCircuitConductorsC(String circuitConductorsC) {
		this.circuitConductorsC = circuitConductorsC;
	}

	public String getCircuitConductorsD() {
		return circuitConductorsD;
	}

	public void setCircuitConductorsD(String circuitConductorsD) {
		this.circuitConductorsD = circuitConductorsD;
	}

	public String getCircuitConductorsE() {
		return circuitConductorsE;
	}

	public void setCircuitConductorsE(String circuitConductorsE) {
		this.circuitConductorsE = circuitConductorsE;
	}

	public String getCircuitConductorsF() {
		return circuitConductorsF;
	}

	public void setCircuitConductorsF(String circuitConductorsF) {
		this.circuitConductorsF = circuitConductorsF;
	}

	public String getCircuitConductorsG() {
		return circuitConductorsG;
	}

	public void setCircuitConductorsG(String circuitConductorsG) {
		this.circuitConductorsG = circuitConductorsG;
	}

	public String getCircuitConductorsH() {
		return circuitConductorsH;
	}

	public void setCircuitConductorsH(String circuitConductorsH) {
		this.circuitConductorsH = circuitConductorsH;
	}

	public String getCircuitConductorsI() {
		return circuitConductorsI;
	}

	public void setCircuitConductorsI(String circuitConductorsI) {
		this.circuitConductorsI = circuitConductorsI;
	}

	public String getCircuitConductorsJ() {
		return circuitConductorsJ;
	}

	public void setCircuitConductorsJ(String circuitConductorsJ) {
		this.circuitConductorsJ = circuitConductorsJ;
	}

	public String getCircuitConductorsK() {
		return circuitConductorsK;
	}

	public void setCircuitConductorsK(String circuitConductorsK) {
		this.circuitConductorsK = circuitConductorsK;
	}

	public String getCircuitEquipmentsA() {
		return circuitEquipmentsA;
	}

	public void setCircuitEquipmentsA(String circuitEquipmentsA) {
		this.circuitEquipmentsA = circuitEquipmentsA;
	}

	public String getCircuitEquipmentsB() {
		return circuitEquipmentsB;
	}

	public void setCircuitEquipmentsB(String circuitEquipmentsB) {
		this.circuitEquipmentsB = circuitEquipmentsB;
	}

	public String getCircuitEquipmentsC() {
		return circuitEquipmentsC;
	}

	public void setCircuitEquipmentsC(String circuitEquipmentsC) {
		this.circuitEquipmentsC = circuitEquipmentsC;
	}

	public String getCircuitEquipmentsD() {
		return circuitEquipmentsD;
	}

	public void setCircuitEquipmentsD(String circuitEquipmentsD) {
		this.circuitEquipmentsD = circuitEquipmentsD;
	}

	public String getCircuitEquipmentsE() {
		return circuitEquipmentsE;
	}

	public void setCircuitEquipmentsE(String circuitEquipmentsE) {
		this.circuitEquipmentsE = circuitEquipmentsE;
	}

	public String getCircuitEquipmentsF() {
		return circuitEquipmentsF;
	}

	public void setCircuitEquipmentsF(String circuitEquipmentsF) {
		this.circuitEquipmentsF = circuitEquipmentsF;
	}

	public String getCircuitEquipmentsG() {
		return circuitEquipmentsG;
	}

	public void setCircuitEquipmentsG(String circuitEquipmentsG) {
		this.circuitEquipmentsG = circuitEquipmentsG;
	}

	public String getCircuitEquipmentsH() {
		return circuitEquipmentsH;
	}

	public void setCircuitEquipmentsH(String circuitEquipmentsH) {
		this.circuitEquipmentsH = circuitEquipmentsH;
	}

	public String getCircuitEquipmentsI() {
		return circuitEquipmentsI;
	}

	public void setCircuitEquipmentsI(String circuitEquipmentsI) {
		this.circuitEquipmentsI = circuitEquipmentsI;
	}

	public String getSubCablesA() {
		return subCablesA;
	}

	public void setSubCablesA(String subCablesA) {
		this.subCablesA = subCablesA;
	}

	public String getSubCablesB() {
		return subCablesB;
	}

	public void setSubCablesB(String subCablesB) {
		this.subCablesB = subCablesB;
	}

	public String getSubCablesC() {
		return subCablesC;
	}

	public void setSubCablesC(String subCablesC) {
		this.subCablesC = subCablesC;
	}

	public String getSubCablesD() {
		return subCablesD;
	}

	public void setSubCablesD(String subCablesD) {
		this.subCablesD = subCablesD;
	}

	public String getSubCablesE() {
		return subCablesE;
	}

	public void setSubCablesE(String subCablesE) {
		this.subCablesE = subCablesE;
	}

	public String getSubCablesF() {
		return subCablesF;
	}

	public void setSubCablesF(String subCablesF) {
		this.subCablesF = subCablesF;
	}

	public String getSubCablesG() {
		return subCablesG;
	}

	public void setSubCablesG(String subCablesG) {
		this.subCablesG = subCablesG;
	}

	public String getSubCablesH() {
		return subCablesH;
	}

	public void setSubCablesH(String subCablesH) {
		this.subCablesH = subCablesH;
	}

	public String getSubCablesI() {
		return subCablesI;
	}

	public void setSubCablesI(String subCablesI) {
		this.subCablesI = subCablesI;
	}

	public String getSubCablesJ() {
		return subCablesJ;
	}

	public void setSubCablesJ(String subCablesJ) {
		this.subCablesJ = subCablesJ;
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

	public String getPermanentlyConnectedK() {
		return permanentlyConnectedK;
	}

	public void setPermanentlyConnectedK(String permanentlyConnectedK) {
		this.permanentlyConnectedK = permanentlyConnectedK;
	}

	public String getPermanentlyConnectedL() {
		return permanentlyConnectedL;
	}

	public void setPermanentlyConnectedL(String permanentlyConnectedL) {
		this.permanentlyConnectedL = permanentlyConnectedL;
	}

	public String getPermanentlyConnectedM() {
		return permanentlyConnectedM;
	}

	public void setPermanentlyConnectedM(String permanentlyConnectedM) {
		this.permanentlyConnectedM = permanentlyConnectedM;
	}

	public String getPermanentlyConnectedN() {
		return permanentlyConnectedN;
	}

	public void setPermanentlyConnectedN(String permanentlyConnectedN) {
		this.permanentlyConnectedN = permanentlyConnectedN;
	}

	public SubDbParent getSubDbParentSubOne() {
		return subDbParentSubOne;
	}

	public void setSubDbParentSubOne(SubDbParent subDbParentSubOne) {
		this.subDbParentSubOne = subDbParentSubOne;
	}

}
