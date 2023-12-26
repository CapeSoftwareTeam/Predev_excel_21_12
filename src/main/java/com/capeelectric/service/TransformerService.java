/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.TransformerException;
import com.capeelectric.model.TransformerDetails;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface TransformerService {
	
	public TransformerDetails addTransformer(TransformerDetails transformerDetails) throws TransformerException;
	
	public List<TransformerDetails> retrieveTransformerData(String fileName, String nodeId) throws TransformerException;
	
	public TransformerDetails updateTransformer(TransformerDetails transformerDetails) throws TransformerException;

	void deleteTransformer(String nodeId) throws TransformerException;


}
