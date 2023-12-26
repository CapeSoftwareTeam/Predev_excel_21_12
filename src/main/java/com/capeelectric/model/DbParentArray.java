package com.capeelectric.model;

import java.io.Serializable;
import java.util.List;

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

import javassist.SerialVersionUID;

@Entity
@Table(name = "ins_db_parent_array")
public class DbParentArray implements Serializable{

	private static final long SerialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DB_PARENT_ID")
	private Integer dbParentID;
	
	@Column(name = "DISTRIBUTION_BOARD")
	private String distributionBoard;
	
	@Column(name = "DISTRIBUTION_LOCATION")
	private String distributionLocation;
	
	@Column(name = "DISTRIBUTION_SOURCE_DETAILS")
	private String distributionSourceDetails;
	
	@Column(name = "DB_PARENT_FLAG")
	private String dbParentFlag;
	
	@Column(name = "DB_PROGRESS")
	private String dbProgress;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "IPAO_INSPECTION_ID")
	private IpaoInspection ipaoInspectionDbParent;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "dbParentArray", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ConsumerUnit> consumerUnit;

	@JsonManagedReference
	@OneToMany(mappedBy = "dbParentArray", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Circuit> circuit;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "dbParentArray", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ObsFormArrayB> obsFormArrayB;

	public Integer getDbParentID() {
		return dbParentID;
	}

	public void setDbParentID(Integer dbParentID) {
		this.dbParentID = dbParentID;
	}

	public String getDistributionBoard() {
		return distributionBoard;
	}

	public void setDistributionBoard(String distributionBoard) {
		this.distributionBoard = distributionBoard;
	}

	public String getDistributionLocation() {
		return distributionLocation;
	}

	public void setDistributionLocation(String distributionLocation) {
		this.distributionLocation = distributionLocation;
	}

	public String getDistributionSourceDetails() {
		return distributionSourceDetails;
	}

	public void setDistributionSourceDetails(String distributionSourceDetails) {
		this.distributionSourceDetails = distributionSourceDetails;
	}

	public String getDbParentFlag() {
		return dbParentFlag;
	}

	public void setDbParentFlag(String dbParentFlag) {
		this.dbParentFlag = dbParentFlag;
	}

	public IpaoInspection getIpaoInspectionDbParent() {
		return ipaoInspectionDbParent;
	}

	public void setIpaoInspectionDbParent(IpaoInspection ipaoInspectionDbParent) {
		this.ipaoInspectionDbParent = ipaoInspectionDbParent;
	}

	public List<ConsumerUnit> getConsumerUnit() {
		return consumerUnit;
	}

	public void setConsumerUnit(List<ConsumerUnit> consumerUnit) {
		this.consumerUnit = consumerUnit;
	}

	public List<Circuit> getCircuit() {
		return circuit;
	}

	public void setCircuit(List<Circuit> circuit) {
		this.circuit = circuit;
	}

	public List<ObsFormArrayB> getObsFormArrayB() {
		return obsFormArrayB;
	}

	public void setObsFormArrayB(List<ObsFormArrayB> obsFormArrayB) {
		this.obsFormArrayB = obsFormArrayB;
	}

	public String getDbProgress() {
		return dbProgress;
	}

	public void setDbProgress(String dbProgress) {
		this.dbProgress = dbProgress;
	}
}
