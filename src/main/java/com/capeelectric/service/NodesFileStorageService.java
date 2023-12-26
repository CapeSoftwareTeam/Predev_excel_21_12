/**
 * 
 */
package com.capeelectric.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.capeelectric.model.DiagramResponseFile;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface NodesFileStorageService {

	public void store(MultipartFile file,String component, String mainFileName, String nodeId, String fileSize) throws 
	  IOException, SerialException, SQLException;
	
	public DiagramResponseFile downloadFile(String nodeId) throws IOException;
	
	public void removeFile(String nodeId) throws IOException;
	
	public void updateFile(MultipartFile file,String component,String mainFileName, Integer fileId, String fileSize) throws 
	  SerialException, SQLException, IOException;
	
	public DiagramResponseFile retrieveFileNameByNodeId(String nodeId) throws IOException;

}
