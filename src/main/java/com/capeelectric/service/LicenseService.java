package com.capeelectric.service;

import java.net.URISyntaxException;

import com.capeelectric.model.ViewerRegister;

public interface LicenseService {

//	public Optional<LvRegister> retrieveLvRegister(String userName);
//
//	public Optional<LpsRegister> retrieveLpsRegister(String userName);

	public ViewerRegister addViewerRegistration(ViewerRegister viewerRegister) throws URISyntaxException, Exception;

}
