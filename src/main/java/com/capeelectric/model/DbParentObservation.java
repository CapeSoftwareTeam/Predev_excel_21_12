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
@Table(name = "ins_db_parent_observation")
public class DbParentObservation implements Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DB_PARENT_OBSERVATION_ID")
	private Integer dbParentObservationId;

	@Column(name = "DB_PARENT_STATUS")
	private String dbParentStatus;
	
	@Column(name = "REFERENCE_ID")
	private Integer referenceId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "OBSERVATIONS_ID")
	private SummaryObservation summaryObservation;

	@JsonManagedReference
	@OneToMany(mappedBy = "dbParentObservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DbChildObservation> dbChildObservation;

	public Integer getDbParentObservationId() {
		return dbParentObservationId;
	}

	public void setDbParentObservationId(Integer dbParentObservationId) {
		this.dbParentObservationId = dbParentObservationId;
	}

	public String getDbParentStatus() {
		return dbParentStatus;
	}

	public void setDbParentStatus(String dbParentStatus) {
		this.dbParentStatus = dbParentStatus;
	}

	public SummaryObservation getSummaryObservation() {
		return summaryObservation;
	}

	public void setSummaryObservation(SummaryObservation summaryObservation) {
		this.summaryObservation = summaryObservation;
	}

	public List<DbChildObservation> getDbChildObservation() {
		return dbChildObservation;
	}

	public void setDbChildObservation(List<DbChildObservation> dbChildObservation) {
		this.dbChildObservation = dbChildObservation;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}
	

}
