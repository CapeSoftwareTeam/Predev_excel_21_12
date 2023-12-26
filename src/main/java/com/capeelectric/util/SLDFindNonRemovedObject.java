package com.capeelectric.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.CableConnector;
import com.capeelectric.model.Cables;
import com.capeelectric.model.GeneralTestingCableConnector;
import com.capeelectric.model.GeneralTestingCables;
import com.capeelectric.model.GeneralTestingPAT;
import com.capeelectric.model.PortableAppliance;

@Configuration
public class SLDFindNonRemovedObject {
	
private static final Logger logger = LoggerFactory.getLogger(SLDFindNonRemovedObject.class);

	
	//find non removed values for 
	public List<GeneralTestingPAT> findNonRemovedPATData(Optional<PortableAppliance> patRepo) {
		logger.info("Called findNonRemovedPATData function");

		ArrayList<GeneralTestingPAT> generalTestingPAT = new ArrayList<GeneralTestingPAT>();
		List<GeneralTestingPAT> findNonRemoveBuildings = patRepo.get().getGeneralTestingPAT();
		for (GeneralTestingPAT generalTestingPATItr: findNonRemoveBuildings) {
			if (generalTestingPATItr.getFlag()==null || !generalTestingPATItr.getFlag().equalsIgnoreCase("R")) {
				if(generalTestingPATItr.getFlag()==null) {
					generalTestingPATItr.setFlag("N");
				}				
				generalTestingPAT.add(generalTestingPATItr);		 
			}
		}
		logger.info("Ended findNonRemovedPATData function");
		return generalTestingPAT;
	}

	public List<GeneralTestingCables> findNonRemovedCablesData(Optional<Cables> cablesRepo) {
		logger.info("Called findNonRemovedCablesData function");

		ArrayList<GeneralTestingCables> generalTestingCables = new ArrayList<GeneralTestingCables>();
		List<GeneralTestingCables> findNonRemoveBuildings = cablesRepo.get().getGeneralTestingCables();
		for (GeneralTestingCables generalTestingCablesItr: findNonRemoveBuildings) {
			if (generalTestingCablesItr.getFlag()==null || !generalTestingCablesItr.getFlag().equalsIgnoreCase("R")) {
				if(generalTestingCablesItr.getFlag()==null) {
					generalTestingCablesItr.setFlag("N");
				}				
				generalTestingCables.add(generalTestingCablesItr);		 
			}
		}
		logger.info("Ended findNonRemovedCablesData function");
		return generalTestingCables;
	}
	
	public List<GeneralTestingCableConnector> findNonRemovedCableConnectorData(Optional<CableConnector> cableConnectorRepo) {
		logger.info("Called findNonRemovedCableConnectorData function");

		ArrayList<GeneralTestingCableConnector> generalTestingCableConnector = new ArrayList<GeneralTestingCableConnector>();
		 List<GeneralTestingCableConnector> generalTestingCableConnectorRepo = cableConnectorRepo.get().getGeneralTestingCableConnector();
		for (GeneralTestingCableConnector GeneralTestingCableConnectorItr: generalTestingCableConnectorRepo) {
			if (GeneralTestingCableConnectorItr.getFlag()==null || !GeneralTestingCableConnectorItr.getFlag().equalsIgnoreCase("R")) {
				if(GeneralTestingCableConnectorItr.getFlag()==null) {
					GeneralTestingCableConnectorItr.setFlag("N");
				}				
				generalTestingCableConnector.add(GeneralTestingCableConnectorItr);		 
			}
		}
		logger.info("Ended findNonRemovedCableConnectorData function");
		return generalTestingCableConnector;
	}
	
//	public List<EarthConnector> findNonRemovedEarthConnectorData(Optional<EarthConnector> earthConnectorRepo) {
//		logger.info("Called findNonRemovedCableConnectorData function");
//
//		ArrayList<EarthConnector> earthConnector = new ArrayList<EarthConnector>();
//		 List<EarthConnector> earthConnectoRepository = earthConnectorRepo.get().get();
//		for (EarthConnector EarthConnectorItr: earthConnectorRepo) {
//			if (EarthConnectorItr.getFlag()==null || !EarthConnectorItr.getFlag().equalsIgnoreCase("R")) {
//				if(EarthConnectorItr.getFlag()==null) {
//					EarthConnectorItr.setFlag("N");
//				}				
//				earthConnector.add(EarthConnectorItr);		 
//			}
//		}
//		logger.info("Ended findNonRemovedCableConnectorData function");
//		return earthConnector;
//	}
	
}
