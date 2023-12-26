/**
 * 
 */
package com.capeelectric.controller;


import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CablesException;
import com.capeelectric.exception.EquipBondException;
import com.capeelectric.model.EquipotentialBonding;
import com.capeelectric.service.EquipBondService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v2")
public class EquipBondController {
private static final Logger logger = LoggerFactory.getLogger(EquipBondController.class);
	
	@Autowired
	private EquipBondService equipBondService;
	
	@PostMapping("/diagram/saveEquipotential_Bonding")
	public ResponseEntity<String> addEquipBond(@RequestBody EquipotentialBonding equipBond)
			throws EquipBondException {
		logger.info("called addEquipBond function File Name : {}", equipBond.getFileName());
		equipBondService.addEquipBond(equipBond); 
		return new ResponseEntity<String>("EquipBond details saved successfully", HttpStatus.CREATED);
	}

	@GetMapping("/diagram/retrieveEquipotential_Bonding/{fileName}/{nodeId}")
	public ResponseEntity<List<EquipotentialBonding>> retrieveEquipBond(@PathVariable String fileName, @PathVariable String nodeId) 
			throws EquipBondException {
		logger.info("called retrieveEquipotential_Bonding function FileName: {}, Node Id : {}", fileName, nodeId);
		return new ResponseEntity<List<EquipotentialBonding>>(equipBondService.retrieveEquipBondData(fileName, nodeId),
				HttpStatus.OK);
	}
	
	@PutMapping("/diagram/updateEquipotential_Bonding")
	public ResponseEntity<String> updateEquipBond(@RequestBody EquipotentialBonding equipBond)
			throws EquipBondException {
		logger.info("called updateEquipotential_Bonding function File Name : {}", equipBond.getFileName());
		equipBondService.updateEquipBond(equipBond);
		return new ResponseEntity<String>("EquipBond details updated successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/diagram/deleteEquipotential_Bonding/{nodeId}")
	public ResponseEntity<String> deleteEquiBond (@PathVariable String nodeId)
	throws EquipBondException{
		logger.info("called deleteEquipotential_Bonding function }", nodeId);
		equipBondService.deleteEquiBond(nodeId);
		return new ResponseEntity<String>("EquipBond deleted Sucessfully",HttpStatus.CREATED);
	}

}
