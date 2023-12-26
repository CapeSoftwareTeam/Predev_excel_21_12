/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SwitchBoardException;
import com.capeelectric.model.SwitchBoard;
/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SwitchBoardService {
	
	public SwitchBoard addSwitchBoard(SwitchBoard switchBoard) throws SwitchBoardException;
	
	public List<SwitchBoard> retrieveSwitchBoardData(String fileName, String nodeId) throws SwitchBoardException;
	
	public SwitchBoard updateSwitchBoard(SwitchBoard switchBoard) throws SwitchBoardException;

	void deleteSwitchBoard(String nodeId) throws SwitchBoardException;


}
