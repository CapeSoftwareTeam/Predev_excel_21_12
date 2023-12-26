package com.capeelectric.model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ins_sub_db_parent")
public class SubDbParent implements Serializable{


	private static final long serialVersionUID= 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUB_DB_PARENT_ID")
	private Integer subDbParentID;
	
	@Column(name = "SUB_DISTRIBUTION_BOARD")
	private String subDistributionBoard;
	
	@Column(name = "SUB_DISTRIBUTION_LOCATION")
	private String subDistributionLocation;
	
	@Column(name = "SUB_DISTRIBUTION_SOURCE")
	private String subDistributionSource;
	
	@Column(name = "SUB_DB_FLAG")
	private String subDbParentFlag;
	
	@Column(name = "SUB_DB_PROGRESS")
	private String subDBProgress;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "IPAO_INSPECTION_ID")
	private IpaoInspection ipaoInspectionSubDB;

	@JsonManagedReference
	@OneToMany(mappedBy = "subDbParentSub", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SubDistribution> subDistribution;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "subDbParentSubOne", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SubDistributionOne> subDistributionOne;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "subDbParentObsC", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ObsFormArrayC> obsFormArrayC;

	public String getSubDistributionBoard() {
		return subDistributionBoard;
	}

	public void setSubDistributionBoard(String subDistributionBoard) {
		this.subDistributionBoard = subDistributionBoard;
	}

	public String getSubDistributionLocation() {
		return subDistributionLocation;
	}

	public void setSubDistributionLocation(String subDistributionLocation) {
		this.subDistributionLocation = subDistributionLocation;
	}

	public String getSubDistributionSource() {
		return subDistributionSource;
	}

	public void setSubDistributionSource(String subDistributionSource) {
		this.subDistributionSource = subDistributionSource;
	}

	public IpaoInspection getIpaoInspectionSubDB() {
		return ipaoInspectionSubDB;
	}

	public void setIpaoInspectionSubDB(IpaoInspection ipaoInspectionSubDB) {
		this.ipaoInspectionSubDB = ipaoInspectionSubDB;
	}

	public Integer getSubDbParentID() {
		return subDbParentID;
	}

	public void setSubDbParentID(Integer subDbParentID) {
		this.subDbParentID = subDbParentID;
	}

	public List<SubDistribution> getSubDistribution() {
		return subDistribution;
	}

	public void setSubDistribution(List<SubDistribution> subDistribution) {
		this.subDistribution = subDistribution;
	}

	public List<SubDistributionOne> getSubDistributionOne() {
		return subDistributionOne;
	}

	public void setSubDistributionOne(List<SubDistributionOne> subDistributionOne) {
		this.subDistributionOne = subDistributionOne;
	}

	public List<ObsFormArrayC> getObsFormArrayC() {
		return obsFormArrayC;
	}

	public void setObsFormArrayC(List<ObsFormArrayC> obsFormArrayC) {
		this.obsFormArrayC = obsFormArrayC;
	}

	public String getSubDbParentFlag() {
		return subDbParentFlag;
	}

	public void setSubDbParentFlag(String subDbParentFlag) {
		this.subDbParentFlag = subDbParentFlag;
	}

	public String getSubDBProgress() {
		return subDBProgress;
	}

	public void setSubDBProgress(String subDBProgress) {
		this.subDBProgress = subDBProgress;
	}
	
}
