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
@Table(name = "ins_obs_form_array_two")
public class ObsFormArrayB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OBS_ID")
	private Integer obsId;
	
	@Column(name = "DISTRIBUTION_OBSERVATION")
	private String distributionObservation;
	
	@Column(name = "OBSB_FLAG")
	private String obsBFlag;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "DB_PARENT_ID")
	private DbParentArray dbParentArray;

	public Integer getObsId() {
		return obsId;
	}

	public void setObsId(Integer obsId) {
		this.obsId = obsId;
	}

	public String getDistributionObservation() {
		return distributionObservation;
	}

	public void setDistributionObservation(String distributionObservation) {
		this.distributionObservation = distributionObservation;
	}

	public String getObsBFlag() {
		return obsBFlag;
	}

	public void setObsBFlag(String obsBFlag) {
		this.obsBFlag = obsBFlag;
	}

	public DbParentArray getDbParentArray() {
		return dbParentArray;
	}

	public void setDbParentArray(DbParentArray dbParentArray) {
		this.dbParentArray = dbParentArray;
	}
	
}
