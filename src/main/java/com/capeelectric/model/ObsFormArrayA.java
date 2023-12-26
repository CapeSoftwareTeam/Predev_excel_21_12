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
@Table(name = "ins_obs_form_array_one")
public class ObsFormArrayA implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OBS_ID")
	private Integer obsId;
	
	@Column(name = "MAINS_OBSERVATION")
	private String mainsObservation;
	
	@Column(name = "OBESA_FLAG")
	private String obsAFlag;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "IPAO_INSPECTION_ID")
	private IpaoInspection ipaoInspectionObsA;

	public Integer getObsId() {
		return obsId;
	}

	public void setObsId(Integer obsId) {
		this.obsId = obsId;
	}

	public String getMainsObservation() {
		return mainsObservation;
	}

	public void setMainsObservation(String mainsObservation) {
		this.mainsObservation = mainsObservation;
	}

	public String getObsAFlag() {
		return obsAFlag;
	}

	public void setObsAFlag(String obsAFlag) {
		this.obsAFlag = obsAFlag;
	}

	public IpaoInspection getIpaoInspectionObsA() {
		return ipaoInspectionObsA;
	}

	public void setIpaoInspectionObsA(IpaoInspection ipaoInspectionObsA) {
		this.ipaoInspectionObsA = ipaoInspectionObsA;
	}
	
}
