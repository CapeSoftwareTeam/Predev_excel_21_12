/**
 * 
 */
package com.capeelectric.service.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.IOUtils;
import com.capeelectric.model.DiagramResponseFile;
import com.capeelectric.repository.FileUploadRepository;
import com.capeelectric.service.NodesFileStorageService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class NodesFileStorageImpl implements NodesFileStorageService {

	private static final Logger logger = LoggerFactory.getLogger(NodesFileStorageImpl.class);

	@Autowired
	private FileUploadRepository fileUploadRepository;

	public void store(MultipartFile file,String component, String mainFileName, String nodeId, String fileSize) throws IOException, SerialException, SQLException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
		Optional<DiagramResponseFile> responseFileData = fileUploadRepository.findByNodeId(nodeId);
		if(!responseFileData.isPresent()) {
		DiagramResponseFile FileDB = new DiagramResponseFile();
		FileDB.setNodeId(nodeId);
		FileDB.setFileName(fileName);
		FileDB.setComponent(component);
		FileDB.setMainFileName(mainFileName);
		FileDB.setData(blob);
		FileDB.setFileSize(fileSize);
		FileDB.setFileType(file.getContentType());
		logger.debug("File Saved In DB");
		fileUploadRepository.save(FileDB);
		}
		else {
			logger.debug("Node Id already saved Saved In DB");
			throw new IOException("Node Id already saved Saved In DB");
		}
	}

	public DiagramResponseFile downloadFile(String nodeId) throws IOException {
		if (nodeId != null && nodeId != "") {
			DiagramResponseFile fileDB = fileUploadRepository.findByNodeId(nodeId).get();
			if (fileDB != null && fileDB.getNodeId().equals(nodeId)) {
				return fileDB;
			} else {
				logger.error("File Not Preset");
				throw new IOException("File Not Preset");
			}
		} else {
			logger.error("Node Id Not Preset");
			throw new IOException("Node Id Not Preset");
		}

	}

	public void removeFile(String nodeId) throws IOException {
		if (nodeId != null && nodeId != "") {
			DiagramResponseFile fileDB = fileUploadRepository.findByNodeId(nodeId).get();
			if (fileDB != null && fileDB.getNodeId().equals(nodeId)) {
				logger.info("File Deleted");
				fileUploadRepository.delete(fileDB);
			} else {
				logger.error("File Not Preset");
				throw new IOException("File Not Preset");
			}

		} else {
			logger.error("Id Not Preset");
			throw new IOException("Id Not Preset");
		}

	}

	public void updateFile(MultipartFile file,String component,String mainFileName, Integer fileId, String fileSize) throws SerialException, SQLException, IOException {
		if (fileId != null && fileId != 0) {
			DiagramResponseFile fileDB = fileUploadRepository.findById(fileId).get();
			if (fileDB != null && fileDB.getFileId().equals(fileId)) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
				fileDB.setMainFileName(mainFileName);
				fileDB.setComponent(component);
				fileDB.setFileName(fileName);
				fileDB.setData(blob);
				fileDB.setFileType(file.getContentType());
				fileDB.setFileSize(fileSize);
				logger.debug("File Update In DB");
				fileUploadRepository.save(fileDB);
			} else {
				logger.error("File Not Preset");
				throw new IOException("File Not Preset");
			}
		} else {
			logger.error("Id Not Preset");
			throw new IOException("Id Not Preset");
		}

	}

	public DiagramResponseFile retrieveFileNameByNodeId(String nodeId) throws IOException {
		if (nodeId != null && nodeId != "") {
			Optional<DiagramResponseFile> fileDB = fileUploadRepository.findByNodeId(nodeId);
			if(fileDB.isPresent()) {
				if (fileDB.get() != null && fileDB.get().getNodeId().equals(nodeId)) {
					return fileDB.get();
				} else {
					logger.error("File Not Present");
				}
			}
		} else {
			logger.error("Id Not Present");
		}
		return null;
	}
}
