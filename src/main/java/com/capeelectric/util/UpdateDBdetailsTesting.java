package com.capeelectric.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.capeelectric.model.DbParentArray;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.TestDistRecords;
import com.capeelectric.model.TestDistribution;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingReport;

@Component
public class UpdateDBdetailsTesting {

	public TestingReport saveTestingReport(Optional<PeriodicInspection> inspection) {

		TestingReport testingReport = new TestingReport();
		testingReport.setUserName(inspection.get().getUserName());
		testingReport.setSiteId(inspection.get().getSiteId());
		testingReport.setCreatedDate(LocalDateTime.now());
		testingReport.setStatus("A");
		testingReport.setTesting(getTestingDetails(testingReport, inspection.get().getIpaoInspection()));
		
		return testingReport;
	}

	public List<Testing> getTestingDetails(TestingReport testingReport, List<IpaoInspection> ipaoInspection) {

		List<Testing> listOfTesting = new ArrayList<Testing>();

		for (IpaoInspection inspection : ipaoInspection) {
			Testing testing = new Testing();
			testing.setLocationCount(inspection.getLocationCount());
			testing.setLocationName(inspection.getLocationName());
			testing.setLocationNumber(inspection.getLocationNumber());
			//relationship 
			testing.setTestingReport(testingReport);
			testing.setTestingStatus("A");
//			testing.setTestDistRecords(getTestDistRecordsDetails(testing,inspection.getDbParentArray()));
			listOfTesting.add(testing);
		}

		return listOfTesting;
	}

	public List<TestDistRecords> getTestDistRecordsDetails(Testing testing, List<DbParentArray> dbParentArray) {

		List<TestDistRecords> listOfTestDistRecords = new ArrayList<TestDistRecords>();
		List<TestDistribution> listOfTestDistribution = new ArrayList<TestDistribution>();

		for (DbParentArray dbValues : dbParentArray) {
	 
			TestDistribution testDistribution = new TestDistribution();
			testDistribution.setReferance(dbValues.getDistributionBoard());
			testDistribution.setLocation(dbValues.getDistributionLocation());
			
			testDistribution.setDistributionBoardDetails(dbValues.getDistributionSourceDetails());
			listOfTestDistribution.add(testDistribution);
			
			TestDistRecords testDistRecords = new TestDistRecords();
			testDistRecords.setLocationCount(dbValues.getConsumerUnit().get(0).getLocationCount());
			testDistRecords.setTestDistribution(listOfTestDistribution);
			testDistRecords.setTestDistRecordStatus(dbValues.getConsumerUnit().get(0).getConsumerStatus());
			
			//Relationship mapping
			testDistRecords.setTesting(testing);
			testDistribution.setTestDistRecords(testDistRecords);
			
			listOfTestDistRecords.add(testDistRecords);

		}

		return listOfTestDistRecords;
	}
}
