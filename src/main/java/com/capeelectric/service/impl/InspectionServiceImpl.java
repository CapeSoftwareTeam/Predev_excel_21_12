package com.capeelectric.service.impl;

import java.security.cert.PKIXRevocationChecker.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.AlternativeInnerObservation;
import com.capeelectric.model.ConsumerUnit;
import com.capeelectric.model.DbChildObservation;
import com.capeelectric.model.DbParentArray;
import com.capeelectric.model.DbParentObservation;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.MainsObservation;
import com.capeelectric.model.ObsFormArrayA;
import com.capeelectric.model.ObsFormArrayB;
import com.capeelectric.model.ObsFormArrayC;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.PeriodicInspectionComment;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.SubDbChildObservation;
import com.capeelectric.model.SubDbObservation;
import com.capeelectric.model.SubDbParent;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SummaryObservation;
import com.capeelectric.model.TestDistRecords;
import com.capeelectric.model.TestDistribution;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingInnerObservation;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.DbParentObservationRepository;
import com.capeelectric.repository.DbParentRepository;
import com.capeelectric.repository.InspectionConsumerUnitRepository;
import com.capeelectric.repository.InspectionIsolationRepository;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.repository.IpaoInspectionRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SubDbObservationRepository;
import com.capeelectric.repository.SubDbParentRepository;
import com.capeelectric.repository.SummaryObservationRepo;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.repository.TestDistRecordsRepository;
import com.capeelectric.repository.TestInfoRepository;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.InspectionService;
import com.capeelectric.util.Constants;
import com.capeelectric.util.FindNonRemovedObject;
import com.capeelectric.util.InternSaveUpdate;
import com.capeelectric.util.SiteDetails;
import com.capeelectric.util.UpdateDBdetailsTesting;
import com.capeelectric.util.UserFullName;
/**
 * This InspectionServiceImpl class to add and retrieve the IpaoInspection object
 * @author capeelectricsoftware
 *
 */
@Service
public class InspectionServiceImpl implements InspectionService {
	
	private static final Logger logger = LoggerFactory.getLogger(InspectionServiceImpl.class);

	@Autowired
	private InspectionRepository inspectionRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private SiteRepository siteRepository;
	
	private PeriodicInspectionComment periodicInspectionComment;
	
	private List<PeriodicInspectionComment> listOfComments;
	
	private String viewerName;
	
	@Autowired
	private SiteDetails siteDetails;
	
	@Autowired
	private TestInfoRepository testInfoRepository;
	
	@Autowired
	private FindNonRemovedObject findNonRemovedObject;
	
	@Autowired
	private InspectionConsumerUnitRepository inspectionConsumerUnitRepository;
	
	@Autowired
	private InspectionIsolationRepository inspectionIsolationRepository;
	
	@Autowired
	private IpaoInspectionRepository ipaoInspectionRepo;
	
	@Autowired
	private DbParentRepository dbParentRepository;
	
	@Autowired
	private SubDbParentRepository subDbParentRepository;
	
	@Autowired
	private SubDbObservationRepository subDbObservationRepo;
	
	@Autowired
	private DbParentObservationRepository dbParentObservationRepo;
	
	@Autowired
	private TestDistRecordsRepository testDistRecordsRepository;
	
	@Autowired 
	private SummaryRepository summaryRepository;
	
	@Autowired
	private SummaryObservationRepo summaryObservationRepo;
	
	@Autowired
	private InternSaveUpdate internSaveUpdate;
	
	@Autowired
	private TestingReportRepository testingReportRepository;
	
	@Autowired
	private UpdateDBdetailsTesting updateDBdetailsTotesting;
	
	/**
	 * @param IpaoInspection object 
	 * addInspectionDetails method to save IpaoInspection object into table
	 * @throws CompanyDetailsException 
	 * 
	*/
	@Transactional
	@Override
	public void addInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException {
		listOfComments = new ArrayList<PeriodicInspectionComment>();
        int i=0;
		if (periodicInspection.getUserName() != null && periodicInspection.getSiteId() != null
				&& periodicInspection.getIpaoInspection() != null) {
			List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
			Optional<PeriodicInspection> periodicInspectionValue = inspectionRepository.findBySiteId(periodicInspection.getSiteId());
			if (!periodicInspectionValue.isPresent() || periodicInspectionValue.get().getStatus().equalsIgnoreCase("R") || !periodicInspectionValue.get().getSiteId().equals(periodicInspection.getSiteId())) {
				if (ipaoInspection != null && ipaoInspection.size() > 0) {
					for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
						ipaoInspectionItr.setLocationCount(new Random().nextInt(999999999));
						if (ipaoInspectionItr !=null && ipaoInspectionItr.getSubDbParent() != null 
								&& ipaoInspectionItr.getDbParentArray() != null && ipaoInspectionItr.getMainsFirstArray() != null && ipaoInspectionItr.getMainsSecondArray() != null
							    && ipaoInspectionItr.getSubDbParent().size()>0 
								&& ipaoInspectionItr.getDbParentArray().size() > 0 && ipaoInspectionItr.getMainsFirstArray().size() > 0 && ipaoInspectionItr.getMainsSecondArray().size() > 0) {
							   for(DbParentArray dbParentArray: ipaoInspectionItr.getDbParentArray()) {
									dbParentArray.setConsumerUnit(addLocationCountInConsumerUnit(dbParentArray.getConsumerUnit()));
							   }
							//findConsumerUnitLocation(ipaoInspectionItr.getConsumerUnit());
							i++;
							if (i == ipaoInspection.size()) {
								periodicInspectionComment = new PeriodicInspectionComment();
								periodicInspectionComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
								periodicInspectionComment.setViewerFlag(Constants.INTIAL_FLAG_VALUE);
								periodicInspectionComment.setNoOfComment(1);
								periodicInspectionComment.setViewerDate(LocalDateTime.now());
								periodicInspectionComment.setPeriodicInspection(periodicInspection);
								listOfComments.add(periodicInspectionComment);
								periodicInspection.setPeriodicInspectorComment(listOfComments);
								periodicInspection.setCreatedDate(LocalDateTime.now());
								periodicInspection.setUpdatedDate(LocalDateTime.now());
								periodicInspection
										.setCreatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
								periodicInspection
										.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
								try {
									inspectionRepository.save(periodicInspection);
									logger.debug("InspectionDetails Successfully Saved in DB");
								}catch(Exception e) {
									logger.error("Not able to save Inspection data "+e.getMessage());
									throw new InspectionException("Not able to save Inspection data "+e.getMessage());
								}
								Optional<PeriodicInspection> periodicInspectionData = inspectionRepository.findBySiteId(periodicInspection.getSiteId());
								Optional<TestingReport> testingRepo = testingReportRepository.findBySiteId(periodicInspection.getSiteId());
								if(!testingRepo.isPresent()) {
									testingReportRepository.save(updateDBdetailsTotesting.saveTestingReport(periodicInspectionData));
								}
								
								siteDetails.updateSite(periodicInspection.getSiteId(),
										periodicInspection.getUserName(),"Step3 completed");
								logger.debug("Site Successfully Saved in DB");
								
								internSaveUpdate.updateSaveFlag(periodicInspection.getUserName(), "Inspection", "Save", periodicInspection.getSiteId());
								logger.debug("Updated successfully internSaveflag");
							}

						} else {
							logger.error("Please fill all the fields before clicking next button");
							throw new InspectionException("Please fill all the fields before clicking next button");
						}
					}
				} else {
					logger.error("SiteId already present");
					throw new InspectionException("SiteId already present");
				}

			} else {
				logger.error("Inspection data contains duplicate Object");
				throw new InspectionException("Inspection data contains duplicate Object");
			}

		} else {
			logger.error("Invalid input");
			throw new InspectionException("Invalid input");
		}
	}

	/**
	 * @param IpaoInspection object 
	 * addInspectionDetails method to save IpaoInspection object into table
	 * @throws CompanyDetailsException 
	 * 
	*/
	@Transactional
	@Override
	public void addInternInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException {
		listOfComments = new ArrayList<PeriodicInspectionComment>();
        int i=0;
		if (periodicInspection.getUserName() != null && periodicInspection.getSiteId() != null
				&& periodicInspection.getIpaoInspection() != null) {
			List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
			Optional<PeriodicInspection> periodicInspectionValue = inspectionRepository.findBySiteId(periodicInspection.getSiteId());
			
			if (!periodicInspectionValue.isPresent() || periodicInspectionValue.get().getStatus().equalsIgnoreCase("R")
					|| !periodicInspectionValue.get().getSiteId().equals(periodicInspection.getSiteId())) {
				
				if (ipaoInspection != null && ipaoInspection.size() > 0) {
					for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
						ipaoInspectionItr.setLocationCount(new Random().nextInt(999999999));
						
							for (DbParentArray dbParentArray : ipaoInspectionItr.getDbParentArray()) {
								dbParentArray.setConsumerUnit(
										addLocationCountInConsumerUnit(dbParentArray.getConsumerUnit()));
							}
							i++;
							if (i == ipaoInspection.size()) {
								periodicInspectionComment = new PeriodicInspectionComment();
								periodicInspectionComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
								periodicInspectionComment.setViewerFlag(Constants.INTIAL_FLAG_VALUE);
								periodicInspectionComment.setNoOfComment(1);
								periodicInspectionComment.setViewerDate(LocalDateTime.now());
								periodicInspectionComment.setPeriodicInspection(periodicInspection);
								listOfComments.add(periodicInspectionComment);
								periodicInspection.setPeriodicInspectorComment(listOfComments);
								periodicInspection.setCreatedDate(LocalDateTime.now());
								periodicInspection.setUpdatedDate(LocalDateTime.now());
								periodicInspection
										.setCreatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
								periodicInspection
										.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
								try {
									inspectionRepository.save(periodicInspection);
									logger.debug("InspectionDetails Successfully Saved in DB");
								} catch (Exception e) {
									logger.error("Not able to save Inspection data " + e.getMessage());
									throw new InspectionException("Not able to save Inspection data " + e.getMessage());
								}
								Optional<PeriodicInspection> periodicInspectionData = inspectionRepository.findBySiteId(periodicInspection.getSiteId());
								Optional<TestingReport> testingRepo = testingReportRepository.findBySiteId(periodicInspection.getSiteId());
								if(!testingRepo.isPresent()) {
									testingReportRepository.save(updateDBdetailsTotesting.saveTestingReport(periodicInspectionData));
								}

								siteDetails.updateSite(periodicInspection.getSiteId(), periodicInspection.getUserName(),
										"Step3 completed");
								logger.debug("Site Successfully Saved in DB");

								internSaveUpdate.updateSaveFlag(periodicInspection.getUserName(), "Inspection",
										"IntSave",periodicInspection.getSiteId());
								logger.debug("Updated successfully internSaveflag");
							}
					}
				} else {
					logger.error("SiteId already present");
					throw new InspectionException("SiteId already present");
				}

			} else {
				logger.error("Inspection data contains duplicate Object");
				throw new InspectionException("Inspection data contains duplicate Object");
			}

		} else {
			logger.error("Invalid input");
			throw new InspectionException("Invalid input");
		}
	}
	
	/**
	 * @param userName,siteId
	 * retrieveInspectionDetails method to retrieve data based on userName,siteId
	 * @return List<IpaoInspection> object 
	*/
	@Override
	public PeriodicInspection retrieveInspectionDetails(String userName, Integer siteId)
			throws InspectionException {
		if (userName != null && !userName.isEmpty() && siteId != null) {
			PeriodicInspection inspectionRepo = inspectionRepository.findByUserNameAndSiteId(userName, siteId);
			if (inspectionRepo != null) {
				
				if (inspectionRepo.getCreatedDate().toLocalDate()
						.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate()) && (inspectionRepo.getStatus() == null || inspectionRepo.getStatus() != null
								&& !inspectionRepo.getStatus().equalsIgnoreCase("R"))) {
					inspectionRepo.setStatus("R");
					inspectionRepository.save(inspectionRepo);
					return new PeriodicInspection();

				} else if (inspectionRepo.getStatus() == null || inspectionRepo.getStatus() != null
						&& !inspectionRepo.getStatus().equalsIgnoreCase("R")) {
					inspectionRepo.setStatus("U");
					
//					inspectionRepo.setIpaoInspection(isNullLocationCount(inspectionRepo.getIpaoInspection()));
					inspectionRepo.setIpaoInspection(findNonRemovedObject.findNonRemovedInspectionLocation(inspectionRepo));
					sortingDateTime(inspectionRepo.getPeriodicInspectorComment());
					return inspectionRepo;
				} else {
					return new PeriodicInspection();
				}
				
			} else {
				logger.error("Given UserName & Site doesn't exist Inspection");
				throw new InspectionException("Given UserName & Site doesn't exist Inspection");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new InspectionException("Invalid Inputs");
		}
	}
	
	@Override
	public PeriodicInspection retrieveInspectionDetails(Integer siteId)
			throws InspectionException {
		if (siteId != null) {
			Optional<PeriodicInspection> inspectionRepoData = inspectionRepository.findBySiteId(siteId);
			PeriodicInspection inspectionRepo = inspectionRepoData.get();

			if (inspectionRepo != null) {
				
				if (inspectionRepo.getCreatedDate().toLocalDate()
						.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate()) && (inspectionRepo.getStatus() == null || inspectionRepo.getStatus() != null
								&& !inspectionRepo.getStatus().equalsIgnoreCase("R")) ) {
					inspectionRepoData.get().setStatus("R");
					inspectionRepository.save(inspectionRepoData.get());
					return new PeriodicInspection();

				} else if (inspectionRepo.getStatus() == null || inspectionRepo.getStatus() != null
						&& !inspectionRepo.getStatus().equalsIgnoreCase("R")) {
					inspectionRepo.setStatus("U");
					
//					inspectionRepo.setIpaoInspection(isNullLocationCount(inspectionRepo.getIpaoInspection()));
					inspectionRepo.setIpaoInspection(findNonRemovedObject.findNonRemovedInspectionLocation(inspectionRepo));
					sortingDateTime(inspectionRepo.getPeriodicInspectorComment());
					return inspectionRepo;
				} else {
					return new PeriodicInspection();
				}
		
			} else {
				logger.error("Given UserName & Site doesn't exist Inspection");
				throw new InspectionException("Given UserName & Site doesn't exist Inspection");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new InspectionException("Invalid Inputs");
		}
	}
	

	/**
	 * @reportId,siteId must required
	 * @param PeriodicInspection Object
	 * updateInspectionDetails method to finding the given PeriodicInspectionId is available or not in DB,
	 * if available only allowed for updating 
	 * @throws CompanyDetailsException 
	 * 
	*/
	@Transactional
	@Override
	public void updateInspectionDetails(PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		if (periodicInspection != null && periodicInspection.getPeriodicInspectionId() != null
				&& periodicInspection.getPeriodicInspectionId() != 0 && periodicInspection.getSiteId() != null
				&& periodicInspection.getSiteId() != 0) {
			Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository
					.findById(periodicInspection.getPeriodicInspectionId());
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getSiteId().equals(periodicInspection.getSiteId())) {
				List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
				Optional<Summary> summaryRepo=summaryRepository.findBySiteId(periodicInspection.getSiteId());
				for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
					logger.debug("locationcount value adding for new location");
					// locationcount value adding for new location
					if (ipaoInspectionItr != null && ipaoInspectionItr.getLocationCount() == null) {
						ipaoInspectionItr.setLocationCount(new Random().nextInt(999999999));
						  for(DbParentArray dbParentArray: ipaoInspectionItr.getDbParentArray()) {
								dbParentArray.setConsumerUnit(addLocationCountInConsumerUnit(dbParentArray.getConsumerUnit()));
						   }
						
					} else {
						for(DbParentArray dbParentArray: ipaoInspectionItr.getDbParentArray()) {
							if(dbParentArray.getConsumerUnit() != null) {
								for (ConsumerUnit consumerUnit : dbParentArray.getConsumerUnit()) {
									// locationcount value adding for new consumerUnit
									if (consumerUnit != null && consumerUnit.getConsumerId() == null) {
										consumerUnit.setLocationCount(new Random().nextInt(999999999));
										logger.debug("locationcount value adding for new consumerUnit");
									}
								}
							}
						}
					}
				}
				periodicInspection.setUpdatedDate(LocalDateTime.now());
				periodicInspection.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
				inspectionRepository.save(periodicInspection);
				logger.debug("Inspection successfully updated into DB");
				internSaveUpdate.updateSaveFlag(periodicInspection.getUserName(), "Inspection",
						"Update",periodicInspection.getSiteId());
				logger.debug("Updated successfully internSaveflag");
				siteDetails.updateSite(periodicInspection.getSiteId(), periodicInspection.getUserName(),"Step3 completed");
				logger.debug("Updated successfully site updatedUsername",periodicInspection.getUserName());
				Optional<PeriodicInspection> periodicInspectionRepoData = inspectionRepository
						.findById(periodicInspection.getPeriodicInspectionId());
				Optional<TestingReport> testingRepo=testingReportRepository.findBySiteId(periodicInspection.getSiteId());
				if(testingRepo.isPresent()) {
					addRemoveStatusInTesting(periodicInspectionRepoData.get().getIpaoInspection(),testingRepo);
					addRemoveStatusInTestDistRecords(periodicInspectionRepoData.get().getIpaoInspection(),periodicInspectionRepoData.get().getSiteId());
				}
				else {
					testingReportRepository.save(updateDBdetailsTotesting.saveTestingReport(periodicInspectionRepoData));
				}
				if(summaryRepo.isPresent()) {
					Summary summary=summaryRepo.get();
					try {
						summaryRepository.save(setStatusInSummary(summary,periodicInspectionRepoData));
						logger.debug("Summary Observations Updated successfully",periodicInspection.getSiteId());
					}
					catch (Exception e) {
						logger.error("Summary Observations Update Failed"+ e.getMessage());
						throw new InspectionException("Summary Observations Update Failed");
					}
				}
			} else {
				logger.error("Given SiteId and ReportId is Invalid");
				throw new InspectionException("Given SiteId and ReportId is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new InspectionException("Invalid inputs");
		}

	}
	
	/**
	 * @reportId,siteId must required
	 * @param PeriodicInspection Object
	 * updateInspectionDetails method to finding the given PeriodicInspectionId is available or not in DB,
	 * if available only allowed for updating 
	 * @throws CompanyDetailsException 
	 * 
	 */
	@Transactional
	@Override
	public void updateInternInspectionDetails(PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		if (periodicInspection != null && periodicInspection.getPeriodicInspectionId() != null
				&& periodicInspection.getPeriodicInspectionId() != 0 && periodicInspection.getSiteId() != null
				&& periodicInspection.getSiteId() != 0) {
			Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository
					.findById(periodicInspection.getPeriodicInspectionId());
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getSiteId().equals(periodicInspection.getSiteId())) {
				List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
				Optional<Summary> summaryRepo=summaryRepository.findBySiteId(periodicInspection.getSiteId());
				for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
					logger.debug("locationcount value adding for new location");
					// locationcount value adding for new location
					if (ipaoInspectionItr != null && ipaoInspectionItr.getLocationCount() == null) {
						ipaoInspectionItr.setLocationCount(new Random().nextInt(999999999));
						for(DbParentArray dbParentArray: ipaoInspectionItr.getDbParentArray()) {
							dbParentArray.setConsumerUnit(addLocationCountInConsumerUnit(dbParentArray.getConsumerUnit()));
						}
						
					} else {
						for(DbParentArray dbParentArray: ipaoInspectionItr.getDbParentArray()) {
							if(dbParentArray.getConsumerUnit() != null) {
								for (ConsumerUnit consumerUnit : dbParentArray.getConsumerUnit()) {
									// locationcount value adding for new consumerUnit
									if (consumerUnit != null && consumerUnit.getConsumerId() == null) {
										consumerUnit.setLocationCount(new Random().nextInt(999999999));
										logger.debug("locationcount value adding for new consumerUnit");
									}
								}
							}
						}
					}
				}
				periodicInspection.setUpdatedDate(LocalDateTime.now());
				periodicInspection.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
				inspectionRepository.save(periodicInspection);
				logger.debug("Inspection successfully updated into DB");
				internSaveUpdate.updateSaveFlag(periodicInspection.getUserName(), "Inspection",
						"IntSave",periodicInspection.getSiteId());
				logger.debug("Updated successfully internSaveflag");
				
				siteDetails.updateSite(periodicInspection.getSiteId(), periodicInspection.getUserName(),"Step3 completed");
				logger.debug("Updated successfully site updatedUsername",periodicInspection.getUserName());
				Optional<PeriodicInspection> periodicInspectionRepoData = inspectionRepository
						.findById(periodicInspection.getPeriodicInspectionId());
				Optional<TestingReport> testingRepo=testingReportRepository.findBySiteId(periodicInspection.getSiteId());
				if(testingRepo.isPresent()) {
					addRemoveStatusInTesting(periodicInspectionRepoData.get().getIpaoInspection(),testingRepo);
					addRemoveStatusInTestDistRecords(periodicInspectionRepoData.get().getIpaoInspection(),periodicInspectionRepoData.get().getSiteId());
				}
				else {
					testingReportRepository.save(updateDBdetailsTotesting.saveTestingReport(periodicInspectionRepoData));
				}
				if(summaryRepo.isPresent()) {
					Summary summary=summaryRepo.get();
					try {
						summaryRepository.save(setStatusInSummary(summary,periodicInspectionRepoData));
						logger.debug("Summary Observations Updated successfully",periodicInspection.getSiteId());
					}
					catch (Exception e) {
						logger.error("Summary Observations Update Failed"+ e.getMessage());
						throw new InspectionException("Summary Observations Update Failed");
					}
				}
			} else {
				logger.error("Given SiteId and ReportId is Invalid");
				throw new InspectionException("Given SiteId and ReportId is Invalid");
			}
			
		} else {
			logger.error("Invalid Inputs");
			throw new InspectionException("Invalid inputs");
		}
		
	}

	private Summary setStatusInSummary(Summary summary, Optional<PeriodicInspection> periodicInspectionRepoData) {

		for (IpaoInspection ipaoInspection : periodicInspectionRepoData.get().getIpaoInspection()) {
			if (ipaoInspection.getInspectionFlag().equalsIgnoreCase("R")) {
				logger.debug("Inspection R Status Update In Summary Observations");
				for (SummaryObservation summaryObservation : summary.getSummaryObservation()) {
					if (summaryObservation.getReferenceId().equals(ipaoInspection.getIpaoInspectionId())) {
						summaryObservation.setObervationStatus(ipaoInspection.getInspectionFlag());
					}
				}
			} else {
				SummaryObservation summaryAvilable = isSummaryAvilable(summary, ipaoInspection);
				SummaryObservation summaryObservation = new SummaryObservation();
				if (summaryAvilable != null) {
					logger.debug("Update Summary Observation for Exist Inspection Location Starts");
					summaryAvilable.setObervationStatus(ipaoInspection.getInspectionFlag());
					List<MainsObservation> mainsObservation = isMainsSummaryAvailable(summaryAvilable,
							ipaoInspection.getObsFormArrayA());
//					List<DbParentObservation> dbParentObservation = isDbParentObservationAvailable(summaryAvilable,
//							ipaoInspection.getDbParentArray());
//					List<SubDbObservation> subDbObservation = isSubDbObservationAvailable(summaryAvilable,
//							ipaoInspection.getSubDbParent());
					summaryAvilable.setMainsObservation(mainsObservation);
//					summaryAvilable.setDbParentObservation(dbParentObservation);
//					summaryAvilable.setSubDbObservation(subDbObservation);
					logger.debug("Update Summary Observation for Exist Inspection Location ends");

				} else {
					logger.debug("Add new Summary Observation for New Inspection Location Starts");
					summaryObservation.setObervationStatus(ipaoInspection.getInspectionFlag());
					summaryObservation.setReferenceId(ipaoInspection.getIpaoInspectionId());
					summaryObservation.setObservationComponentDetails("inspectionComponent");
					List<MainsObservation> mainsObservation = newMainsObservation(summaryObservation,
							ipaoInspection.getObsFormArrayA());
//					List<DbParentObservation> dbParentObservation = newDbParentObservation(summaryObservation,
//							ipaoInspection.getDbParentArray());
//					List<SubDbObservation> subDbObservation = newSubDbObservation(summaryObservation,
//							ipaoInspection.getSubDbParent());
//					summaryObservation.setSubDbObservation(subDbObservation);
//					summaryObservation.setDbParentObservation(dbParentObservation);
					summaryObservation.setMainsObservation(mainsObservation);
					summaryObservation.setSummary(summary);
					summary.getSummaryObservation().add(summaryObservation);
					logger.debug("Add new Summary Observation for New Inspection Location ends");
				}

			}
		}
		return summary;

	}
	
	private Summary updateDbObservation(DbParentArray dbParentArr,IpaoInspection ipaoInspection,Summary summary) {
		
		SummaryObservation summaryAvailable = isSummaryAvilable(summary, ipaoInspection);

		 if(summaryAvailable!=null) {
			 List<DbParentObservation> dbParentObservation = isDbParentObservationAvailable(summaryAvailable,dbParentArr);
			 summaryAvailable.setDbParentObservation(dbParentObservation);
		 }
		return summary;
	}
	
	private Summary updateSubDbObservation(SubDbParent subDbParentArr,IpaoInspection ipaoInspection,Summary summary) {
		
		SummaryObservation summaryAvailable = isSummaryAvilable(summary, ipaoInspection);

		 if(summaryAvailable!=null) {
			 List<SubDbObservation> subDbParentObservation = isSubDbObservationAvailable(summaryAvailable,subDbParentArr);
			 summaryAvailable.setSubDbObservation(subDbParentObservation);
		 }
		return summary;
	}
	
	
	private List<SubDbObservation> isSubDbObservationAvailable(SummaryObservation summaryAvilable,
			SubDbParent subDbParent) {
		if (summaryAvilable.getSubDbObservation() != null) {
//			for (SubDbParent subDbParent1 : subDbParent) {
				SubDbObservation subDbObservation1 = new SubDbObservation();
				SubDbObservation subDbObservation = issubDbObservationAvailable(summaryAvilable.getSubDbObservation(),
						subDbParent);
				if (subDbObservation != null) {
					logger.debug("Update Sub Db in Summary Observations Starts");
					subDbObservation.setSubDbObservationStatus(subDbParent.getSubDbParentFlag());
					subDbObservation.setSubDbChildObservation(
							newSubDbChildObservation1(subDbObservation, subDbParent.getObsFormArrayC()));
					logger.debug("Update Sub Db in Summary Observations Ends");
				} else {
					logger.debug("Add new Sub Db in Summary Observations Starts");
					subDbObservation1.setReferenceId(subDbParent.getSubDbParentID());
					subDbObservation1.setSubDbObservationStatus(subDbParent.getSubDbParentFlag());
					subDbObservation1.setSummaryObservation(summaryAvilable);
					subDbObservation1.setSubDbChildObservation(
							newSubDbChildObservation(subDbObservation1, subDbParent.getObsFormArrayC()));
					summaryAvilable.getSubDbObservation().add(subDbObservation1);
					logger.debug("Add new Sub Db in Summary Observations Ends");
//				}
			}
		}

		return summaryAvilable.getSubDbObservation();
	}

	private List<SubDbChildObservation> newSubDbChildObservation1(SubDbObservation subDbObservation,
			List<ObsFormArrayC> obsFormArrayC) {
		for (ObsFormArrayC obsFormArrayC1 : obsFormArrayC) {
			SubDbChildObservation subDbChildObservation1 = isnewSubDbChildObservationsAvailable(
					subDbObservation.getSubDbChildObservation(), obsFormArrayC1);
			SubDbChildObservation subDbChildObservation2 = new SubDbChildObservation();

			if (subDbChildObservation1 != null) {
				logger.debug("Update Sub Db Observations in Summary Observations Starts");
				subDbChildObservation1.setObervationStatus(obsFormArrayC1.getObsCFlag());
				subDbChildObservation1.setObservations(obsFormArrayC1.getSubDistributionObs());
				logger.debug("Update Sub Db Observations in Summary Observations ends");
			} else {
				logger.debug("Add new Sub Db Observations in Summary Observations Starts");
				subDbChildObservation2.setObervationStatus(obsFormArrayC1.getObsCFlag());
				subDbChildObservation2.setReferenceId(obsFormArrayC1.getObsId());
				subDbChildObservation2.setObservations(obsFormArrayC1.getSubDistributionObs());
				subDbChildObservation2.setSubDbObservation(subDbObservation);
				subDbObservation.getSubDbChildObservation().add(subDbChildObservation2);
				logger.debug("Add new Sub Db Observations in Summary Observations ends");
			}
		}
		return subDbObservation.getSubDbChildObservation();

	}

	private SubDbChildObservation isnewSubDbChildObservationsAvailable(
			List<SubDbChildObservation> subDbChildObservation, ObsFormArrayC obsFormArrayC1) {
		for (SubDbChildObservation subDbChildObservation1 : subDbChildObservation) {
			if (subDbChildObservation1.getReferenceId() != null
					&& subDbChildObservation1.getReferenceId().equals(obsFormArrayC1.getObsId())) {
				logger.debug("Sub Db Observation Id Already in Summary Observations");
				return subDbChildObservation1;
			}
		}
		return null;
	}

	private SubDbObservation issubDbObservationAvailable(List<SubDbObservation> subDbObservation,
			SubDbParent subDbParent1) {
		for (SubDbObservation subDbObservation1 : subDbObservation) {
			if (subDbObservation1.getReferenceId() != null
					&& subDbObservation1.getReferenceId().equals(subDbParent1.getSubDbParentID())) {
				logger.debug("Sub Db Id Already in Summary Observations");
				return subDbObservation1;
			}
		}
		return null;
	}

	private List<DbParentObservation> isDbParentObservationAvailable(SummaryObservation summaryAvilable,DbParentArray dbParentArray) {
		if (summaryAvilable.getDbParentObservation() != null) {
//			for (DbParentArray dbParentArray1 : dbParentArray) {
				DbParentObservation dbParentObservation1 = new DbParentObservation();
				DbParentObservation dbParentObservation = isDbParentObservationAvailable1(
						summaryAvilable.getDbParentObservation(), dbParentArray);
				if (dbParentObservation != null) {
					logger.debug("Update Db in Summary Observations Starts");
					dbParentObservation.setDbParentStatus(dbParentArray.getDbParentFlag());
					dbParentObservation.setDbChildObservation(
							newDbChildObservation1(dbParentObservation, dbParentArray.getObsFormArrayB()));
					logger.debug("Update Db in Summary Observations Ends");
				} else {
					logger.debug("Add new Db in Summary Observations Starts");
					dbParentObservation1.setReferenceId(dbParentArray.getDbParentID());
					dbParentObservation1.setDbParentStatus(dbParentArray.getDbParentFlag());
					dbParentObservation1.setSummaryObservation(summaryAvilable);
					dbParentObservation1.setDbChildObservation(
							newDbChildObservation(dbParentObservation1, dbParentArray.getObsFormArrayB()));
					summaryAvilable.getDbParentObservation().add(dbParentObservation1);
					logger.debug("Add new Db in Summary Observations Ends");
				}
//			}
		}
		return summaryAvilable.getDbParentObservation();
	}

	private List<DbChildObservation> newDbChildObservation1(DbParentObservation dbParentObservation,
			List<ObsFormArrayB> obsFormArrayB) {
		for (ObsFormArrayB obsFormArrayB1 : obsFormArrayB) {
			DbChildObservation dbChildObservation1 = isnewChildObservationsAvailable(
					dbParentObservation.getDbChildObservation(), obsFormArrayB1);
			DbChildObservation dbChildObservation2 = new DbChildObservation();

			if (dbChildObservation1 != null) {
				logger.debug("Update Db Observations in Summary Observations Starts");
				dbChildObservation1.setObervationStatus(obsFormArrayB1.getObsBFlag());
				dbChildObservation1.setObservations(obsFormArrayB1.getDistributionObservation());
				logger.debug("Update Db Observations in Summary Observations Ends");
			} else {
				logger.debug("Add new Db Observations in Summary Observations Starts");
				dbChildObservation2.setObervationStatus(obsFormArrayB1.getObsBFlag());
				dbChildObservation2.setReferenceId(obsFormArrayB1.getObsId());
				dbChildObservation2.setObservations(obsFormArrayB1.getDistributionObservation());
				dbChildObservation2.setDbParentObservation(dbParentObservation);
				dbParentObservation.getDbChildObservation().add(dbChildObservation2);
				logger.debug("Add new Db Observations in Summary Observations Ends");
			}
		}
		return dbParentObservation.getDbChildObservation();
	}

	private DbChildObservation isnewChildObservationsAvailable(List<DbChildObservation> dbChildObservation,
			ObsFormArrayB obsFormArrayB1) {
		for (DbChildObservation dbChildObservation1 : dbChildObservation) {
			if (dbChildObservation1.getReferenceId() != null
					&& dbChildObservation1.getReferenceId().equals(obsFormArrayB1.getObsId())) {
				logger.debug("Db Observation Id Already in Summary Observations");
				return dbChildObservation1;
			}
		}
		return null;
	}

	private DbParentObservation isDbParentObservationAvailable1(List<DbParentObservation> dbParentObservation,
			DbParentArray dbParentArray1) {
		for (DbParentObservation dbParentObservation1 : dbParentObservation) {
			if (dbParentObservation1.getReferenceId() != null
					&& dbParentObservation1.getReferenceId().equals(dbParentArray1.getDbParentID())) {
				logger.debug("Db Parent Id Already in Summary Observations");
				return dbParentObservation1;
			}
		}
		return null;
	}

	private List<SubDbObservation> newSubDbObservation(SummaryObservation summaryObservation,
			List<SubDbParent> subDbParent) {
		logger.debug("New SubDb parent Add in Summary Observations");
		ArrayList<SubDbObservation> dbParentObservationList = new ArrayList<>();
		SubDbObservation subDbObservation1 = new SubDbObservation();

		for (SubDbParent subDbParent1 : subDbParent) {
			subDbObservation1.setReferenceId(subDbParent1.getSubDbParentID());
			subDbObservation1.setSubDbObservationStatus(subDbParent1.getSubDbParentFlag());
			List<SubDbChildObservation> subDbChildObservation = newSubDbChildObservation(subDbObservation1,
					subDbParent1.getObsFormArrayC());
			subDbObservation1.setSubDbChildObservation(subDbChildObservation);
			subDbObservation1.setSummaryObservation(summaryObservation);
			dbParentObservationList.add(subDbObservation1);
		}
		return dbParentObservationList;
	}

	private List<SubDbChildObservation> newSubDbChildObservation(SubDbObservation subDbObservation1,
			List<ObsFormArrayC> obsFormArrayC) {
		logger.debug("New SubDb Observations Add in Summary Observations");
		ArrayList<SubDbChildObservation> subDbChildObservationList = new ArrayList<>();
		SubDbChildObservation subDbChildObservation1 = new SubDbChildObservation();
		for (ObsFormArrayC obsFormArrayC1 : obsFormArrayC) {
			subDbChildObservation1.setObervationStatus(obsFormArrayC1.getObsCFlag());
			subDbChildObservation1.setReferenceId(obsFormArrayC1.getObsId());
			subDbChildObservation1.setObservations(obsFormArrayC1.getSubDistributionObs());
			subDbChildObservation1.setSubDbObservation(subDbObservation1);
			subDbChildObservationList.add(subDbChildObservation1);
		}

		return subDbChildObservationList;
	}

	private List<DbParentObservation> newDbParentObservation(SummaryObservation summaryObservation,
			List<DbParentArray> dbParentArray) {
		logger.debug("New Db parent Add in Summary Observations");
		ArrayList<DbParentObservation> dbParentObservationList = new ArrayList<>();
		DbParentObservation dbParentObservation1 = new DbParentObservation();
		for (DbParentArray dbParentArray1 : dbParentArray) {
			dbParentObservation1.setReferenceId(dbParentArray1.getDbParentID());
			dbParentObservation1.setDbParentStatus(dbParentArray1.getDbParentFlag());
			List<DbChildObservation> dbChildObservation = newDbChildObservation(dbParentObservation1,
					dbParentArray1.getObsFormArrayB());
			dbParentObservation1.setDbChildObservation(dbChildObservation);
			dbParentObservation1.setSummaryObservation(summaryObservation);
			dbParentObservationList.add(dbParentObservation1);
		}
		return dbParentObservationList;
	}

	private List<DbChildObservation> newDbChildObservation(DbParentObservation dbParentObservation1,
			List<ObsFormArrayB> obsFormArrayB) {
		logger.debug("New Db Observations Add in Summary Observations");
		ArrayList<DbChildObservation> dbChildObservationList = new ArrayList<>();
		DbChildObservation dbChildObservation1 = new DbChildObservation();
		for (ObsFormArrayB obsFormArrayB1 : obsFormArrayB) {
			dbChildObservation1.setObervationStatus(obsFormArrayB1.getObsBFlag());
			dbChildObservation1.setReferenceId(obsFormArrayB1.getObsId());
			dbChildObservation1.setObservations(obsFormArrayB1.getDistributionObservation());
			dbChildObservation1.setDbParentObservation(dbParentObservation1);
			dbChildObservationList.add(dbChildObservation1);
		}

		return dbChildObservationList;
	}

	private List<MainsObservation> newMainsObservation(SummaryObservation summaryObservation,
			List<ObsFormArrayA> obsFormArrayA) {
		logger.debug("New Mains Observations Add in Summary Observations");
		ArrayList<MainsObservation> mainsObservationList = new ArrayList<>();
		MainsObservation mainsObservation = new MainsObservation();
		for (ObsFormArrayA ObsFormArrayA : obsFormArrayA) {
			mainsObservation.setReferenceId(ObsFormArrayA.getObsId());
			mainsObservation.setObervationStatus(ObsFormArrayA.getObsAFlag());
			mainsObservation.setObservations(ObsFormArrayA.getMainsObservation());
			mainsObservation.setSummaryObservation(summaryObservation);
			mainsObservationList.add(mainsObservation);
		}
		return mainsObservationList;
	}

	private SummaryObservation isSummaryAvilable(Summary summary, IpaoInspection ipaoInspection) {
		for (SummaryObservation summaryObservation : summary.getSummaryObservation()) {
			if (summaryObservation.getReferenceId() != null
					&& summaryObservation.getReferenceId().equals(ipaoInspection.getIpaoInspectionId())) {
				logger.debug("Inspection Id already in summary Observations");
				return summaryObservation;
			}

		}
		return null;

	}

	private List<MainsObservation> isMainsSummaryAvailable(SummaryObservation summaryObservation,
			List<ObsFormArrayA> obsFormArrayA) {
		if (summaryObservation.getMainsObservation() != null) {
			for (ObsFormArrayA obsFormArrayA1 : obsFormArrayA) {
				MainsObservation mainsObservation1 = new MainsObservation();
				MainsObservation mainsObservation = isAvailable(summaryObservation.getMainsObservation(),
						obsFormArrayA1);
				if (mainsObservation != null) {
					logger.debug("Inspection Mains Observation update in Summary Observations Starts");
					mainsObservation.setObservations(obsFormArrayA1.getMainsObservation());
					mainsObservation.setObervationStatus(obsFormArrayA1.getObsAFlag());
					logger.debug("Inspection Mains Observation update in Summary Observations ends");
				} else {
					logger.debug("New Mains Observation add In Summary Observations");
					mainsObservation1.setReferenceId(obsFormArrayA1.getObsId());
					mainsObservation1.setObervationStatus(obsFormArrayA1.getObsAFlag());
					mainsObservation1.setObservations(obsFormArrayA1.getMainsObservation());
					mainsObservation1.setSummaryObservation(summaryObservation);
					summaryObservation.getMainsObservation().add(mainsObservation1);
				}
			}
		}

		return summaryObservation.getMainsObservation();
	}

	private MainsObservation isAvailable(List<MainsObservation> mainsObservation, ObsFormArrayA obsFormArrayA1) {
		for (MainsObservation mainsObservation1 : mainsObservation) {
			if (mainsObservation1.getReferenceId() != null
					&& mainsObservation1.getReferenceId().equals(obsFormArrayA1.getObsId())) {
				logger.debug("Inspection Mains Observation Id already in Summary Observations");
				return mainsObservation1;
			}
		}
		return null;
	}

	@Override
	public void sendComments(String userName, Integer siteId, PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException {
		PeriodicInspection periodicInspection = verifyCommentsInfo(userName, siteId, periodicInspectionComment, Constants.SEND_COMMENT);
		if (periodicInspection != null) {
			inspectionRepository.save(periodicInspection);
			logger.debug("sendComments successfully into DB");
		} else {
			logger.error("Invalid Inputs");
			throw new InspectionException("Periodic-Inspection information doesn't exist for given Site-Id");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId,
			PeriodicInspectionComment periodicInspectionComment) throws InspectionException {
		PeriodicInspection periodicInspection = verifyCommentsInfo(inspectorUserName, siteId, periodicInspectionComment,
				Constants.REPLY_COMMENT);
		if (periodicInspection != null) {
			inspectionRepository.save(periodicInspection);
			logger.debug("ReplyComments successfully into DB");
			return viewerName;
		} else {
			logger.error("Periodic-Inspection information doesn't exist for given Site-Id");
			throw new InspectionException("Periodic-Inspection information doesn't exist for given Site-Id");
		}
	}

	@Override
	public void approveComments(String userName, Integer siteId, PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException {
		PeriodicInspection periodicInspection = verifyCommentsInfo(userName, siteId, periodicInspectionComment,
				Constants.APPROVE_REJECT_COMMENT);
		if (periodicInspection != null) {
			inspectionRepository.save(periodicInspection);
			logger.debug("ReplyComments successfully into DB");
		} else {
			logger.error("Periodic-Inspection information doesn't exist for given Site-Id");
			throw new InspectionException("Periodic-Inspection information doesn't exist for given Site-Id");
		}
	}

	private PeriodicInspection verifyCommentsInfo(String userName, Integer siteId,
			PeriodicInspectionComment periodicInspectionComment, String process) throws InspectionException {

		Boolean flagInspectionComment = true;
		if (userName != null && siteId != null && periodicInspectionComment != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)
					&& siteRepo.get().getAssignedTo() != null && siteRepo.get().getUserName() != null
					&& siteRepo.get().getSite() != null) {
				Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository.findBySiteId(siteId);
				if (periodicInspectionRepo.isPresent() && periodicInspectionRepo.get() != null
						&& periodicInspectionRepo.get().getUserName() != null
						&& checkInspectorViewer(userName, process, siteRepo, periodicInspectionRepo)) {
					PeriodicInspection periodicInspection = periodicInspectionRepo.get();
					periodicInspection.setUpdatedDate(LocalDateTime.now());
					periodicInspection.setUpdatedBy(userName);
					List<PeriodicInspectionComment> periodicInspectorCommentRepo = periodicInspection
							.getPeriodicInspectorComment();

					for (PeriodicInspectionComment periodicInspectionCommentItr : periodicInspectorCommentRepo) {
						if (periodicInspectionCommentItr.getCommentsId()
								.equals(periodicInspectionComment.getCommentsId())) {
							flagInspectionComment = false;

							periodicInspectionCommentItr.setPeriodicInspection(periodicInspection);

							if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
								periodicInspectionCommentItr.setSiteName(siteRepo.get().getSite());
								periodicInspectionCommentItr.setInspectorEmail(siteRepo.get().getUserName());
								periodicInspectionCommentItr.setViewerUserEmail(siteRepo.get().getAssignedTo());
								periodicInspectionCommentItr.setViewerDate(LocalDateTime.now());
								periodicInspectionCommentItr
										.setViewerComment(periodicInspectionComment.getViewerComment());
								periodicInspectionCommentItr.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
								periodicInspectionCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								return periodicInspection;
							}
							if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
								periodicInspectionCommentItr.setInspectorDate(LocalDateTime.now());
								periodicInspectionCommentItr
										.setInspectorUserName(userFullName.findByUserName(userName));
								periodicInspectionCommentItr
										.setInspectorComment(periodicInspectionComment.getInspectorComment());
								periodicInspectionCommentItr.setInspectorFlag(Constants.INCREASED_FLAG_VALUE);
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								return periodicInspection;
							}
							if (process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
								periodicInspectionCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								periodicInspectionCommentItr
										.setApproveOrReject(periodicInspectionComment.getApproveOrReject());
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								return periodicInspection;
							}
						}
					}
					if (flagInspectionComment) {

						if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
							periodicInspectionComment.setNoOfComment(
									checkNoOfComments(periodicInspection.getPeriodicInspectorComment()));
							periodicInspectionComment.setPeriodicInspection(periodicInspection);
							periodicInspectionComment.setSiteName(siteRepo.get().getSite());
							periodicInspectionComment.setInspectorEmail(siteRepo.get().getUserName());
							periodicInspectionComment.setViewerUserEmail(siteRepo.get().getAssignedTo());
							periodicInspectionComment.setViewerDate(LocalDateTime.now());
							periodicInspectionComment.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
							periodicInspectionComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
							periodicInspectionComment.setViewerUserName(userFullName.findByUserName(userName));
							periodicInspectorCommentRepo.add(periodicInspectionComment);
							periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
							return periodicInspection;
						} else {
							logger.error("Sending viewer comments faild");
							throw new InspectionException("Sending viewer comments faild");
						}
					}
				} else {
					logger.error("Given username not have access for comments");
					throw new InspectionException("Given username not have access for comments");
				}

			} else {
				logger.error("Siteinformation doesn't exist, try with different Site-Id");
				throw new InspectionException("Siteinformation doesn't exist, try with different Site-Id");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new InspectionException("Invalid Inputs");
		}
		return null;
	}
	
	private void sortingDateTime(List<PeriodicInspectionComment> listOfComments) {
		if (listOfComments.size() > 1) {
			Collections.sort(listOfComments, (o1, o2) -> o1.getViewerDate().compareTo(o2.getViewerDate()));
		}
	}
	
	private Integer checkNoOfComments(List<PeriodicInspectionComment> listOfComments) {
		Integer maxNum = 0;
		String approveRejectedFlag = "";
		for (PeriodicInspectionComment periodicInspectionComment : listOfComments) {
			if (periodicInspectionComment != null && maxNum <= periodicInspectionComment.getNoOfComment()) {
				maxNum = periodicInspectionComment.getNoOfComment();
				approveRejectedFlag = periodicInspectionComment.getApproveOrReject();
			}
		}
		if (approveRejectedFlag != null && approveRejectedFlag.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
			return maxNum + 1;
		} else {
			return maxNum;
		}
	}
	
	private Boolean checkInspectorViewer(String userName, String process, Optional<Site> siteRepo,
			Optional<PeriodicInspection> periodicInspectionRepo) throws InspectionException {
		Boolean flag = false;
		if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
			if (siteRepo.get().getUserName().equalsIgnoreCase(userName)
					&& periodicInspectionRepo.get().getUserName() != null
					&& siteRepo.get().getUserName().equalsIgnoreCase(periodicInspectionRepo.get().getUserName())) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersonsItr : sitePersons) {
					if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null) {
						viewerName = sitePersonsItr.getPersonInchargeEmail();
						return flag = true;
					}
				}
			} else {
				logger.error("Given userName not allowing for " + process + " comment");
				throw new InspectionException("Given userName not allowing for " + process + " comment");
			}

		} else if (process.equalsIgnoreCase(Constants.SEND_COMMENT) || process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {

			Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
			for (SitePersons sitePersonsItr : sitePersons) {
				if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null
						&& sitePersonsItr.getPersonInchargeEmail().equalsIgnoreCase(userName)) {
					return flag = true;
				} else {
					logger.debug("Given userName not allowing for " + process + " comment");
					throw new InspectionException("Given userName not allowing for " + process + " comment");
				}
			}
		}
		return flag;
	}
	
	private void addRemoveStatusInTesting(List<IpaoInspection> listIpaoInspection, Optional<TestingReport> testingRepo2)
			throws InspectionException {
		for (IpaoInspection ipaoInspectionItr : listIpaoInspection) {
			if (ipaoInspectionItr != null && ipaoInspectionItr.getLocationCount() != null) {
				try {
					Testing testingRepo = testInfoRepository.findByLocationCount(ipaoInspectionItr.getLocationCount());
					if (testingRepo != null&& testingRepo.getLocationCount().equals(ipaoInspectionItr.getLocationCount())) {
						testingRepo.setTestingStatus(ipaoInspectionItr.getInspectionFlag());
						testingRepo.setLocationName(ipaoInspectionItr.getLocationName());
						testingRepo.setLocationNumber(ipaoInspectionItr.getLocationNumber());
						testInfoRepository.save(testingRepo);
					}
					else {
				       Testing testing=new Testing();
						testing.setLocationCount(ipaoInspectionItr.getLocationCount());
						testing.setLocationName(ipaoInspectionItr.getLocationName());
						testing.setLocationNumber(ipaoInspectionItr.getLocationNumber());
						testing.setTestingReport(testingRepo2.get());
						testing.setTestingStatus(ipaoInspectionItr.getInspectionFlag());
//						testing.setTestDistRecords(updateDBdetailsTotesting.getTestDistRecordsDetails(testing,ipaoInspectionItr.getDbParentArray()));
						testInfoRepository.save(testing);
					}
					
					
				} catch (Exception e) {
					logger.debug("Please check removed Inspection Location data not available in PeriodicTesting"
							+ e.getMessage());
					throw new InspectionException(
							"Please check removed Inspection Location data not available in PeriodicTesting"
									+ e.getMessage());
				}
			}
		}
	}


//	private List<IpaoInspection> isNullLocationCount(List<IpaoInspection> ipaoInspection) {
//		List<IpaoInspection> ipaoInspectionList = new ArrayList<IpaoInspection>();
//		 for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
//			if (ipaoInspectionItr !=null && ipaoInspectionItr.getLocationCount() == null) {
//				ipaoInspectionItr.setLocationCount(new Random().nextInt(999999999));
//				ipaoInspectionList.add(ipaoInspectionItr);
//			}
//			ipaoInspectionList.add(ipaoInspectionItr);
//		}
//		return ipaoInspectionList;
//	}

//	private void findConsumerUnitLocation(List<ConsumerUnit> consumerUnitList) throws InspectionException {
//		for (ConsumerUnit consumerUnit : consumerUnitList) {
//			if (consumerUnit != null && consumerUnit.getLocation() != null) {
//				ConsumerUnit consumerLocation = inspectionConsumerUnitRepository
//						.findByLocation(consumerUnit.getLocation());
//				if (consumerLocation == null) {
//
//				} else {
//					logger.error("Given LocationName already present in ConsumerUnit,please try new LocationName");
//					throw new InspectionException(
//							"Given LocationName already present in ConsumerUnit,please try new LocationName");
//				}
//
//			} else {
//				logger.error("Please check Location Information in ConsumerUnit");
//				throw new InspectionException("Please check Location Information in ConsumerUnit");
//			}
//		}
//
//	}

	/**
	 * addRemoveStatusInTestDistRecords function first finding consumer R status then search corresponding 
	 *  testdistrecords it will set R status
	 * @param summaryObservation 
	 * @throws InspectionException 
	 * */
	private void addRemoveStatusInTestDistRecords(List<IpaoInspection> ipaoInspectionList,Integer siteId) throws InspectionException {
		Optional<Summary> summaryRepo=summaryRepository.findBySiteId(siteId);
		for (IpaoInspection ipaoInspection : ipaoInspectionList) {
			Testing testing=testInfoRepository.findByLocationCount(ipaoInspection.getLocationCount());
			for (DbParentArray dbParentArray : ipaoInspection.getDbParentArray()) {
				if (dbParentArray != null && dbParentArray.getDbParentFlag() != null
						&& dbParentArray.getConsumerUnit().get(0).getLocationCount() != null) {
					try {

						TestDistRecords testDistRecords = testDistRecordsRepository
								.findByLocationCount(dbParentArray.getConsumerUnit().get(0).getLocationCount());
						if (testDistRecords != null) {
							testDistRecords.setTestDistRecordStatus(dbParentArray.getDbParentFlag());
							if(testDistRecords.getTestDistribution()!=null) {
									testDistRecords.getTestDistribution().get(0).setDistributionBoardDetails(dbParentArray.getDistributionSourceDetails());
									testDistRecords.getTestDistribution().get(0).setReferance(dbParentArray.getDistributionBoard());
									testDistRecords.getTestDistribution().get(0).setLocation(dbParentArray.getDistributionLocation());
							}
						
							if(testDistRecords.getTestingInnerObservation()!=null) {
								  for(TestingInnerObservation testingInnerObservation:testDistRecords.getTestingInnerObservation()) {
								    	if(summaryRepo.isPresent()) {
								    		Summary summary=summaryRepo.get();
											summaryRepository.save(setStatusInSummaryObservation(summary,testDistRecords.getTestingInnerObservation()));
								    	}
								    	testingInnerObservation.setTestingInnerObservationStatus(testingInnerObservation.getTestingInnerObservationStatus());
							}
						    }
							testDistRecordsRepository.save(testDistRecords);
							
						}
						else {
							TestDistRecords newTestDistRecords=new TestDistRecords();
							List<TestDistribution> listOfTestDistribution = new ArrayList<TestDistribution>();
							TestDistribution testDistribution = new TestDistribution();
							testDistribution.setReferance(dbParentArray.getDistributionBoard());
							testDistribution.setLocation(dbParentArray.getDistributionLocation());
							testDistribution.setDistributionBoardDetails(dbParentArray.getDistributionSourceDetails());
							listOfTestDistribution.add(testDistribution);
							newTestDistRecords.setLocationCount(dbParentArray.getConsumerUnit().get(0).getLocationCount());
							newTestDistRecords.setTestDistRecordStatus(dbParentArray.getConsumerUnit().get(0).getConsumerStatus());
							newTestDistRecords.setTestDistribution(listOfTestDistribution);
							newTestDistRecords.setTesting(testing);
							testDistribution.setTestDistRecords(newTestDistRecords);
							testDistRecordsRepository.save(newTestDistRecords);
						}
					} catch (Exception e) {
						logger.error(
								"Please verify Removed consumerUnit records,Removed data not available in TestingDistrubtionRecords"
										+ e.getMessage());
						throw new InspectionException(
								"Please verify Removed consumerUnit records,Removed data not available in TestingDistrubtionRecords"
										+ e.getMessage());
					}

				}

			}
		}

	}



private Summary setStatusInSummaryObservation(Summary summary, List<TestingInnerObservation> testingInnerObservation) {
	for(TestingInnerObservation testingInnerObservation1:testingInnerObservation) {
		for(SummaryObservation summaryObservation:summary.getSummaryObservation()) {
			if(summaryObservation.getReferenceId()!=null&&summaryObservation.getReferenceId().equals(testingInnerObservation1.getTestingInnerObervationsId())) {
				summaryObservation.setObervationStatus("R");
			}
		}
	}
	
	
	return summary;
}

//	@Override
//	public String retrieveLocation(Integer consumerId, String distributionDetails, String reference, String location) {
//	
//        ConsumerUnit fetchLocationDetails = inspectionConsumerUnitRepository.findByDistributionBoardDetailsAndReferanceAndLocation(distributionDetails, reference, location);
////		return fetchLocationDetails != null ? fetchLocationDetails.getLocation(): "";	
//		
//		if(fetchLocationDetails !=null && fetchLocationDetails.getConsumerStatus().equalsIgnoreCase("R")) {
//			fetchLocationDetails = null;
//		}
//		logger.debug(
//				"Finding distributionDetails, referance ,location values is available in Isolation table :{}, Is Comparing ID is different: {}",
//				fetchLocationDetails != null, (fetchLocationDetails != null && consumerId != null
//						&& !consumerId.equals(fetchLocationDetails.getConsumerId())));
//		return fetchLocationDetails != null && (consumerId == null
//				|| (consumerId != null && !consumerId.equals(fetchLocationDetails.getConsumerId())))
//						? fetchLocationDetails.getLocation()
//						: "";
//		
//	}
	
//	@Override
//	public String retrieveIsolationLocation(Integer isolationId, String distributionDetails, String referance,
//			String location) {
//		IsolationCurrent getLocationDetails = inspectionIsolationRepository
//				.findByDistributionBoardDetailsAndReferanceAndLocation(distributionDetails, referance, location);
//		if(getLocationDetails !=null && getLocationDetails.getIsolationStatus().equalsIgnoreCase("R")) {
//			getLocationDetails = null;
//		}
//		logger.debug(
//				"Finding distributionDetails, referance ,location values is available in Isolation table :{}, Is Comparing ID is different: {}",
//				getLocationDetails != null, (getLocationDetails != null && isolationId != null
//						&& !isolationId.equals(getLocationDetails.getIsolationCurrentId())));
//		return getLocationDetails != null && (isolationId == null
//				|| (isolationId != null && !isolationId.equals(getLocationDetails.getIsolationCurrentId())))
//						? getLocationDetails.getLocation()
//						: "";
//
//	}
	
	@Override
	public String retrieveDbParentArrayLocation(String distributionBoard,
			String distributionLocation, String distributionSourceDetails){
		String dbParentFlag="A";
		String msg = "";
		DbParentArray dbParentArray = dbParentRepository.findByDistributionBoardAndDistributionLocationAndDistributionSourceDetailsAndDbParentFlag(distributionBoard, distributionLocation, distributionSourceDetails,dbParentFlag);
			if(dbParentArray != null && !dbParentArray.getDbParentFlag().equalsIgnoreCase("R")) {
				return  msg = "Location already exist";
			}else {
				return  msg = "You can proceed with this Location";
			}
		
	}
	
	/**
	 * addLocationCountInConsumerUnit function  finding cosumerunit then randomly added some digts number in locationcount
	 * @throws InspectionException 
	 * */	
	private List<ConsumerUnit> addLocationCountInConsumerUnit(List<ConsumerUnit> consumerUnitList) {
		List<ConsumerUnit> locationCountList = new ArrayList<ConsumerUnit>();
		 for (ConsumerUnit consumerUnit : consumerUnitList) {
			 consumerUnit.setLocationCount(new Random().nextInt(999999999));
			 locationCountList.add(consumerUnit);
		}
		return locationCountList;
	}	
    
	/**
	 * New Inspection Save Service
	 */
	@Override
	public void addNewInspectionDetails(PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		listOfComments = new ArrayList<PeriodicInspectionComment>();
		int i = 0;
		if (periodicInspection.getUserName() != null && periodicInspection.getSiteId() != null
				&& periodicInspection.getIpaoInspection() != null) {
			List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
			Optional<PeriodicInspection> periodicInspectionValue = inspectionRepository
					.findBySiteId(periodicInspection.getSiteId());
			if (!periodicInspectionValue.isPresent() || periodicInspectionValue.get().getStatus().equalsIgnoreCase("R")
					|| !periodicInspectionValue.get().getSiteId().equals(periodicInspection.getSiteId())) {
				if (ipaoInspection != null && ipaoInspection.size() > 0) {
					for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
						ipaoInspectionItr.setLocationCount(new Random().nextInt(999999999));
						// findConsumerUnitLocation(ipaoInspectionItr.getConsumerUnit());
						i++;
						if (i == ipaoInspection.size()) {
							periodicInspectionComment = new PeriodicInspectionComment();
							periodicInspectionComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
							periodicInspectionComment.setViewerFlag(Constants.INTIAL_FLAG_VALUE);
							periodicInspectionComment.setNoOfComment(1);
							periodicInspectionComment.setViewerDate(LocalDateTime.now());
							periodicInspectionComment.setPeriodicInspection(periodicInspection);
							listOfComments.add(periodicInspectionComment);
							periodicInspection.setPeriodicInspectorComment(listOfComments);
							periodicInspection.setCreatedDate(LocalDateTime.now());
							periodicInspection.setUpdatedDate(LocalDateTime.now());
							periodicInspection
									.setCreatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
							periodicInspection
									.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
							try {
								inspectionRepository.save(periodicInspection);
								logger.debug("InspectionDetails Successfully Saved in DB");
							} catch (Exception e) {
								logger.error("Not able to save Inspection data " + e.getMessage());
								throw new InspectionException("Not able to save Inspection data " + e.getMessage());
							}
							Optional<PeriodicInspection> periodicInspectionData = inspectionRepository
									.findBySiteId(periodicInspection.getSiteId());
							Optional<TestingReport> testingRepo = testingReportRepository
									.findBySiteId(periodicInspection.getSiteId());
							if (!testingRepo.isPresent()) {
								testingReportRepository
										.save(updateDBdetailsTotesting.saveTestingReport(periodicInspectionData));
							}

							siteDetails.updateSite(periodicInspection.getSiteId(), periodicInspection.getUserName(),
									"Step3 completed");
							logger.debug("Site Successfully Saved in DB");

							internSaveUpdate.updateSaveFlag(periodicInspection.getUserName(), "Inspection", "Save",
									periodicInspection.getSiteId());
							logger.debug("Updated successfully internSaveflag");
						}

					}
				} else {
					logger.error("SiteId already present");
					throw new InspectionException("SiteId already present");
				}

			} else {
				logger.error("Inspection data contains duplicate Object");
				throw new InspectionException("Inspection data contains duplicate Object");
			}

		} else {
			logger.error("Invalid input");
			throw new InspectionException("Invalid input");
		}

	}

	/**
	 * New Inspection Update Call
	 */
	@Override
	public void updateNewInspectionDetails(PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		if (periodicInspection != null && periodicInspection.getPeriodicInspectionId() != null
				&& periodicInspection.getPeriodicInspectionId() != 0 && periodicInspection.getSiteId() != null
				&& periodicInspection.getSiteId() != 0) {
			Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository
					.findById(periodicInspection.getPeriodicInspectionId());
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getSiteId().equals(periodicInspection.getSiteId())) {
				List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
				Optional<Summary> summaryRepo = summaryRepository.findBySiteId(periodicInspection.getSiteId());
				for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
					logger.debug("locationcount value adding for new location");
					// locationcount value adding for new location
					if (ipaoInspectionItr != null && ipaoInspectionItr.getLocationCount() == null) {
						ipaoInspectionItr.setLocationCount(new Random().nextInt(999999999));
					}
				}
				periodicInspection.setUpdatedDate(LocalDateTime.now());
				periodicInspection.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
				inspectionRepository.save(periodicInspection);
				logger.debug("Inspection successfully updated into DB");
				internSaveUpdate.updateSaveFlag(periodicInspection.getUserName(), "Inspection", "Update",
						periodicInspection.getSiteId());
				logger.debug("Updated successfully internSaveflag");
				siteDetails.updateSite(periodicInspection.getSiteId(), periodicInspection.getUserName(),
						"Step3 completed");
				logger.debug("Updated successfully site updatedUsername", periodicInspection.getUserName());
				Optional<PeriodicInspection> periodicInspectionRepoData = inspectionRepository
						.findById(periodicInspection.getPeriodicInspectionId());
				Optional<TestingReport> testingRepo = testingReportRepository
						.findBySiteId(periodicInspection.getSiteId());
				if (testingRepo.isPresent()) {
					addRemoveStatusInTesting(periodicInspectionRepoData.get().getIpaoInspection(), testingRepo);
//					addRemoveStatusInTestDistRecords(periodicInspectionRepoData.get().getIpaoInspection(),periodicInspectionRepoData.get().getSiteId());
				} else {
					testingReportRepository
							.save(updateDBdetailsTotesting.saveTestingReport(periodicInspectionRepoData));
				}
				if (summaryRepo.isPresent()) {
					Summary summary = summaryRepo.get();
					try {
						summaryRepository.save(setStatusInSummary(summary, periodicInspectionRepoData));
						logger.debug("Summary Observations Updated successfully", periodicInspection.getSiteId());
					} catch (Exception e) {
						logger.error("Summary Observations Update Failed" + e.getMessage());
						throw new InspectionException("Summary Observations Update Failed");
					}
				}
			} else {
				logger.error("Given SiteId and ReportId is Invalid");
				throw new InspectionException("Given SiteId and ReportId is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new InspectionException("Invalid inputs");
		}

	}
	
	@Override
	public void addNewInternInspectionDetails(PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		listOfComments = new ArrayList<PeriodicInspectionComment>();
		int i = 0;
		if (periodicInspection.getUserName() != null && periodicInspection.getSiteId() != null
				&& periodicInspection.getIpaoInspection() != null) {
			List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
			Optional<PeriodicInspection> periodicInspectionValue = inspectionRepository
					.findBySiteId(periodicInspection.getSiteId());
			if (!periodicInspectionValue.isPresent() || periodicInspectionValue.get().getStatus().equalsIgnoreCase("R")
					|| !periodicInspectionValue.get().getSiteId().equals(periodicInspection.getSiteId())) {
				if (ipaoInspection != null && ipaoInspection.size() > 0) {
					for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
						ipaoInspectionItr.setLocationCount(new Random().nextInt(999999999));
						// findConsumerUnitLocation(ipaoInspectionItr.getConsumerUnit());
						i++;
						if (i == ipaoInspection.size()) {
							periodicInspectionComment = new PeriodicInspectionComment();
							periodicInspectionComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
							periodicInspectionComment.setViewerFlag(Constants.INTIAL_FLAG_VALUE);
							periodicInspectionComment.setNoOfComment(1);
							periodicInspectionComment.setViewerDate(LocalDateTime.now());
							periodicInspectionComment.setPeriodicInspection(periodicInspection);
							listOfComments.add(periodicInspectionComment);
							periodicInspection.setPeriodicInspectorComment(listOfComments);
							periodicInspection.setCreatedDate(LocalDateTime.now());
							periodicInspection.setUpdatedDate(LocalDateTime.now());
							periodicInspection
									.setCreatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
							periodicInspection
									.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
							try {
								inspectionRepository.save(periodicInspection);
								logger.debug("InspectionDetails Successfully Saved in DB");
							} catch (Exception e) {
								logger.error("Not able to save Inspection data " + e.getMessage());
								throw new InspectionException("Not able to save Inspection data " + e.getMessage());
							}
							Optional<PeriodicInspection> periodicInspectionData = inspectionRepository
									.findBySiteId(periodicInspection.getSiteId());
							Optional<TestingReport> testingRepo = testingReportRepository
									.findBySiteId(periodicInspection.getSiteId());
							if (!testingRepo.isPresent()) {
								testingReportRepository
										.save(updateDBdetailsTotesting.saveTestingReport(periodicInspectionData));
							}

							siteDetails.updateSite(periodicInspection.getSiteId(), periodicInspection.getUserName(),
									"Step3 completed");
							logger.debug("Site Successfully Saved in DB");

							internSaveUpdate.updateSaveFlag(periodicInspection.getUserName(), "Inspection", "IntSave",
									periodicInspection.getSiteId());
							logger.debug("Updated successfully internSaveflag");
						}

					}
				} else {
					logger.error("SiteId already present");
					throw new InspectionException("SiteId already present");
				}

			} else {
				logger.error("Inspection data contains duplicate Object");
				throw new InspectionException("Inspection data contains duplicate Object");
			}

		} else {
			logger.error("Invalid input");
			throw new InspectionException("Invalid input");
		}

	}




@Override
	public void updateNewInternInspectionDetails(PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		if (periodicInspection != null && periodicInspection.getPeriodicInspectionId() != null
				&& periodicInspection.getPeriodicInspectionId() != 0 && periodicInspection.getSiteId() != null
				&& periodicInspection.getSiteId() != 0) {
			Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository
					.findById(periodicInspection.getPeriodicInspectionId());
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getSiteId().equals(periodicInspection.getSiteId())) {
				List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
				Optional<Summary> summaryRepo = summaryRepository.findBySiteId(periodicInspection.getSiteId());
				for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
					logger.debug("locationcount value adding for new location");
					// locationcount value adding for new location
					if (ipaoInspectionItr != null && ipaoInspectionItr.getLocationCount() == null) {
						ipaoInspectionItr.setLocationCount(new Random().nextInt(999999999));
					}
				}
				periodicInspection.setUpdatedDate(LocalDateTime.now());
				periodicInspection.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
				inspectionRepository.save(periodicInspection);
				logger.debug("Inspection successfully updated into DB");
				internSaveUpdate.updateSaveFlag(periodicInspection.getUserName(), "Inspection", "IntSave",
						periodicInspection.getSiteId());
				logger.debug("Updated successfully internSaveflag");
				siteDetails.updateSite(periodicInspection.getSiteId(), periodicInspection.getUserName(),
						"Step3 completed");
				logger.debug("Updated successfully site updatedUsername", periodicInspection.getUserName());
				Optional<PeriodicInspection> periodicInspectionRepoData = inspectionRepository
						.findById(periodicInspection.getPeriodicInspectionId());
				Optional<TestingReport> testingRepo = testingReportRepository
						.findBySiteId(periodicInspection.getSiteId());
				if (testingRepo.isPresent()) {
					addRemoveStatusInTesting(periodicInspectionRepoData.get().getIpaoInspection(), testingRepo);
//					addRemoveStatusInTestDistRecords(periodicInspectionRepoData.get().getIpaoInspection(),periodicInspectionRepoData.get().getSiteId());
				} else {
					testingReportRepository
							.save(updateDBdetailsTotesting.saveTestingReport(periodicInspectionRepoData));
				}
				if (summaryRepo.isPresent()) {
					Summary summary = summaryRepo.get();
					try {
						summaryRepository.save(setStatusInSummary(summary, periodicInspectionRepoData));
						logger.debug("Summary Observations Updated successfully", periodicInspection.getSiteId());
					} catch (Exception e) {
						logger.error("Summary Observations Update Failed" + e.getMessage());
						throw new InspectionException("Summary Observations Update Failed");
					}
				}
			} else {
				logger.error("Given SiteId and ReportId is Invalid");
				throw new InspectionException("Given SiteId and ReportId is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new InspectionException("Invalid inputs");
		}

	}


	
	/**
	 * Updating Distribution Board (inspection)
	 * @throws InspectionException 
	 * @throws
	 */
	@Override
	public DbParentArray updateDisributionBoard(DbParentArray dbParentArray, Integer ipaoInspectionId, Integer siteId) throws InspectionException {
		Optional<IpaoInspection> ipaoInspection = ipaoInspectionRepo.findById(ipaoInspectionId);
		logger.debug("Updating service Impl Called Finding IpaoInspection +"+ipaoInspectionRepo.findById(ipaoInspectionId));
		Optional<DbParentArray> dbParent = dbParentRepository.findById(dbParentArray.getDbParentID());
		if(dbParent.isPresent()) {
			dbParentArray.setIpaoInspectionDbParent(ipaoInspection.get());
			DbParentArray dbParentArr = dbParentRepository.save(dbParentArray);
			logger.info("DbParent is Updated");
			addTestDistRecords(dbParentArr, ipaoInspection.get(), siteId);
			Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);
			if(summaryRepo.isPresent()) {
				summaryRepository.save(updateDbObservation(dbParentArr,ipaoInspection.get(),summaryRepo.get()));
			}
			dbParentArr.setObsFormArrayB(findNonRemovedObject.findNonRemovedObsFormB(dbParentArr.getObsFormArrayB()));
			return dbParentArr;
		}else {
			logger.error("DbParent is not Present");
			return null;
		}
		
	}

	@Override
	public DbParentArray addDisributionBoard(DbParentArray dbParentArray, Integer ipaoInspectionId, Integer siteId)
			throws InspectionException {
		logger.debug("add DbParent called");
		Optional<IpaoInspection> ipaoInspection = ipaoInspectionRepo.findById(ipaoInspectionId);
		dbParentArray.setConsumerUnit(addLocationCountInConsumerUnit(dbParentArray.getConsumerUnit()));
		dbParentArray.setIpaoInspectionDbParent(ipaoInspection.get());
		try {
			DbParentArray dbParentArrayValue = dbParentRepository.save(dbParentArray);
			logger.info("DbParent Saved successfully");
			addTestDistRecords(dbParentArrayValue, ipaoInspection.get(), siteId);
			Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);
			if(summaryRepo.isPresent()) {
				summaryRepository.save(updateDbObservation(dbParentArrayValue, ipaoInspection.get(), summaryRepo.get()));
			   }
			return dbParentArrayValue;
		}catch (Exception e) {
			logger.error("DB Parent db is not saved"+e.getMessage());
			throw new InspectionException("DbParent not saved in DB");
		}
	}
	

	private void addTestDistRecords(DbParentArray dbParentArray, IpaoInspection ipaoInspection, Integer siteId)
			throws InspectionException {
		Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);
		Testing testing = testInfoRepository.findByLocationCount(ipaoInspection.getLocationCount());
		if (dbParentArray != null && dbParentArray.getDbParentFlag() != null
				&& dbParentArray.getConsumerUnit().get(0).getLocationCount() != null) {
			try {

				TestDistRecords testDistRecords = testDistRecordsRepository
						.findByLocationCount(dbParentArray.getConsumerUnit().get(0).getLocationCount());
				if (testDistRecords != null) {
					testDistRecords.setTestDistRecordStatus(dbParentArray.getDbParentFlag());
					if (testDistRecords.getTestDistribution() != null) {
						testDistRecords.getTestDistribution().get(0)
								.setDistributionBoardDetails(dbParentArray.getDistributionSourceDetails());
						testDistRecords.getTestDistribution().get(0).setReferance(dbParentArray.getDistributionBoard());
						testDistRecords.getTestDistribution().get(0)
								.setLocation(dbParentArray.getDistributionLocation());
					}

					if (testDistRecords.getTestingInnerObservation() != null) {
						for (TestingInnerObservation testingInnerObservation : testDistRecords
								.getTestingInnerObservation()) {
							if (summaryRepo.isPresent()) {
								Summary summary = summaryRepo.get();
								summaryRepository.save(setStatusInSummaryObservation(summary,
										testDistRecords.getTestingInnerObservation()));
							}
							testingInnerObservation.setTestingInnerObservationStatus(
									testingInnerObservation.getTestingInnerObservationStatus());
						}
					}
					testDistRecordsRepository.save(testDistRecords);

				} else {
					TestDistRecords newTestDistRecords = new TestDistRecords();
					List<TestDistribution> listOfTestDistribution = new ArrayList<TestDistribution>();
					TestDistribution testDistribution = new TestDistribution();
					testDistribution.setReferance(dbParentArray.getDistributionBoard());
					testDistribution.setLocation(dbParentArray.getDistributionLocation());
					testDistribution.setDistributionBoardDetails(dbParentArray.getDistributionSourceDetails());
					listOfTestDistribution.add(testDistribution);
					newTestDistRecords.setLocationCount(dbParentArray.getConsumerUnit().get(0).getLocationCount());
					newTestDistRecords
							.setTestDistRecordStatus(dbParentArray.getConsumerUnit().get(0).getConsumerStatus());
					newTestDistRecords.setTestDistribution(listOfTestDistribution);
					newTestDistRecords.setTesting(testing);
					testDistribution.setTestDistRecords(newTestDistRecords);
					testDistRecordsRepository.save(newTestDistRecords);
				}
			} catch (Exception e) {
				logger.error(
						"Please verify Removed consumerUnit records,Removed data not available in TestingDistrubtionRecords"
								+ e.getMessage());
				throw new InspectionException(
						"Please verify Removed consumerUnit records,Removed data not available in TestingDistrubtionRecords"
								+ e.getMessage());
			}
		}
	}
	
	@Override
	public DbParentArray deleteDistributionBoard(Integer dbParentID, Integer siteId) throws InspectionException {
		logger.debug("Delete DbParent Service impl called");
		try {
			Optional<DbParentArray> dbParentArray = dbParentRepository.findById(dbParentID);
			if (dbParentArray.isPresent()) {
				
				DbParentArray dbParentArr = dbParentArray.get();
				dbParentArr.setDbParentFlag("R");
				TestDistRecords testDistRecords = testDistRecordsRepository
						.findByLocationCount(dbParentArr.getConsumerUnit().get(0).getLocationCount());
				testDistRecords.setTestDistRecordStatus("R");
				testDistRecordsRepository.save(testDistRecords);
				logger.debug("DbParent removed successfully");
				Optional<Summary> summary = summaryRepository.findBySiteId(siteId);
				if(summary.isPresent()) {
					Optional<DbParentObservation> dbParentObs = dbParentObservationRepo.findByReferenceId(dbParentID);
					if(dbParentObs.isPresent()) {
						DbParentObservation dbParentObservation = dbParentObs.get();
						dbParentObservation.setDbParentStatus("R");
						logger.info("DbParent Observation Status Updated");
						dbParentObservationRepo.save(dbParentObservation);
					}
				}
				return dbParentRepository.save(dbParentArr);
			}
			return null;

		} catch (Exception e) {
			logger.error("DB ParentID is not matched");
			throw new InspectionException("Db parentID is not matched");
		}
	}

	@Override
	public SubDbParent updateSubDBParent(SubDbParent subDbParentArr, Integer siteId, Integer ipaoInspectionId) throws InspectionException {
		logger.debug("SubDb Update service impl called finding ipaoInspection IpaoInspec"+ipaoInspectionRepo.findById(ipaoInspectionId));
		Optional<IpaoInspection> ipaoInspection = ipaoInspectionRepo.findById(ipaoInspectionId);
		subDbParentArr.setIpaoInspectionSubDB(ipaoInspection.get());
		try {
			SubDbParent subDbParent = subDbParentRepository.save(subDbParentArr);
			logger.info("SubDBParent updated");
			Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);
			if(summaryRepo.isPresent()) {
				summaryRepository.save(updateSubDbObservation(subDbParent, ipaoInspection.get(), summaryRepo.get()));
			}
			subDbParent.setObsFormArrayC(findNonRemovedObject.findNonRemovedObsFormC(subDbParent.getObsFormArrayC()));
	  		return subDbParent;
		}catch (Exception e) {
			logger.error("subDB not updated "+e.getMessage());
			throw new InspectionException("kdsjb");
		}
		
	}

	@Override
	public SubDbParent addSubDistributionBoard(SubDbParent subDbParent, Integer ipaoInspectionId, Integer siteId) throws InspectionException {
		logger.debug("Add SubDb Service Impl method called");
		Optional<IpaoInspection> ipaoInspection = ipaoInspectionRepo.findById(ipaoInspectionId);
			subDbParent.setIpaoInspectionSubDB(ipaoInspection.get());
			try {
				SubDbParent subDbParentM = subDbParentRepository.save(subDbParent);
				logger.info("SubDb Saved Successfully");
				Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);
				if(summaryRepo.isPresent()) {
					summaryRepository.save(updateSubDbObservation(subDbParentM, ipaoInspection.get(), summaryRepo.get()));
				}
				return subDbParentM;
			}catch (Exception e) {
				logger.error("SubDB Parent not saved"+e.getMessage());
				throw new InspectionException("subDb not Saved");
			}
	}

	@Override
	public SubDbParent deleteSubDistributionBoard(Integer subDbParentID, Integer siteId) throws InspectionException {   
		Optional<SubDbParent> subDbParent = subDbParentRepository.findById(subDbParentID);
		try {
			if(subDbParent.isPresent()) {
		          SubDbParent subDBParentM = subDbParent.get();
		          subDBParentM.setSubDbParentFlag("R");
		          try {
		        	  logger.debug("SubDbParent is removed properly subDbParentId : {}",subDbParentID);
		        	  Optional<Summary> summary = summaryRepository.findBySiteId(siteId);
		        	  if(summary.isPresent()) {
		        		  Optional<SubDbObservation> subDbObservation = subDbObservationRepo.findByReferenceId(subDbParentID);
		        		  if(subDbObservation.isPresent()) {
		        			  SubDbObservation subDbObs = subDbObservation.get();
		        			  subDbObs.setSubDbObservationStatus("R");
		        			  logger.info("SubDbObservation status changed");
		        			  subDbObservationRepo.save(subDbObs);
		        		  }
		        	  }
		              return subDbParentRepository.save(subDBParentM);
		          }catch (Exception e) {
		            logger.error("SubDbParent not removed "+e.getMessage());
		            throw new InspectionException("SubDbParent not removed "+e.getMessage());
		          }
				} 
		}catch (Exception e) {
            logger.error("SubDbParent is not Present"+e.getMessage());
            throw new InspectionException("SubDBParent is not Present "+e.getMessage());
		}

		return null;
	}
}
