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
@Table(name = "DISTRUBUTIONPANNEL_TABLE")
public class DistrubutionPannel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISTRIBUTION_ID")
	private Integer distrubutionPannelId; 

	@Column(name = "CB_WIRE_SIZE")
	private String cbWireSize;

	@Column(name = "CB_DESCRIPTION")
	private String cbDesc;

	@Column(name = "MATCHES_RECEPTABLE")
	private String matchesReceptable;

	@Column(name = "MATCHES_RECEPTABLE_DESC")
	private String matchesReceptableDesc;

	@Column(name = "INDIVDIAL_PEWIRE")
	private String indivdialPwire;

	@Column(name = "INDIVDIAL_PEWIRE_DESC")
	private String indivdialPwireDesc;

	@Column(name = "INDIVDIAL_NEUTRALWIRE")
	private String indivdialNeutralwire;

	@Column(name = "INDIVDIAL_NEUTRALWIRE_DES")
	private String indivdialNeutralwireDesc;

	@Column(name = "COMPUTER_LOAD_CIRCUTE")
	private String computerLoadCircute;

	@Column(name = "COMPUTERLOAD_CIRCUTE_DES")
	private String computerLoadCircuteDes;

	@Column(name = "COMPUTERLOAD_RECEPTABLE")
	private String computerLoadReceptable;

	@Column(name = "COMPUTERLOAD_RECEPTABLE_DESC")
	private String computerLoadReceptableDesc;

	@Column(name = "BRACH_CIRCUTE_RUN")
	private String branchCircuteRun;

	@Column(name = "BRACHCIRCUTE_RUN_DES")
	private String branchCircuteRunDesc;

	@Column(name = "FREQUENTLY_CYCLIDLOADS")
	private String frequencyCyclidLoads;

	@Column(name = "FREQUENTLY_CYCLIDLOADS_DES")
	private String frequencyCyclidLoadsDesc;

	@Column(name = "CONDUCTORS")
	private String conductors;

	@Column(name = "CONDUCTORS_DES")
	private String conductorsDesc;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "POWEREARTHINGDATA_ID")
	private PowerEarthingData powerEarthingData;

	public Integer getDistrubutionPannelId() {
		return distrubutionPannelId;
	}

	public void setDistrubutionPannelId(Integer distrubutionPannelId) {
		this.distrubutionPannelId = distrubutionPannelId;
	}

	public String getCbWireSize() {
		return cbWireSize;
	}

	public void setCbWireSize(String cbWireSize) {
		this.cbWireSize = cbWireSize;
	}

	public String getCbDesc() {
		return cbDesc;
	}

	public void setCbDesc(String cbDesc) {
		this.cbDesc = cbDesc;
	}

	public String getMatchesReceptable() {
		return matchesReceptable;
	}

	public void setMatchesReceptable(String matchesReceptable) {
		this.matchesReceptable = matchesReceptable;
	}

	public String getMatchesReceptableDesc() {
		return matchesReceptableDesc;
	}

	public void setMatchesReceptableDesc(String matchesReceptableDesc) {
		this.matchesReceptableDesc = matchesReceptableDesc;
	}

	public String getIndivdialPwire() {
		return indivdialPwire;
	}

	public void setIndivdialPwire(String indivdialPwire) {
		this.indivdialPwire = indivdialPwire;
	}

	public String getIndivdialPwireDesc() {
		return indivdialPwireDesc;
	}

	public void setIndivdialPwireDesc(String indivdialPwireDesc) {
		this.indivdialPwireDesc = indivdialPwireDesc;
	}

	public String getIndivdialNeutralwire() {
		return indivdialNeutralwire;
	}

	public void setIndivdialNeutralwire(String indivdialNeutralwire) {
		this.indivdialNeutralwire = indivdialNeutralwire;
	}

	public String getIndivdialNeutralwireDesc() {
		return indivdialNeutralwireDesc;
	}

	public void setIndivdialNeutralwireDesc(String indivdialNeutralwireDesc) {
		this.indivdialNeutralwireDesc = indivdialNeutralwireDesc;
	}

	public String getComputerLoadCircute() {
		return computerLoadCircute;
	}

	public void setComputerLoadCircute(String computerLoadCircute) {
		this.computerLoadCircute = computerLoadCircute;
	}

	public String getComputerLoadCircuteDes() {
		return computerLoadCircuteDes;
	}

	public void setComputerLoadCircuteDes(String computerLoadCircuteDes) {
		this.computerLoadCircuteDes = computerLoadCircuteDes;
	}

	public String getComputerLoadReceptable() {
		return computerLoadReceptable;
	}

	public void setComputerLoadReceptable(String computerLoadReceptable) {
		this.computerLoadReceptable = computerLoadReceptable;
	}

	public String getComputerLoadReceptableDesc() {
		return computerLoadReceptableDesc;
	}

	public void setComputerLoadReceptableDesc(String computerLoadReceptableDesc) {
		this.computerLoadReceptableDesc = computerLoadReceptableDesc;
	}

	public String getBranchCircuteRun() {
		return branchCircuteRun;
	}

	public void setBranchCircuteRun(String branchCircuteRun) {
		this.branchCircuteRun = branchCircuteRun;
	}

	public String getBranchCircuteRunDesc() {
		return branchCircuteRunDesc;
	}

	public void setBranchCircuteRunDesc(String branchCircuteRunDesc) {
		this.branchCircuteRunDesc = branchCircuteRunDesc;
	}

	public String getFrequencyCyclidLoads() {
		return frequencyCyclidLoads;
	}

	public void setFrequencyCyclidLoads(String frequencyCyclidLoads) {
		this.frequencyCyclidLoads = frequencyCyclidLoads;
	}

	public String getFrequencyCyclidLoadsDesc() {
		return frequencyCyclidLoadsDesc;
	}

	public void setFrequencyCyclidLoadsDesc(String frequencyCyclidLoadsDesc) {
		this.frequencyCyclidLoadsDesc = frequencyCyclidLoadsDesc;
	}

	public String getConductors() {
		return conductors;
	}

	public void setConductors(String conductors) {
		this.conductors = conductors;
	}

	public String getConductorsDesc() {
		return conductorsDesc;
	}

	public void setConductorsDesc(String conductorsDesc) {
		this.conductorsDesc = conductorsDesc;
	}

	public PowerEarthingData getPowerEarthingData() {
		return powerEarthingData;
	}

	public void setPowerEarthingData(PowerEarthingData powerEarthingData) {
		this.powerEarthingData = powerEarthingData;
	}

}
