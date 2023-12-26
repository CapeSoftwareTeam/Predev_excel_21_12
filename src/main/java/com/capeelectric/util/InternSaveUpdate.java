package com.capeelectric.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capeelectric.model.LVInternSave;
import com.capeelectric.repository.InternSaveRepo;

@Component
public class InternSaveUpdate {

	@Autowired
	private InternSaveRepo internSaveRepo;

	public void updateSaveFlag(String userName, String serviceName, String flagName, Integer siteId) {

		Optional<LVInternSave> internSaveRepoData = internSaveRepo.findByUserNameAndSiteId(userName, siteId);

		if (internSaveRepoData.isPresent()) {
			setFlagValue(internSaveRepoData.get(), serviceName, flagName);

		} else {
			LVInternSave lvInternSave = new LVInternSave();
			lvInternSave.setUserName(userName);
			lvInternSave.setSiteId(siteId);
			setFlagValue(lvInternSave, serviceName, flagName);
		}
	}

	private void setFlagValue(LVInternSave internSave, String serviceName, String flagName) {

		if (serviceName.equalsIgnoreCase("BasicInfo")) {
			internSave.setBasicService(flagName);
		} else if (serviceName.equalsIgnoreCase("Supply")) {
			internSave.setSupplyService(flagName);
		} else if (serviceName.equalsIgnoreCase("Inspection")) {
			internSave.setInspectionService(flagName);
		} else {
			internSave.setTestingService(flagName);
		}

		internSaveRepo.save(internSave);

	}

}
