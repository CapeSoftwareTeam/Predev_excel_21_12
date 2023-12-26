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
@Table(name = "text_excel_data_child")
public class TestExcelDataChild  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEXT_EXCEL_DATA_CHILDID")
	private Integer textExcelDataChildId;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "EXPERIENCE")
	private String experience;
	
	@Column(name = "MARITAL_STATUS")
	private String  maritalStatus;

	@Column(name = "MOTHER_NAME")
	private String  motherName;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "TEXT_EXCEL_DATAID")
	private TestExcelData testExceldata;

	public Integer getTextExcelDataChildId() {
		return textExcelDataChildId;
	}

	public void setTextExcelDataChildId(Integer textExcelDataChildId) {
		this.textExcelDataChildId = textExcelDataChildId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public TestExcelData getTestExceldata() {
		return testExceldata;
	}

	public void setTestExceldata(TestExcelData testExceldata) {
		this.testExceldata = testExceldata;
	}
	
	
	
	
	
}
