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
@Table(name = "ins_mains_first_array")
public class MainsFirstArray implements Serializable{
	
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MAINS_FIRST_ID")
	private Integer mainsFirstId;
	
	@Column(name = "DE_EQUIPA")
	private String deEquipA;
	
	@Column(name = "DE_EQUIPB")
	private String deEquipB;
	
	@Column(name = "DE_EQUIPC")
	private String deEquipC;
	
	@Column(name = "DE_EQUIPD")
	private String deEquipD;
	
	@Column(name = "DE_EQUIPE")
	private String deEquipE;
	
	@Column(name = "DE_EQUIPF")
	private String deEquipF;
	
	@Column(name = "DE_EQUIPG")
	private String deEquipG;
	
	@Column(name = "DE_EQUIPH")
	private String deEquipH;
	
	@Column(name = "DE_EQUIPI")
	private String deEquipI;
	
	@Column(name = "WARNING_NOTEA")
	private String warningNoticeA;
	
	@Column(name = "WARNING_NOTEB")
	private String warningNoticeB;
	
	@Column(name = "WARNING_NOTEC")
	private String warningNoticeC;
	
	@Column(name = "WARNING_NOTED")
	private String warningNoticeD;
	
	@Column(name = "WARNING_NOTEE")
	private String warningNoticeE;
	
	@Column(name = "WARNING_NOTEF")
	private String warningNoticeF;
	
	@Column(name = "WARNING_NOTEG")
	private String warningNoticeG;
	
	@Column(name = "WARNING_NOTEH")
	private String warningNoticeH;
	
	@Column(name = "WARNING_NOTEI")
	private String warningNoticeI;
	
	@Column(name = "WARNING_NOTEJ")
	private String warningNoticeJ;
	
	@Column(name = "WARNING_NOTEK")
	private String warningNoticeK;
	
	@Column(name = "WARNING_NOTEL")
	private String warningNoticeL;
	
	@Column(name = "WARNING_NOTEM")
	private String warningNoticeM;
		
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
	
    // Circuit Equipment’s
    @Column(name = "CIRCUIT_EQUIPMENTA")
    private String circuitEquipmentA;
    
    @Column(name = "CIRCUIT_EQUIPMENTB")
    private String circuitEquipmentB;
    
    @Column(name = "CIRCUIT_EQUIPMENTC")
    private String circuitEquipmentC;
    
    @Column(name = "CIRCUIT_EQUIPMENTD")
    private String circuitEquipmentD;
    
    @Column(name = "CIRCUIT_EQUIPMENTE")
    private String circuitEquipmentE;
    
    @Column(name = "CIRCUIT_EQUIPMENTF")
    private String circuitEquipmentF;
    
    @Column(name = "CIRCUIT_EQUIPMENTG")
    private String circuitEquipmentG;
    
    @Column(name = "CIRCUIT_EQUIPMENTH")
    private String circuitEquipmentH;
    
    @Column(name = "CIRCUIT_EQUIPMENTI")
    private String circuitEquipmentI;
    
    @Column(name = "CIRCUIT_EQUIPMENTJ")
    private String circuitEquipmentJ;
    
    @Column(name = "CIRCUIT_EQUIPMENTK")
    private String circuitEquipmentK;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "IPAO_INSPECTION_ID")
    private IpaoInspection ipaoInspectionMainsFirst;

	public Integer getMainsFirstId() {
		return mainsFirstId;
	}

	public void setMainsFirstId(Integer mainsFirstId) {
		this.mainsFirstId = mainsFirstId;
	}

	public String getDeEquipA() {
		return deEquipA;
	}

	public void setDeEquipA(String deEquipA) {
		this.deEquipA = deEquipA;
	}

	public String getDeEquipB() {
		return deEquipB;
	}

	public void setDeEquipB(String deEquipB) {
		this.deEquipB = deEquipB;
	}

	public String getDeEquipC() {
		return deEquipC;
	}

	public void setDeEquipC(String deEquipC) {
		this.deEquipC = deEquipC;
	}

	public String getDeEquipD() {
		return deEquipD;
	}

	public void setDeEquipD(String deEquipD) {
		this.deEquipD = deEquipD;
	}

	public String getDeEquipE() {
		return deEquipE;
	}

	public void setDeEquipE(String deEquipE) {
		this.deEquipE = deEquipE;
	}

	public String getDeEquipF() {
		return deEquipF;
	}

	public void setDeEquipF(String deEquipF) {
		this.deEquipF = deEquipF;
	}

	public String getDeEquipG() {
		return deEquipG;
	}

	public void setDeEquipG(String deEquipG) {
		this.deEquipG = deEquipG;
	}

	public String getDeEquipH() {
		return deEquipH;
	}

	public void setDeEquipH(String deEquipH) {
		this.deEquipH = deEquipH;
	}

	public String getDeEquipI() {
		return deEquipI;
	}

	public void setDeEquipI(String deEquipI) {
		this.deEquipI = deEquipI;
	}

	
	public String getWarningNoticeA() {
		return warningNoticeA;
	}

	public void setWarningNoticeA(String warningNoticeA) {
		this.warningNoticeA = warningNoticeA;
	}

	public String getWarningNoticeB() {
		return warningNoticeB;
	}

	public void setWarningNoticeB(String warningNoticeB) {
		this.warningNoticeB = warningNoticeB;
	}

	public String getWarningNoticeC() {
		return warningNoticeC;
	}

	public void setWarningNoticeC(String warningNoticeC) {
		this.warningNoticeC = warningNoticeC;
	}

	public String getWarningNoticeD() {
		return warningNoticeD;
	}

	public void setWarningNoticeD(String warningNoticeD) {
		this.warningNoticeD = warningNoticeD;
	}

	public String getWarningNoticeE() {
		return warningNoticeE;
	}

	public void setWarningNoticeE(String warningNoticeE) {
		this.warningNoticeE = warningNoticeE;
	}

	public String getWarningNoticeF() {
		return warningNoticeF;
	}

	public void setWarningNoticeF(String warningNoticeF) {
		this.warningNoticeF = warningNoticeF;
	}

	public String getWarningNoticeG() {
		return warningNoticeG;
	}

	public void setWarningNoticeG(String warningNoticeG) {
		this.warningNoticeG = warningNoticeG;
	}

	public String getWarningNoticeH() {
		return warningNoticeH;
	}

	public void setWarningNoticeH(String warningNoticeH) {
		this.warningNoticeH = warningNoticeH;
	}

	public String getWarningNoticeI() {
		return warningNoticeI;
	}

	public void setWarningNoticeI(String warningNoticeI) {
		this.warningNoticeI = warningNoticeI;
	}

	public String getWarningNoticeJ() {
		return warningNoticeJ;
	}

	public void setWarningNoticeJ(String warningNoticeJ) {
		this.warningNoticeJ = warningNoticeJ;
	}

	public String getWarningNoticeK() {
		return warningNoticeK;
	}

	public void setWarningNoticeK(String warningNoticeK) {
		this.warningNoticeK = warningNoticeK;
	}

	public String getWarningNoticeL() {
		return warningNoticeL;
	}

	public void setWarningNoticeL(String warningNoticeL) {
		this.warningNoticeL = warningNoticeL;
	}

	public String getWarningNoticeM() {
		return warningNoticeM;
	}

	public void setWarningNoticeM(String warningNoticeM) {
		this.warningNoticeM = warningNoticeM;
	}

	public IpaoInspection getIpaoInspectionMainsFirst() {
		return ipaoInspectionMainsFirst;
	}

	public void setIpaoInspectionMainsFirst(IpaoInspection ipaoInspectionMainsFirst) {
		this.ipaoInspectionMainsFirst = ipaoInspectionMainsFirst;
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

	public String getCircuitEquipmentA() {
		return circuitEquipmentA;
	}

	public void setCircuitEquipmentA(String circuitEquipmentA) {
		this.circuitEquipmentA = circuitEquipmentA;
	}

	public String getCircuitEquipmentB() {
		return circuitEquipmentB;
	}

	public void setCircuitEquipmentB(String circuitEquipmentB) {
		this.circuitEquipmentB = circuitEquipmentB;
	}

	public String getCircuitEquipmentC() {
		return circuitEquipmentC;
	}

	public void setCircuitEquipmentC(String circuitEquipmentC) {
		this.circuitEquipmentC = circuitEquipmentC;
	}

	public String getCircuitEquipmentD() {
		return circuitEquipmentD;
	}

	public void setCircuitEquipmentD(String circuitEquipmentD) {
		this.circuitEquipmentD = circuitEquipmentD;
	}

	public String getCircuitEquipmentE() {
		return circuitEquipmentE;
	}

	public void setCircuitEquipmentE(String circuitEquipmentE) {
		this.circuitEquipmentE = circuitEquipmentE;
	}

	public String getCircuitEquipmentF() {
		return circuitEquipmentF;
	}

	public void setCircuitEquipmentF(String circuitEquipmentF) {
		this.circuitEquipmentF = circuitEquipmentF;
	}

	public String getCircuitEquipmentG() {
		return circuitEquipmentG;
	}

	public void setCircuitEquipmentG(String circuitEquipmentG) {
		this.circuitEquipmentG = circuitEquipmentG;
	}

	public String getCircuitEquipmentH() {
		return circuitEquipmentH;
	}

	public void setCircuitEquipmentH(String circuitEquipmentH) {
		this.circuitEquipmentH = circuitEquipmentH;
	}

	public String getCircuitEquipmentI() {
		return circuitEquipmentI;
	}

	public void setCircuitEquipmentI(String circuitEquipmentI) {
		this.circuitEquipmentI = circuitEquipmentI;
	}

	public String getCircuitEquipmentJ() {
		return circuitEquipmentJ;
	}

	public void setCircuitEquipmentJ(String circuitEquipmentJ) {
		this.circuitEquipmentJ = circuitEquipmentJ;
	}

	public String getCircuitEquipmentK() {
		return circuitEquipmentK;
	}

	public void setCircuitEquipmentK(String circuitEquipmentK) {
		this.circuitEquipmentK = circuitEquipmentK;
	}
    
}

