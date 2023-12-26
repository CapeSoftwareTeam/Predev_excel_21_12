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

import javassist.SerialVersionUID;

@Entity
@Table(name = "ins_mains_second_array")
public class MainsSecondArray implements Serializable{
	
	public static final long SerialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MAINS_SECOND_ID")
	private Integer mainsSecondId;
	
    @Column(name = "CABLES_CONTROLSA")
    private String cablesContolsA;
    
    @Column(name = "CABLES_CONTROLSB")
    private String cablesContolsB;
    
    @Column(name = "CABLES_CONTROLSC")
    private String cablesContolsC;
    
    @Column(name = "CABLES_CONTROLSD")
    private String cablesContolsD;
    
    @Column(name = "CABLES_CONTROLSE")
    private String cablesContolsE;
    
    @Column(name = "CABLES_CONTROLSF")
    private String cablesContolsF;
    
    @Column(name = "CABLES_CONTROLSG")
    private String cablesContolsG;
    
    @Column(name = "CABLES_CONTROLSH")
    private String cablesContolsH;
    
    @Column(name = "CABLES_CONTROLSI")
    private String cablesContolsI;
    
    @Column(name = "CABLES_CONTROLSJ")
    private String cablesContolsJ;
        
    @Column(name = "ISOLATORS_CONTROLSA")
    private String isolatorsControlsA;

    @Column(name = "ISOLATORS_CONTROLSB")
    private String isolatorsControlsB;
    
    @Column(name = "ISOLATORS_CONTROLSC")
    private String isolatorsControlsC;
    
    @Column(name = "ISOLATORS_CONTROLSD")
    private String isolatorsControlsD;
    
    @Column(name = "ISOLATORS_CONTROLSE")
    private String isolatorsControlsE;
    
    @Column(name = "ISOLATORS_CONTROLSF")
    private String isolatorsControlsF;
    
    @Column(name = "ISOLATORS_CONTROLSG")
    private String isolatorsControlsG;
    
    @Column(name = "SWITCHING_DEVICESA")
    private String switchingDevicesA;
    
    @Column(name = "SWITCHING_DEVICESB")
    private String switchingDevicesB;
    
    @Column(name = "SWITCHING_DEVICESC")
    private String switchingDevicesC;
    
    @Column(name = "SWITCHING_DEVICESD")
    private String switchingDevicesD;
    
    @Column(name = "SWITCHING_DEVICESE")
    private String switchingDevicesE;
    
    @Column(name = "SWITCHING_DEVICESF")
    private String switchingDevicesF;
    
    @Column(name = "SWITCHING_DEVICESG")
    private String switchingDevicesG;
    
    @Column(name = "SWITCHING_DEVICESH")
    private String switchingDevicesH;
    
    @Column(name = "SWITCHING_DEVICESI")
    private String switchingDevicesI;
    
    @Column(name = "SWITCHING_DEVICESJ")
    private String switchingDevicesJ;
    
    @Column(name = "SWITCHING_DEVICESK")
    private String switchingDevicesK;
    
    @Column(name = "SWITCHING_DEVICESL")
    private String switchingDevicesL;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "IPAO_INSPECTION_ID")
    private IpaoInspection ipaoInspectionMainsSecond;

	public Integer getMainsSecondId() {
		return mainsSecondId;
	}

	public void setMainsSecondId(Integer mainsSecondId) {
		this.mainsSecondId = mainsSecondId;
	}

	public String getCablesContolsA() {
		return cablesContolsA;
	}

	public void setCablesContolsA(String cablesContolsA) {
		this.cablesContolsA = cablesContolsA;
	}

	public String getCablesContolsB() {
		return cablesContolsB;
	}

	public void setCablesContolsB(String cablesContolsB) {
		this.cablesContolsB = cablesContolsB;
	}

	public String getCablesContolsC() {
		return cablesContolsC;
	}

	public void setCablesContolsC(String cablesContolsC) {
		this.cablesContolsC = cablesContolsC;
	}

	public String getCablesContolsD() {
		return cablesContolsD;
	}

	public void setCablesContolsD(String cablesContolsD) {
		this.cablesContolsD = cablesContolsD;
	}

	public String getCablesContolsE() {
		return cablesContolsE;
	}

	public void setCablesContolsE(String cablesContolsE) {
		this.cablesContolsE = cablesContolsE;
	}

	public String getCablesContolsF() {
		return cablesContolsF;
	}

	public void setCablesContolsF(String cablesContolsF) {
		this.cablesContolsF = cablesContolsF;
	}

	public String getCablesContolsG() {
		return cablesContolsG;
	}

	public void setCablesContolsG(String cablesContolsG) {
		this.cablesContolsG = cablesContolsG;
	}

	public String getCablesContolsH() {
		return cablesContolsH;
	}

	public void setCablesContolsH(String cablesContolsH) {
		this.cablesContolsH = cablesContolsH;
	}

	public String getCablesContolsI() {
		return cablesContolsI;
	}

	public void setCablesContolsI(String cablesContolsI) {
		this.cablesContolsI = cablesContolsI;
	}

	public String getCablesContolsJ() {
		return cablesContolsJ;
	}

	public void setCablesContolsJ(String cablesContolsJ) {
		this.cablesContolsJ = cablesContolsJ;
	}

	public String getIsolatorsControlsA() {
		return isolatorsControlsA;
	}

	public void setIsolatorsControlsA(String isolatorsControlsA) {
		this.isolatorsControlsA = isolatorsControlsA;
	}

	public String getIsolatorsControlsB() {
		return isolatorsControlsB;
	}

	public void setIsolatorsControlsB(String isolatorsControlsB) {
		this.isolatorsControlsB = isolatorsControlsB;
	}

	public String getIsolatorsControlsC() {
		return isolatorsControlsC;
	}

	public void setIsolatorsControlsC(String isolatorsControlsC) {
		this.isolatorsControlsC = isolatorsControlsC;
	}

	public String getIsolatorsControlsD() {
		return isolatorsControlsD;
	}

	public void setIsolatorsControlsD(String isolatorsControlsD) {
		this.isolatorsControlsD = isolatorsControlsD;
	}

	public String getIsolatorsControlsE() {
		return isolatorsControlsE;
	}

	public void setIsolatorsControlsE(String isolatorsControlsE) {
		this.isolatorsControlsE = isolatorsControlsE;
	}

	public String getIsolatorsControlsF() {
		return isolatorsControlsF;
	}

	public void setIsolatorsControlsF(String isolatorsControlsF) {
		this.isolatorsControlsF = isolatorsControlsF;
	}

	public String getIsolatorsControlsG() {
		return isolatorsControlsG;
	}

	public void setIsolatorsControlsG(String isolatorsControlsG) {
		this.isolatorsControlsG = isolatorsControlsG;
	}

	public String getSwitchingDevicesA() {
		return switchingDevicesA;
	}

	public void setSwitchingDevicesA(String switchingDevicesA) {
		this.switchingDevicesA = switchingDevicesA;
	}

	public String getSwitchingDevicesB() {
		return switchingDevicesB;
	}

	public void setSwitchingDevicesB(String switchingDevicesB) {
		this.switchingDevicesB = switchingDevicesB;
	}

	public String getSwitchingDevicesC() {
		return switchingDevicesC;
	}

	public void setSwitchingDevicesC(String switchingDevicesC) {
		this.switchingDevicesC = switchingDevicesC;
	}

	public String getSwitchingDevicesD() {
		return switchingDevicesD;
	}

	public void setSwitchingDevicesD(String switchingDevicesD) {
		this.switchingDevicesD = switchingDevicesD;
	}

	public String getSwitchingDevicesE() {
		return switchingDevicesE;
	}

	public void setSwitchingDevicesE(String switchingDevicesE) {
		this.switchingDevicesE = switchingDevicesE;
	}

	public String getSwitchingDevicesF() {
		return switchingDevicesF;
	}

	public void setSwitchingDevicesF(String switchingDevicesF) {
		this.switchingDevicesF = switchingDevicesF;
	}

	public String getSwitchingDevicesG() {
		return switchingDevicesG;
	}

	public void setSwitchingDevicesG(String switchingDevicesG) {
		this.switchingDevicesG = switchingDevicesG;
	}

	public String getSwitchingDevicesH() {
		return switchingDevicesH;
	}

	public void setSwitchingDevicesH(String switchingDevicesH) {
		this.switchingDevicesH = switchingDevicesH;
	}

	public String getSwitchingDevicesI() {
		return switchingDevicesI;
	}

	public void setSwitchingDevicesI(String switchingDevicesI) {
		this.switchingDevicesI = switchingDevicesI;
	}

	public String getSwitchingDevicesJ() {
		return switchingDevicesJ;
	}

	public void setSwitchingDevicesJ(String switchingDevicesJ) {
		this.switchingDevicesJ = switchingDevicesJ;
	}

	public String getSwitchingDevicesK() {
		return switchingDevicesK;
	}

	public void setSwitchingDevicesK(String switchingDevicesK) {
		this.switchingDevicesK = switchingDevicesK;
	}

	public String getSwitchingDevicesL() {
		return switchingDevicesL;
	}

	public void setSwitchingDevicesL(String switchingDevicesL) {
		this.switchingDevicesL = switchingDevicesL;
	}

	public IpaoInspection getIpaoInspectionMainsSecond() {
		return ipaoInspectionMainsSecond;
	}

	public void setIpaoInspectionMainsSecond(IpaoInspection ipaoInspectionMainsSecond) {
		this.ipaoInspectionMainsSecond = ipaoInspectionMainsSecond;
	}
    
}
