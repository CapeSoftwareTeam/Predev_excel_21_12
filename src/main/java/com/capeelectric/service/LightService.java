/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.LightException;
import com.capeelectric.model.Light;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface LightService {
	
	public Light addLight(Light light) throws LightException ;
	
	public List<Light> retrieveLightData(String fileName, String nodeId) throws LightException;
	
	public Light updateLight(Light light) throws LightException ;

	void deleteLight(String nodeId) throws LightException;

}