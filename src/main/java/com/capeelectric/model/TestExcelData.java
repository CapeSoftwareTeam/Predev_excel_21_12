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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "text_excel_data")
public class TestExcelData implements Serializable {
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "TEXT_EXCEL_DATAID")
		private Integer textExcelDataId;
		
		@Column(name = "USER_NAME")
		private String userName;
		
		@Column(name = "ADDRESS")
		private String address;
		
		@Column(name = "PHONE_NUMBER")
		private String  phoneNumber;
    
		@Column(name = "PERSONAL_DATA")
		private String  personalData;
		
		@JsonManagedReference
		@OneToMany(mappedBy = "testExceldata", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<TestExcelDataChild> testExcelDataChild;
		
		public Integer getTextExcelDataId() {
			return textExcelDataId;
		}

		public void setTextExcelDataId(Integer textExcelDataId) {
			this.textExcelDataId = textExcelDataId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getPersonalData() {
			return personalData;
		}

		public void setPersonalData(String personalData) {
			this.personalData = personalData;
		}

		public List<TestExcelDataChild> getTestExcelDataChild() {
			return testExcelDataChild;
		}

		public void setTestExcelDataChild(List<TestExcelDataChild> testExcelDataChild) {
			this.testExcelDataChild = testExcelDataChild;
		}
		
	

		

	
		
		
}
