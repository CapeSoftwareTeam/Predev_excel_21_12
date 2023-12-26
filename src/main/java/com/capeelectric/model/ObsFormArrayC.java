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
@Table(name = "ins_obs_form_array_three")
public class ObsFormArrayC implements Serializable{
	
	private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OBS_ID")
	private Integer obsId;
	
	@Column(name = "SUB_DISTRIBUTION_OBS")
	private String subDistributionObs;
	
	@Column(name = "OBSC_FLAG")
	private String obsCFlag;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SUB_DB_PARENT_ID")
	private SubDbParent subDbParentObsC;

	public Integer getObsId() {
		return obsId;
	}

	public void setObsId(Integer obsId) {
		this.obsId = obsId;
	}

	public String getSubDistributionObs() {
		return subDistributionObs;
	}

	public void setSubDistributionObs(String subDistributionObs) {
		this.subDistributionObs = subDistributionObs;
	}

	public String getObsCFlag() {
		return obsCFlag;
	}

	public void setObsCFlag(String obsCFlag) {
		this.obsCFlag = obsCFlag;
	}

	public SubDbParent getSubDbParentObsC() {
		return subDbParentObsC;
	}

	public void setSubDbParentObsC(SubDbParent subDbParentObsC) {
		this.subDbParentObsC = subDbParentObsC;
	}
	
}
