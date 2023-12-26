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

@Entity
@Table(name = "ins_subdb_observation")
public class SubDbObservation implements Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUBDB_OBSERVATION_ID")
	private Integer subDbObservationId;

	@Column(name = "SUBDB_OBSERVATION_STATUS")
	private String subDbObservationStatus;
	
	@Column(name = "REFERENCE_ID")
	private Integer referenceId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "OBSERVATIONS_ID")
	private SummaryObservation summaryObservation;

	@JsonManagedReference
	@OneToMany(mappedBy = "subDbObservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SubDbChildObservation> subDbChildObservation;

	public Integer getSubDbObservationId() {
		return subDbObservationId;
	}

	public void setSubDbObservationId(Integer subDbObservationId) {
		this.subDbObservationId = subDbObservationId;
	}

	public String getSubDbObservationStatus() {
		return subDbObservationStatus;
	}

	public void setSubDbObservationStatus(String subDbObservationStatus) {
		this.subDbObservationStatus = subDbObservationStatus;
	}

	public SummaryObservation getSummaryObservation() {
		return summaryObservation;
	}

	public void setSummaryObservation(SummaryObservation summaryObservation) {
		this.summaryObservation = summaryObservation;
	}

	public List<SubDbChildObservation> getSubDbChildObservation() {
		return subDbChildObservation;
	}

	public void setSubDbChildObservation(List<SubDbChildObservation> subDbChildObservation) {
		this.subDbChildObservation = subDbChildObservation;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}
}
