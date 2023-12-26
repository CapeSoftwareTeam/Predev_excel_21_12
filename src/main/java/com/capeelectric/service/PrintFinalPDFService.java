package com.capeelectric.service;

import com.capeelectric.exception.PdfException;

public interface PrintFinalPDFService {
	public void printFinalPDF(String userName, Integer siteId, String siteName) throws Exception, PdfException;
	public void printFinalEMCPDF(String userName, Integer emcId, String clientName) throws Exception;

}
