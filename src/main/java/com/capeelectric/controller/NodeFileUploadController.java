/**
 * 
 */
package com.capeelectric.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.IOUtils;
import com.capeelectric.model.DiagramResponseFile;
import com.capeelectric.service.NodesFileStorageService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class NodeFileUploadController<V> {

	private static final Logger logger = LoggerFactory.getLogger(NodeFileUploadController.class);
	@Autowired
	private NodesFileStorageService nodesFileStorageService;

	@PostMapping("/diagram/uploadNodeFile/{nodeId}/{fileSize}")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("component") String component,@RequestParam("mainFileName") String mainFileName,  @PathVariable String nodeId, @PathVariable String fileSize)
			throws IOException, SerialException, SQLException {
		logger.debug("File Upload Start for "+component+" component file name: " +mainFileName+" and Node Id: "+nodeId);
		nodesFileStorageService.store(file, component, mainFileName, nodeId, fileSize);
		logger.debug("File Upload End for "+component+" component file name: " +mainFileName+" and Node Id: "+nodeId);
		return new ResponseEntity<String>("File Uploaded Successfully", HttpStatus.OK);
	}

	@GetMapping("/diagram/downloadNodeFile/{component}/{nodeId}")
	public ResponseEntity<String> downloadFile(@PathVariable String component, @PathVariable String nodeId, HttpServletResponse response)
			throws IOException, SQLException {
		logger.debug("DownloadFile File Start for component: "+component+" and Node Id: "+nodeId);
		DiagramResponseFile fileDB = nodesFileStorageService.downloadFile(nodeId);
		response.setHeader("Content-Disposition", "inline; fileDB.getfileId()=\"" + fileDB.getFileId() + "\"");
		OutputStream out = response.getOutputStream();
		response.setContentType(fileDB.getFileName());
		IOUtils.copy(fileDB.getData().getBinaryStream(), out);
		out.flush();
		out.close();
		return null;

	}

	@GetMapping("/diagram/retrieveNodeFileName/{component}/{nodeId}")
	public ResponseEntity<Map> retrieveFileNameByNoded(@PathVariable String component,@PathVariable String nodeId) throws IOException, SQLException {
		logger.debug("Retrieve File Start for component: "+component+" and Node Id: "+nodeId);
		DiagramResponseFile fileDB = nodesFileStorageService.retrieveFileNameByNodeId(nodeId);
		if(null != fileDB) {
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("fileId", fileDB.getFileId().toString());
			hashMap.put("nodeId", fileDB.getNodeId());
			hashMap.put("component", fileDB.getComponent());
			hashMap.put("mainFileName", fileDB.getMainFileName());
			hashMap.put("fileType", fileDB.getFileType());
			hashMap.put("fileName", fileDB.getFileName());
			hashMap.put("fileSize", fileDB.getFileSize());
			return new ResponseEntity<Map>(hashMap, HttpStatus.OK);
		}
		return null;
		
	}

	@PutMapping("/diagram/updateNodeFile/{fileId}/{fileSize}")
	public ResponseEntity<String> updateFile(@RequestParam("file") MultipartFile file,@RequestParam("component") String component,@RequestParam("mainFileName") String mainFileName, @PathVariable Integer fileId, @PathVariable String fileSize)
			throws IOException, SerialException, SQLException {
		logger.debug("UpdateFile File Start for "+component+" component file name: {}",mainFileName);
		nodesFileStorageService.updateFile(file, component, mainFileName, fileId, fileSize);
		logger.debug("UpdateFile File End for "+component+" component file name: {}",mainFileName);
		return new ResponseEntity<String>("File Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/diagram/removeNodeFile/{component}/{nodeId}")
	public ResponseEntity<String> removeFile(@PathVariable String component,@PathVariable String nodeId) throws IOException {
		logger.debug("Remove File Start for "+component+" component node Id: {}",nodeId);
		nodesFileStorageService.removeFile(nodeId);
		logger.debug("Remove File End for "+component+" component node Id: {}",nodeId);
		return new ResponseEntity<String>("File  Deleted Successfully", HttpStatus.OK);
	}
}
