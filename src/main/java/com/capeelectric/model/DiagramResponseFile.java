/**
 * 
 */
package com.capeelectric.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CAPE-SOFTWARE
 *
 */

@Entity
@Table(name = "FILE_UPLOAD_DIAGRAM_TABLE")
public class DiagramResponseFile implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FILE_ID")
	private Integer fileId;

	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "MAIN_FILE_NAME")
	private String mainFileName;

	@Column(name = "NODE_ID")
	private String nodeId;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "DATA")
	private Blob data;
	
	@Column(name = "FILE_SIZE")
	private String fileSize;
	
	@Column(name = "COMPONENT")
	private String component;

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMainFileName() {
		return mainFileName;
	}

	public void setMainFileName(String mainFileName) {
		this.mainFileName = mainFileName;
	}

	

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
