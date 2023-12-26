/**
 * 
 */
package com.capeelectric.service;


import java.util.List;

import com.capeelectric.exception.LTMotorException;
import com.capeelectric.model.LTMotor;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface LTMotorService {
	
	public LTMotor addLTMotor(LTMotor ltMotor) throws LTMotorException ;
	
	public List<LTMotor> retrieveLTMotorData(String fileName, String nodeId) throws LTMotorException;
	
	public LTMotor updateLTMotor(LTMotor ltMotor) throws LTMotorException ;

	void deleteLTMotor(String nodeId) throws LTMotorException;

	


}
