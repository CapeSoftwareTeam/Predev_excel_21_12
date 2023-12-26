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
@Table(name = "ins_mains_observation")
public class MainsObservation implements Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MAINS_OBSERVATION_ID")
	private Integer mainsObservationId;

	@Column(name = "FURTHER_ACTIONS")
	private String furtherActions;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "OBERVATION_STATUS")
	private String obervationStatus;

	@Column(name = "OBSERVATIONS")
	private String observations;
	
	@Column(name = "REFERENCE_ID")
	private Integer referenceId;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "OBSERVATIONS_ID")
	private SummaryObservation summaryObservation;
	
	public Integer getMainsObservationId() {
		return mainsObservationId;
	}

	public void setMainsObservationId(Integer mainsObservationId) {
		this.mainsObservationId = mainsObservationId;
	}

	public String getFurtherActions() {
		return furtherActions;
	}

	public void setFurtherActions(String furtherActions) {
		this.furtherActions = furtherActions;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getObervationStatus() {
		return obervationStatus;
	}

	public void setObervationStatus(String obervationStatus) {
		this.obervationStatus = obervationStatus;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public SummaryObservation getSummaryObservation() {
		return summaryObservation;
	}

	public void setSummaryObservation(SummaryObservation summaryObservation) {
		this.summaryObservation = summaryObservation;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}
}
