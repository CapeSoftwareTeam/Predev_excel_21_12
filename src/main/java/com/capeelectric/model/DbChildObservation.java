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
@Table(name = "ins_db_child_observation")
public class DbChildObservation implements Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DB_CHILD_OBSERVATION_ID")
	private Integer dbChildObservationId;

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
	@JoinColumn(name = "DB_PARENT_OBSERVATION_ID")
	private DbParentObservation dbParentObservation;

	public Integer getDbChildObservationId() {
		return dbChildObservationId;
	}

	public void setDbChildObservationId(Integer dbChildObservationId) {
		this.dbChildObservationId = dbChildObservationId;
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

	public DbParentObservation getDbParentObservation() {
		return dbParentObservation;
	}

	public void setDbParentObservation(DbParentObservation dbParentObservation) {
		this.dbParentObservation = dbParentObservation;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}
}
