/**
 * 
 */
package com.capeelectric.service;


import java.util.List;

import com.capeelectric.exception.FanException;
import com.capeelectric.model.Fan;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface FanService {
	
	public Fan addFan(Fan fan) throws FanException ;
	
	public List<Fan> retrieveFanData(String fileName, String nodeId) throws FanException;
	
	public Fan updateFan(Fan fan) throws FanException ;

	void deleteFan(String nodeId) throws FanException;


}
