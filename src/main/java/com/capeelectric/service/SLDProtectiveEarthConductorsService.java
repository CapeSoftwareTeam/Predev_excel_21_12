package com.capeelectric.service;

import java.util.Optional;
import com.capeelectric.exception.ProtectiveEarthConductorsException;
import com.capeelectric.model.ProtectiveEarthConductors;
public interface SLDProtectiveEarthConductorsService {

	ProtectiveEarthConductors addProtectiveEarthConductors(ProtectiveEarthConductors protectiveEarthConductors)
			throws ProtectiveEarthConductorsException;

	Optional<ProtectiveEarthConductors> retrieveProtectiveEarthConductors(String fileName, String nodeId)
			throws ProtectiveEarthConductorsException;

	ProtectiveEarthConductors updateProtectiveEarthConductors(ProtectiveEarthConductors protectiveEarthConductors)
			throws ProtectiveEarthConductorsException;

	void deleteProtectiveEarthConductors(String nodeId) throws ProtectiveEarthConductorsException;

}
