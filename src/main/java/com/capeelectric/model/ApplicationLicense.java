package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application_license")
public class ApplicationLicense implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="APPLICATION_LICENSEID")
	private Integer applicationLicenseId;
	
	@Column(name = "NO_OF_LICENSE")
	private Integer noofLicense;
	
	@Column(name = "INSPECTOR_NAME")
	private String inspectorName;
	
	@Column(name = "INSPECTOR_EMAIL")
	private String inspectorEmail;
	
	@Column(name = "PHONE_NUM")
	private String phoneNumber;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "APPLICATION_NAME")
    private String applicationName;

	@Column(name = "LICENSE_AMOUNT")
    private String licenseAmount;
	
	@Column(name = "ORDERID")
	private String orderId;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	public Integer getApplicationLicenseId() {
		return applicationLicenseId;
	}

	public void setApplicationLicenseId(Integer applicationLicenseId) {
		this.applicationLicenseId = applicationLicenseId;
	}

	public Integer getNoofLicense() {
		return noofLicense;
	}

	public void setNoofLicense(Integer noofLicense) {
		this.noofLicense = noofLicense;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getInspectorName() {
		return inspectorName;
	}

	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}

	public String getInspectorEmail() {
		return inspectorEmail;
	}

	public void setInspectorEmail(String inspectorEmail) {
		this.inspectorEmail = inspectorEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getLicenseAmount() {
		return licenseAmount;
	}

	public void setLicenseAmount(String licenseAmount) {
		this.licenseAmount = licenseAmount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
}
