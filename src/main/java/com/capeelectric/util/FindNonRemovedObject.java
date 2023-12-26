package com.capeelectric.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.AlternativeInnerObservation;
import com.capeelectric.model.BoundingLocationReport;
import com.capeelectric.model.Circuit;
import com.capeelectric.model.CircuitBreaker;
import com.capeelectric.model.ConsumerUnit;
import com.capeelectric.model.DbChildObservation;
import com.capeelectric.model.DbParentArray;
import com.capeelectric.model.DbParentObservation;
import com.capeelectric.model.EarthingLocationReport;
import com.capeelectric.model.InspectionInnerObservations;
import com.capeelectric.model.InspectionOuterObservation;
import com.capeelectric.model.InstalLocationReport;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.IsolationCurrent;
import com.capeelectric.model.MainsObservation;
import com.capeelectric.model.ObsFormArrayA;
import com.capeelectric.model.ObsFormArrayB;
import com.capeelectric.model.ObsFormArrayC;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.SignatorDetails;
import com.capeelectric.model.SubDbChildObservation;
import com.capeelectric.model.SubDbObservation;
import com.capeelectric.model.SubDbParent;
import com.capeelectric.model.SummaryObservation;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.SupplyOuterObservation;
import com.capeelectric.model.SupplyParameters;
import com.capeelectric.model.TestDistRecords;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingEquipment;
import com.capeelectric.model.TestingInnerObservation;
import com.capeelectric.model.TestingRecords;
import com.capeelectric.model.TestingRecordsSourceSupply;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.SupplyCharacteristicsRepository;

/**
 * This FindNonRemovedObject Util class finding non Removed object for all_steps
 * 
 * @author capeelectricsoftware
 *
 */
@Configuration
public class FindNonRemovedObject {
	
	@Autowired
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;
	
	  

	public List<IpaoInspection> findNonRemovedInspectionLocation(PeriodicInspection inspectionRepo) {

		ArrayList<IpaoInspection> inspectionReport = new ArrayList<IpaoInspection>();
		List<IpaoInspection> findNonRemoveLocation = inspectionRepo.getIpaoInspection();
		for (IpaoInspection inspectionLocationReport : findNonRemoveLocation) {
			if (inspectionLocationReport.getInspectionFlag()==null || (inspectionLocationReport.getInspectionFlag() !=null &&
					!inspectionLocationReport.getInspectionFlag().equalsIgnoreCase("R"))) {
				if(inspectionLocationReport.getInspectionFlag()==null) {
					inspectionLocationReport.setInspectionFlag("N");
				}
				inspectionLocationReport.setObsFormArrayA(findNonRemovedObsFormA(inspectionLocationReport.getObsFormArrayA()));
				inspectionLocationReport.setSubDbParent(findNonRemovedSubParentDB(inspectionLocationReport.getSubDbParent()));
				inspectionLocationReport.setDbParentArray(findNonRemovedInspectionDbParent(inspectionLocationReport.getDbParentArray()));
//				inspectionLocationReport.setConsumerUnit(findNonRemovedInspectionConsumerUnit(inspectionLocationReport.getConsumerUnit()));
				inspectionLocationReport.setIsolationCurrent(findNonRemovedIsolationCurrent(inspectionLocationReport.getIsolationCurrent()));
				inspectionLocationReport.setInspectionOuterObervation(findNonRemovedInspectionOuterObervation(inspectionLocationReport.getInspectionOuterObervation()));
				inspectionReport.add(inspectionLocationReport);
				 
			}
		}
		return inspectionReport;
	}
	
	public List<DbParentArray> findNonRemovedInspectionDbParent(List<DbParentArray> listOfDbParent) {

		ArrayList<DbParentArray> unRemovedCircuit = new ArrayList<DbParentArray>();
		for (DbParentArray dbParentArray : listOfDbParent) {
			if (dbParentArray.getDbParentFlag() == null || (dbParentArray.getDbParentFlag() !=null &&
					!dbParentArray.getDbParentFlag().equalsIgnoreCase("R"))) {
				if (dbParentArray.getDbParentFlag() == null) {
					dbParentArray.setDbParentFlag("N");
				}
				dbParentArray.setObsFormArrayB(findNonRemovedObsFormB(dbParentArray.getObsFormArrayB()));

			
				unRemovedCircuit.add(dbParentArray);
			}
		}
		return unRemovedCircuit;
	}
	
	public List<ObsFormArrayB> findNonRemovedObsFormB(List<ObsFormArrayB> obsFormArray){
		ArrayList<ObsFormArrayB> obsFormB = new ArrayList<ObsFormArrayB>();	
		for(ObsFormArrayB obsFormArrayB : obsFormArray) {
			if(obsFormArrayB.getObsBFlag() !=null && (obsFormArrayB.getObsBFlag()!=null &&
					!obsFormArrayB.getObsBFlag().equalsIgnoreCase("R"))) {
				obsFormB.add(obsFormArrayB);
			}
		}
		return obsFormB;
		
	}
	
	public List<ObsFormArrayA> findNonRemovedObsFormA(List<ObsFormArrayA> obsFormArrayA){
		ArrayList<ObsFormArrayA> obsFormArrayAList = new ArrayList<ObsFormArrayA>();
		 for(ObsFormArrayA obsAformArray:obsFormArrayA) {
			 if(obsAformArray.getObsAFlag() !=null &&( obsAformArray.getObsAFlag() !=null
					 &&!obsAformArray.getObsAFlag().equalsIgnoreCase("R"))) {
				 obsFormArrayAList.add(obsAformArray);
			 }
		 }
		return obsFormArrayAList;
		
	}
	
	public List<ObsFormArrayC> findNonRemovedObsFormC(List<ObsFormArrayC> obsFormC){
		   ArrayList<ObsFormArrayC> obsFormArrayCList = new ArrayList<ObsFormArrayC>();
		   for(ObsFormArrayC obsFormArrayC: obsFormC) {
			   if(obsFormArrayC.getObsCFlag() !=null && !obsFormArrayC.getObsCFlag().equalsIgnoreCase("R")) {
				   obsFormArrayCList.add(obsFormArrayC);
			   }
		   }
		return obsFormArrayCList;
	}
	
	public List<SubDbParent> findNonRemovedSubParentDB(List<SubDbParent> subDbParent){
		   ArrayList<SubDbParent> subDbParentList = new ArrayList<SubDbParent>();
		   for(SubDbParent subDb: subDbParent) {
			   if( subDb.getSubDbParentFlag()!=null && !subDb.getSubDbParentFlag().equalsIgnoreCase("R")) {
					subDb.setObsFormArrayC(findNonRemovedObsFormC(subDb.getObsFormArrayC()));
					subDbParentList.add(subDb);
			   }
		   }
		   
		return subDbParentList;
	}
	
	public List<IsolationCurrent> findNonRemovedIsolationCurrent(List<IsolationCurrent> listOfIsolationCurrent) {

		ArrayList<IsolationCurrent> unRemovedIsolationCurrent = new ArrayList<IsolationCurrent>();
		for (IsolationCurrent isolationCurrent : listOfIsolationCurrent) {
			if (isolationCurrent.getIsolationStatus() == null || (isolationCurrent.getIsolationStatus() != null && !isolationCurrent.getIsolationStatus().equalsIgnoreCase("R"))) {
				if (isolationCurrent.getIsolationStatus() == null) {
					isolationCurrent.setIsolationStatus("N");
				}
				unRemovedIsolationCurrent.add(isolationCurrent);
			}
		}
		return unRemovedIsolationCurrent;
	}
	
//	public List<ConsumerUnit> findNonRemovedInspectionConsumerUnit(List<ConsumerUnit> ConsumerUnit) {
//
//		ArrayList<ConsumerUnit> unRemovedConsumer = new ArrayList<ConsumerUnit>();
//		for (ConsumerUnit consumerUnit : ConsumerUnit) {
//			if (consumerUnit.getConsumerStatus() == null || !consumerUnit.getConsumerStatus().equalsIgnoreCase("R")) {
//				if (consumerUnit.getConsumerStatus() == null) {
//					consumerUnit.setConsumerStatus("N");
//				}
//				unRemovedConsumer.add(consumerUnit);
//			}
//		}
//		return unRemovedConsumer;
//	}

	public List<InstalLocationReport> findNonRemovedInstallLocation(SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<InstalLocationReport> locationReport = new ArrayList<InstalLocationReport>();
		List<InstalLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getInstalLocationReport();
		for (InstalLocationReport instalLocationReport : findNonRemoveLocation) {
			if (instalLocationReport.getInstalLocationReportStatus()==null || 
					(instalLocationReport.getInstalLocationReportStatus() !=null && !instalLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R"))) {
				if(instalLocationReport.getInstalLocationReportStatus()==null) {
					instalLocationReport.setInstalLocationReportStatus("N");
				}
				locationReport.add(instalLocationReport);
			}
		}
		return locationReport;
	}

	public List<BoundingLocationReport> findNonRemovedBondingLocation(
			SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<BoundingLocationReport> locationReport = new ArrayList<BoundingLocationReport>();
		List<BoundingLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getBoundingLocationReport();
		for (BoundingLocationReport bondingLocationReport : findNonRemoveLocation) {
			if (bondingLocationReport.getInstalLocationReportStatus()==null || (bondingLocationReport.getInstalLocationReportStatus() !=null && !bondingLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R"))) {
				if(bondingLocationReport.getInstalLocationReportStatus()==null) {
					bondingLocationReport.setInstalLocationReportStatus("N");
				}
				locationReport.add(bondingLocationReport);
			}
		}
		return locationReport;
	}

	public List<EarthingLocationReport> findNonRemovedEarthingLocation(
			SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<EarthingLocationReport> locationReport = new ArrayList<EarthingLocationReport>();
		List<EarthingLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getEarthingLocationReport();
		for (EarthingLocationReport earthingLocationReport : findNonRemoveLocation) {
			if (earthingLocationReport.getInstalLocationReportStatus() ==null || (earthingLocationReport.getInstalLocationReportStatus() !=null && !earthingLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R"))) {
				if(earthingLocationReport.getInstalLocationReportStatus()==null) {
					earthingLocationReport.setInstalLocationReportStatus("N");
				}
				locationReport.add(earthingLocationReport);
			}
		}
		return locationReport;
	}

	public List<Testing> findNonRemoveTesting(List<Testing> listOfTesting,Integer siteId) {
		
		ArrayList<Testing> nonRemoveTesting = new ArrayList<Testing>();
		Integer i=1;
		for (Testing testing : listOfTesting) {
			if (testing != null && testing.getTestingStatus() != null
					&& !testing.getTestingStatus().equalsIgnoreCase("R")) {

				if (testing.getTestingStatus() == null) {
					testing.setTestingStatus("N");
				}
				testing.setTestingEquipment(findNonRemoveTestingEquipment(testing.getTestingEquipment()));
				List<TestDistRecords> NonRemoveTestingRecords = new ArrayList<TestDistRecords>();
				List<TestDistRecords> testDistRecords = testing.getTestDistRecords();
				Integer j=1;
				for (TestDistRecords testDistRecord : testDistRecords) {

					if (testDistRecord.getTestDistRecordStatus() == null) {
						testDistRecord.setTestDistRecordStatus("N");
					} else if (testDistRecord.getTestDistRecordStatus() != null
							&& !testDistRecord.getTestDistRecordStatus().equalsIgnoreCase("R")) {
						testDistRecord
								.setTestingRecords(findNonRemoveTestingRecord(testDistRecord.getTestingRecords(),siteId));
						testDistRecord.setTestingInnerObservation(
								findNonRemoveTestingInnerObservation(testDistRecord.getTestingInnerObservation(),i,j));
						NonRemoveTestingRecords.add(testDistRecord);
						j++;
					}
					
				}
				testing.setTestDistRecords(NonRemoveTestingRecords);
				nonRemoveTesting.add(testing);
				i++;
			}

		}
		return nonRemoveTesting;
	}

	private List<TestingEquipment> findNonRemoveTestingEquipment(List<TestingEquipment> testingEquipment) {
		List<TestingEquipment> listNonRemovedTestingEquipment = new ArrayList<TestingEquipment>();
		for (TestingEquipment testingEquipmentItr : testingEquipment) {
			if (testingEquipmentItr.getTestingEquipmentStatus() == null || (testingEquipmentItr.getTestingEquipmentStatus() !=null && !testingEquipmentItr.getTestingEquipmentStatus().equalsIgnoreCase("R"))) {
				if(testingEquipmentItr.getTestingEquipmentStatus() ==null) {
					testingEquipmentItr.setTestingEquipmentStatus("N");
				}
				listNonRemovedTestingEquipment.add(testingEquipmentItr);
			}
		}
		return listNonRemovedTestingEquipment;
	}

	public List<TestingRecords> findNonRemoveTestingRecord(List<TestingRecords> listOfTestingRecords,Integer siteId) {

		List<TestingRecords> listNonRemovedTestingRecord = new ArrayList<TestingRecords>();
		Optional<SupplyCharacteristics> supplyCharacteristicsRepo=supplyCharacteristicsRepository.findBySiteId(siteId);

		for (TestingRecords testingRecords : listOfTestingRecords) {
			if (testingRecords.getTestingRecordStatus() == null
					|| (testingRecords.getTestingRecordStatus() !=null &&
					!testingRecords.getTestingRecordStatus().equalsIgnoreCase("R"))) {
				if (testingRecords.getTestingRecordStatus() == null) {
					testingRecords.setTestingRecordStatus("N");
				}
				
				testingRecords.setTestingRecordsSourceSupply(findNonRemoveTestingSourceSupply(testingRecords.getTestingRecordsSourceSupply(),supplyCharacteristicsRepo.get().getSupplyParameters()));
				listNonRemovedTestingRecord.add(testingRecords);
			}			
		}
		return listNonRemovedTestingRecord;
	}
	
	public List<TestingRecordsSourceSupply> findNonRemoveTestingSourceSupply(List<TestingRecordsSourceSupply> testingSourceSupply,List<SupplyParameters> supplyParameters){
		List<TestingRecordsSourceSupply> listNonRemovedTestingRecordsSourceSupply = new ArrayList<TestingRecordsSourceSupply>();
		Integer i=0;
		 for(TestingRecordsSourceSupply testingRecordsSourceSupply:testingSourceSupply) {
				if (testingRecordsSourceSupply.getSourceSupplyStatus() == null
						|| (testingRecordsSourceSupply.getSourceSupplyStatus() != null
								&& !testingRecordsSourceSupply.getSourceSupplyStatus().equalsIgnoreCase("R"))) {
				 
				 if(testingRecordsSourceSupply.getSourceSupplyStatus()==null) {
					 testingRecordsSourceSupply.setSourceSupplyStatus("A");
					 if(supplyParameters.get(i).getSupplyParameterStatus()!="R") {
						 testingRecordsSourceSupply.setSupplyId(supplyParameters.get(i).getSupplyparametersId());
					 }
					 i++;
				 }
				 listNonRemovedTestingRecordsSourceSupply.add(testingRecordsSourceSupply);
			 } 
			
		 }
		return listNonRemovedTestingRecordsSourceSupply;
		
	}

	public Set<SignatorDetails> findNonRemovedReport(Set<SignatorDetails> signatorDetails) {
		Set<SignatorDetails> signatorDetail = new HashSet<SignatorDetails>();
		for (SignatorDetails signatorDetailItr : signatorDetails) {
			if (signatorDetailItr.getSignatorStatus() == null
					|| (signatorDetailItr.getSignatorStatus() !=null && !signatorDetailItr.getSignatorStatus().equalsIgnoreCase("R"))) {
				if(signatorDetailItr.getSignatorStatus() == null) {
					signatorDetailItr.setSignatorStatus("N");
				}
				signatorDetail.add(signatorDetailItr);
			}
		}
		return signatorDetail;
	}

//	public List<SummaryObervation> findNonRemoveObservation(List<SummaryObervation> summaryObervation) {
//		List<SummaryObervation> obervationList = new ArrayList<SummaryObervation>();
//		for (SummaryObervation obervation : summaryObervation) {
//			if (obervation.getObervationStatus() == null
//					|| !obervation.getObervationStatus().equalsIgnoreCase("R")) {
//				if(obervation.getObervationStatus() == null) {
//					obervation.setObervationStatus("N");
//				}
//				obervationList.add(obervation);
//			}
//		}
//		return obervationList;
//	}

	public List<CircuitBreaker> findNonRemovedCircuitBreaker(List<CircuitBreaker> circuitBreaker) {
		List<CircuitBreaker> circuitBreakerList = new ArrayList<CircuitBreaker>();
		for (CircuitBreaker circuitBreakerItr : circuitBreaker) {
			if (circuitBreakerItr.getCircuitStatus() == null
					|| (circuitBreakerItr.getCircuitStatus() !=null && !circuitBreakerItr.getCircuitStatus().equalsIgnoreCase("R"))) {
				if(circuitBreakerItr.getCircuitStatus() == null) {
					circuitBreakerItr.setCircuitStatus("N");
				}
				circuitBreakerList.add(circuitBreakerItr);
			}
		}
		return circuitBreakerList;
	}

	public List<SupplyParameters> findNonRemovedSupplyParameters(List<SupplyParameters> supplyParameters) {
		List<SupplyParameters> supplyParametersList = new ArrayList<SupplyParameters>();
		for (SupplyParameters supplyParametersItr : supplyParameters) {
			if (supplyParametersItr.getSupplyParameterStatus() == null
					|| ( supplyParametersItr.getSupplyParameterStatus() !=null && !supplyParametersItr.getSupplyParameterStatus().equalsIgnoreCase("R"))) {
				if(supplyParametersItr.getSupplyParameterStatus() == null) {
					supplyParametersItr.setSupplyParameterStatus("N");
				}
				supplyParametersList.add(supplyParametersItr);
			}
		}
		return supplyParametersList;
	}

	public List<SupplyOuterObservation> findNonRemovedSupplyOuterObservation(
			List<SupplyOuterObservation> supplyOuterObservation) {
		List<SupplyOuterObservation> supplyOuterObservationList = new ArrayList<SupplyOuterObservation>();
		for (SupplyOuterObservation supplyOuterObservationItr : supplyOuterObservation) {
			if (supplyOuterObservationItr.getSupplyOuterObservationStatus() != null
					&& !supplyOuterObservationItr.getSupplyOuterObservationStatus().equalsIgnoreCase("R")) {
				List<AlternativeInnerObservation> alternativeInnerObservationList = new ArrayList<AlternativeInnerObservation>();
				if (supplyOuterObservationItr.getAlternativeInnerObservation() != null 
						&& supplyOuterObservationItr.getObservationComponentDetails().equalsIgnoreCase("alternate")) {
					for (AlternativeInnerObservation alternativeInnerObservation : supplyOuterObservationItr
							.getAlternativeInnerObservation()) {
						if (alternativeInnerObservation.getAlternativeInnerObservationStatus() != null
								&& !alternativeInnerObservation.getAlternativeInnerObservationStatus()
										.equalsIgnoreCase("R")) {
							alternativeInnerObservationList.add(alternativeInnerObservation);
						}
					}
					
				}
				supplyOuterObservationItr.setAlternativeInnerObservation(alternativeInnerObservationList);
				supplyOuterObservationList.add(supplyOuterObservationItr);
			}
		}
		return supplyOuterObservationList;
	}


	private List<InspectionOuterObservation> findNonRemovedInspectionOuterObervation(
			List<InspectionOuterObservation> inspectionOuterObervation) {

		List<InspectionOuterObservation> outerObservationList = new ArrayList<InspectionOuterObservation>();
		for (InspectionOuterObservation outerObservation : inspectionOuterObervation) {
			if (outerObservation.getInspectionOuterObservationStatus() != null
					&& !outerObservation.getInspectionOuterObservationStatus().equalsIgnoreCase("R")) {
				List<InspectionInnerObservations> innerObservationsList = new ArrayList<InspectionInnerObservations>();
				if (outerObservation.getInspectionInnerObservations() != null) {
					for (InspectionInnerObservations innerObservations : outerObservation
							.getInspectionInnerObservations()) {
						if (innerObservations.getInspectionInnerObservationStatus() != null
								&& !innerObservations.getInspectionInnerObservationStatus().equalsIgnoreCase("R")) {
							innerObservationsList.add(innerObservations);
						}
					}
				}
				outerObservation.setInspectionInnerObservations(innerObservationsList);
				outerObservationList.add(outerObservation);
			}
		}
		return outerObservationList;
	}

	public List<TestingInnerObservation> findNonRemoveTestingInnerObservationByReport(
			Optional<TestingReport> testingReport) {

		List<TestingInnerObservation> innerObservationList = new ArrayList<TestingInnerObservation>();
		List<Testing> testingList = testingReport.get().getTesting();
       Integer i=1;
		for (Testing testing : testingList) {
			if (testing.getTestingStatus() != null && !testing.getTestingStatus().equalsIgnoreCase("R")) {
				List<TestDistRecords> testDistRecords = testing.getTestDistRecords();
				Integer j=1;
				for (TestDistRecords testDistRecordItr : testDistRecords) {
					if (testDistRecordItr.getTestDistRecordStatus() != null
							&& !testDistRecordItr.getTestDistRecordStatus().equalsIgnoreCase("R")) {
						innerObservationList.addAll(
								findNonRemoveTestingInnerObservation(testDistRecordItr.getTestingInnerObservation(),i,j));
						j++;
					}
				
				}
				i++;
			}

		}
		return innerObservationList;

	}
	
	
	public List<TestingInnerObservation> findNonRemoveTestingInnerObservation(
			List<TestingInnerObservation> testingInnerObservation,Integer i,Integer j) {
		List<TestingInnerObservation> innerObservationList = new ArrayList<TestingInnerObservation>();

		for (TestingInnerObservation innerObservation : testingInnerObservation) {
			if (innerObservation.getTestingInnerObservationStatus() != null
					&& !innerObservation.getTestingInnerObservationStatus().equalsIgnoreCase("R")) {
				innerObservation.setObservationComponentDetails("circuit"+","+"Location"+i+","+"Details Testing"+j);
				innerObservationList.add(innerObservation);
			}
		}
		return innerObservationList;
	}
	public List<TestingInnerObservation> findNonRemoveTestingInnerObservationList(
			List<TestingInnerObservation> testingInnerObservation) {
		List<TestingInnerObservation> innerObservationList = new ArrayList<TestingInnerObservation>();

		for (TestingInnerObservation innerObservation : testingInnerObservation) {
			if (innerObservation.getTestingInnerObservationStatus() != null
					&& !innerObservation.getTestingInnerObservationStatus().equalsIgnoreCase("R")) {
				innerObservation.setObservationComponentDetails(innerObservation.getObservationComponentDetails());
				innerObservationList.add(innerObservation);
			}
		}
		return innerObservationList;
	}
	
	

	public List<SummaryObservation> fileNonRemoveSummaryObservations(List<SummaryObservation> summaryObservation) {
		ArrayList<SummaryObservation> SummaryObservationList=new ArrayList<SummaryObservation>();
		for(SummaryObservation summaryObservation1:summaryObservation) {
			if(summaryObservation1.getObervationStatus()==null||!summaryObservation1.getObervationStatus().equalsIgnoreCase("R")) {
				if(summaryObservation1.getObervationStatus()==null) {
					summaryObservation1.setObervationStatus("A");
				}
				if(summaryObservation1.getMainsObservation()!=null) {
					summaryObservation1.setMainsObservation(fileNoneRemoveMainsObservations(summaryObservation1.getMainsObservation()));
				}
				if(summaryObservation1.getDbParentObservation()!=null) {
					summaryObservation1.setDbParentObservation(fileNoneRemoveDBObservations(summaryObservation1.getDbParentObservation()));
				}
				if(summaryObservation1.getSubDbObservation()!=null) {
					summaryObservation1.setSubDbObservation(fileNoneRemoveSubDBObservations(summaryObservation1.getSubDbObservation()));
				}
				SummaryObservationList.add(summaryObservation1);
			}
		}
		
		return SummaryObservationList;
		
		
	}

	private List<SubDbObservation> fileNoneRemoveSubDBObservations(List<SubDbObservation> subDbObservation) {
		ArrayList<SubDbObservation> subDbObservationList=new ArrayList<SubDbObservation>();
		for(SubDbObservation subDbObservation1:subDbObservation) {
			if(subDbObservation1.getSubDbObservationStatus()==null|| (subDbObservation1.getSubDbObservationStatus() !=null && 
					!subDbObservation1.getSubDbObservationStatus().equalsIgnoreCase("R"))) {
				if(subDbObservation1!=null) {
					subDbObservation1.setSubDbChildObservation(fileNoneRemoveSubDbChildObservations(subDbObservation1.getSubDbChildObservation()));
				}
				subDbObservationList.add(subDbObservation1);
			}
		}
		return subDbObservationList;
	}

	private List<SubDbChildObservation> fileNoneRemoveSubDbChildObservations(List<SubDbChildObservation> subDbChildObservation) {
		ArrayList<SubDbChildObservation> subDbChildObservationList=new ArrayList<SubDbChildObservation>();
		for(SubDbChildObservation subDbChildObservation1:subDbChildObservation) {
			if(subDbChildObservation1.getObervationStatus()==null||(subDbChildObservation1.getObervationStatus() !=null &&
					!subDbChildObservation1.getObervationStatus().equalsIgnoreCase("R"))) {
				subDbChildObservationList.add(subDbChildObservation1);
			}
		}
		return subDbChildObservationList;
	}

	private List<DbParentObservation> fileNoneRemoveDBObservations(List<DbParentObservation> dbParentObservation) {
		ArrayList<DbParentObservation> dbParentObservationList=new ArrayList<DbParentObservation>();
		for(DbParentObservation dbParentObservation1:dbParentObservation) {
			if(dbParentObservation1.getDbParentStatus()==null||(dbParentObservation1.getDbParentStatus() !=null &&
					!dbParentObservation1.getDbParentStatus().equalsIgnoreCase("R"))) {
				if(dbParentObservation1!=null) {
					dbParentObservation1.setDbChildObservation(fileNoneRemoveDbChildObservations(dbParentObservation1.getDbChildObservation()));
				}
				dbParentObservationList.add(dbParentObservation1);
			}
		}
		return dbParentObservationList;
	}

	private List<DbChildObservation> fileNoneRemoveDbChildObservations(List<DbChildObservation> dbChildObservation) {
		ArrayList<DbChildObservation> dbChildObservationList=new ArrayList<DbChildObservation>();
		for(DbChildObservation dbChildObservation1:dbChildObservation) {
			if(dbChildObservation1.getObervationStatus()==null|| (dbChildObservation1.getObervationStatus() !=null && 
					!dbChildObservation1.getObervationStatus().equalsIgnoreCase("R"))) {
				dbChildObservationList.add(dbChildObservation1);
			}
		}
		return dbChildObservationList;
	}

	private List<MainsObservation> fileNoneRemoveMainsObservations(List<MainsObservation> mainsObservation) {
		ArrayList<MainsObservation> mainsObservationList=new ArrayList<MainsObservation>();
		for(MainsObservation mainsObservation1:mainsObservation) {
			if(mainsObservation1.getObervationStatus()==null||
					(mainsObservation1.getObervationStatus() !=null && 
					!mainsObservation1.getObervationStatus().equalsIgnoreCase("R"))) {
				mainsObservationList.add(mainsObservation1);
			}
			
		}
		return mainsObservationList;
	}
}
