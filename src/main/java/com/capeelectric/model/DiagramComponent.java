/**
 * 
 */
package com.capeelectric.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "DIAGRAM_COMPONENT")

@NamedQueries(value = {
		@NamedQuery(name = "diagramRepository.findByUserNameAndFileName", query = "select s from DiagramComponent s where s.userName=:userName and s.fileName=:fileName"),
		@NamedQuery(name = "diagramRepository.findByUserNameAndDiagramId", query = "select s from DiagramComponent s where s.userName=:userName and s.diagramId=:diagramId"),
		@NamedQuery(name = "diagramRepository.findByUserName", query = "select s from DiagramComponent s where s.userName=:userName")

})

public class DiagramComponent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DIAGRAM_ID")
	private Integer diagramId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE")
	private String file;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	public Integer getDiagramId() {
		return diagramId;
	}

	public void setDiagramId(Integer diagramId) {
		this.diagramId = diagramId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	

	
}
