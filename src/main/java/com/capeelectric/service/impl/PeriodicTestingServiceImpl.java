package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SummaryObservation;
import com.capeelectric.model.TestDistRecords;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingInnerObservation;
import com.capeelectric.model.TestingReport;
import com.capeelectric.model.TestingReportComment;
import com.capeelectric.repository.DetailTestingRepository;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SummaryObservationRepo;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.repository.TestingRepository;
import com.capeelectric.service.PeriodicTestingService;
import com.capeelectric.util.Constants;
import com.capeelectric.util.FindNonRemovedObject;
import com.capeelectric.util.InternSaveUpdate;
import com.capeelectric.util.SiteDetails;
import com.capeelectric.util.UpdateDBdetailsTesting;
import com.capeelectric.util.UserFullName;

/**
 * This TestInfoServiceImpl service class doing save and retrieve operation based on Testing
 * @author capeelectricsoftware
 *
 */
@Service
public class PeriodicTestingServiceImpl implements PeriodicTestingService {

	private static final Logger logger = LoggerFactory.getLogger(PeriodicTestingServiceImpl.class);
	
	@Autowired
	private TestingReportRepository testingReportRepository;
	
	@Autowired
	private UserFullName userFullName;

	@Autowired
	private SiteRepository siteRepository;
	
	private TestingReportComment testingComment;
	
	private String viewerName;
	
	@Autowired
	private SiteDetails siteDetails;
	
	@Autowired
	private FindNonRemovedObject findNonRemovedObject;
	
	@Autowired 
	private SummaryRepository summaryRepository;
	
	@Autowired 
	private SummaryObservationRepo summaryObservationRepo;
	
	@Autowired 
	private DetailTestingRepository detailTestingRepository;
	
	@Autowired 
	private InternSaveUpdate internSaveUpdate;
	
	@Autowired
	private TestingRepository testingRepository;
	
	@Autowired
	private InspectionRepository inspectionRepository;
	
	@Autowired
	private UpdateDBdetailsTesting updateDBdetailsTesting;
		
	/**
	 * @param Testing
	 * addTestingReport method to Testing object will be storing corresponding tables
	 * @throws CompanyDetailsException 
	*/
	@Transactional
	@Override
	public void addTestingReport(TestingReport testingReport) throws PeriodicTestingException, CompanyDetailsException {
		int i = 0;
		List<TestingReportComment> listOfComments = new ArrayList<TestingReportComment>();
		if (testingReport.getUserName() != null && testingReport.getSiteId() != null) {
			Optional<TestingReport> testingRepo = testingReportRepository.findBySiteId(testingReport.getSiteId());
			if (!testingRepo.isPresent() || !testingRepo.get().getSiteId().equals(testingReport.getSiteId()) || !testingRepo.get().getStatus().equalsIgnoreCase("R")) {
				List<Testing> testing = testingReport.getTesting();
				if (testing != null && testing.size() > 0) {
					for (Testing testingItr : testing) {

						if (testingItr != null && checkAllObject(testingItr.getTestDistRecords())
								&& testingReport.getTestIncomingDistribution() != null
								&& testingReport.getTestIncomingDistribution().size() > 0) {
							i++;
							if (i == testing.size()) {
								testingComment = new TestingReportComment();
								testingComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
								testingComment.setViewerFlag(Constants.INTIAL_FLAG_VALUE);
								testingComment.setNoOfComment(1);
								testingComment.setViewerDate(LocalDateTime.now());
								testingComment.setTestingReport(testingReport);
								listOfComments.add(testingComment);
								testingReport.setTestingComment(listOfComments);
								testingReport.setCreatedDate(LocalDateTime.now());
								testingReport.setCreatedBy(userFullName.findByUserName(testingReport.getUserName()));
								testingReport.setUpdatedDate(LocalDateTime.now());
								testingReport.setUpdatedBy(userFullName.findByUserName(testingReport.getUserName()));
								testingReportRepository.save(testingReport);
								logger.debug("Testing Details Successfully Saved in DB");
								siteDetails.updateSite(testingReport.getSiteId(), testingReport.getUserName(),"Step4 completed");
								logger.debug("Updated successfully site updatedUsername",testingReport.getUserName());
								
								internSaveUpdate.updateSaveFlag(testingReport.getUserName(), "Testing", "Save", testingReport.getSiteId());
								logger.debug("Updated successfully internSaveflag");
							}

						} else {
							logger.error("Please fill all the fields before clicking next button");
							throw new PeriodicTestingException(
									"Please fill all the fields before clicking next button");
						}

					}
				} else {
					logger.error("Testing data contains duplicate Object");
					throw new PeriodicTestingException("Testing data contains duplicate Object");
				}
			} else {
				logger.error("Site-Id Already Present");
				throw new PeriodicTestingException("Site-Id Already Present");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new PeriodicTestingException("Invalid Inputs");
		}
	}
	
	/**
	 * @param Testing
	 * addTestingReport method to Testing object will be storing corresponding tables
	 * @throws CompanyDetailsException 
	*/
	@Transactional
	@Override
	public void addInternTestingReport(TestingReport testingReport) throws PeriodicTestingException, CompanyDetailsException {
		int i = 0;
		List<TestingReportComment> listOfComments = new ArrayList<TestingReportComment>();
		if (testingReport.getUserName() != null && testingReport.getSiteId() != null) {
			Optional<TestingReport> testingRepo = testingReportRepository.findBySiteId(testingReport.getSiteId());
			if (!testingRepo.isPresent() || (testingRepo.get().getSiteId() !=null && !testingRepo.get().getSiteId().equals(testingReport.getSiteId())) || (testingRepo.get().getStatus()!=null && !testingRepo.get().getStatus().equalsIgnoreCase("R"))) {
								testingComment = new TestingReportComment();
								testingComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
								testingComment.setViewerFlag(Constants.INTIAL_FLAG_VALUE);
								testingComment.setNoOfComment(1);
								testingComment.setViewerDate(LocalDateTime.now());
								testingComment.setTestingReport(testingReport);
								listOfComments.add(testingComment);
								testingReport.setTestingComment(listOfComments);
								testingReport.setCreatedDate(LocalDateTime.now());
								testingReport.setCreatedBy(userFullName.findByUserName(testingReport.getUserName()));
								testingReport.setUpdatedDate(LocalDateTime.now());
								testingReport.setUpdatedBy(userFullName.findByUserName(testingReport.getUserName()));
								testingReportRepository.save(testingReport);
								logger.debug("Testing Details Successfully Saved in DB");
								siteDetails.updateSite(testingReport.getSiteId(), testingReport.getUserName(),"Step4 completed");
								logger.debug("Updated successfully site updatedUsername",testingReport.getUserName());
								
								internSaveUpdate.updateSaveFlag(testingReport.getUserName(), "Testing", "IntSave", testingReport.getSiteId());
								logger.debug("Updated successfully internSaveflag");

			} else {
				logger.error("Site-Id Already Present");
				throw new PeriodicTestingException("Site-Id Already Present");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new PeriodicTestingException("Invalid Inputs");
		}
	}
	

	/**
	 * @param userName,siteId
	 * retrieveTestingReport method to retrieve based on userName and siteId
	 * @return Optional<Testing>
	 */
	@Override
	public TestingReport retrieveTestingReport(String userName, Integer siteId) throws PeriodicTestingException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			TestingReport testingReportRepo = testingReportRepository.findByUserNameAndSiteId(userName, siteId);
			if (testingReportRepo != null) {
				if (testingReportRepo.getCreatedDate().toLocalDate()
						.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate()) && (testingReportRepo.getStatus() == null || testingReportRepo.getStatus() != null
								&& !testingReportRepo.getStatus().equalsIgnoreCase("R"))) {
					testingReportRepo.setStatus("R");
					testingReportRepository.save(testingReportRepo);
					return new TestingReport();

				} else if (testingReportRepo.getStatus() == null || testingReportRepo.getStatus() != null
						&& !testingReportRepo.getStatus().equalsIgnoreCase("R")) {
					testingReportRepo.setStatus("U");
					testingReportRepo.setTesting(
							findNonRemovedObject.findNonRemoveTesting(testingReportRepo.getTesting(), siteId));
 					sortingDateTime(testingReportRepo.getTestingComment());
					return testingReportRepo;
				} else {
					return new TestingReport();
				}
			} else {
				logger.error("Given UserName & Site doesn't exist Testing");
				throw new PeriodicTestingException("Given UserName & Site doesn't exist Testing");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new PeriodicTestingException("Invalid Inputs");
		}
	}
	
	@Override
	public TestingReport retrieveTestingReport(Integer siteId) throws PeriodicTestingException {
		if (siteId != null && siteId != 0) {
			Optional<TestingReport> testingReportRepoData = testingReportRepository.findBySiteId(siteId);
			TestingReport testingReportRepo = testingReportRepoData.get();

			if (testingReportRepo == null) {

				Optional<PeriodicInspection> inspection = inspectionRepository.findBySiteId(siteId);
				if (inspection.isPresent()) {
					updateDBdetailsTesting.saveTestingReport(inspection);
				}
			} 
			
			if (testingReportRepo.getCreatedDate().toLocalDate()
					.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate()) && (testingReportRepo.getStatus() == null || testingReportRepo.getStatus() != null
							&& !testingReportRepo.getStatus().equalsIgnoreCase("R"))) {
				testingReportRepoData.get().setStatus("R");
				testingReportRepository.save(testingReportRepoData.get());
				return new TestingReport();

			} else if (testingReportRepo.getStatus() == null || testingReportRepo.getStatus() != null
					&& !testingReportRepo.getStatus().equalsIgnoreCase("R")) {
				testingReportRepo.setStatus("U");
				testingReportRepo.setTesting(
						findNonRemovedObject.findNonRemoveTesting(testingReportRepo.getTesting(), siteId));
					sortingDateTime(testingReportRepo.getTestingComment());
				return testingReportRepo;
			} else {
				return new TestingReport();
			}
//			else {
//				logger.error("Given UserName & Site doesn't exist Testing");
//				throw new PeriodicTestingException("Given UserName & Site doesn't exist Testing");
//			}
		} else {
			logger.error("Invalid Inputs");
			throw new PeriodicTestingException("Invalid Inputs");
		}
	}
	


	/**
	 * @reportId,siteId must required
	 * @param TestingReport Object
	 * updatePeriodicTesting method to finding the given TestingReportId is available or not in DB,
	 * if available only allowed for updating 
	 * @throws CompanyDetailsException 
	 * 
	*/
	@Transactional
	@Override
	public void updatePeriodicTesting(TestingReport testingReport) throws PeriodicTestingException, CompanyDetailsException {
		if (testingReport != null && testingReport.getTestingReportId() != null
				&& testingReport.getTestingReportId() != 0 && testingReport.getSiteId() != null
				&& testingReport.getSiteId() != 0) {
			Optional<TestingReport> periodicInspectionRepo = testingReportRepository
					.findById(testingReport.getTestingReportId());
//			Optional<Summary> summaryRepo=summaryRepository.findBySiteId(testingReport.getSiteId());
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getSiteId().equals(testingReport.getSiteId())) {
				testingReport.setUpdatedDate(LocalDateTime.now());
				testingReport.setUpdatedBy(userFullName.findByUserName(testingReport.getUserName()));
				testingReportRepository.save(testingReport);
				internSaveUpdate.updateSaveFlag(testingReport.getUserName(), "Testing", "Update",testingReport.getSiteId());
				logger.debug("Updated successfully internSaveflag");
				
				siteDetails.updateSite(testingReport.getSiteId(), testingReport.getUserName(),"Step4 completed");
//				Optional<TestingReport> periodicTetsingRepo = testingReportRepository
//						.findById(testingReport.getTestingReportId());
//				if(summaryRepo.isPresent()) {
//					Summary summary=summaryRepo.get();
//					try {
//						summaryRepository.save(setStatusInSummary(summary,periodicTetsingRepo));
//					}catch (Exception e) {
//						logger.error("Summary Observations Update Failed"+e.getMessage());
//						throw new PeriodicTestingException("Summary Observations Update Failed");
//					}
//					
//				}
			} else {
				logger.error("Given SiteId and ReportId is Invalid");
				throw new PeriodicTestingException("Given SiteId and ReportId is Invalid");
			}

		} else {
			logger.error("Invalid inputs");
			throw new PeriodicTestingException("Invalid inputs");
		}
	}
	
	/**
	 * @reportId,siteId must required
	 * @param TestingReport Object
	 * updatePeriodicTesting method to finding the given TestingReportId is available or not in DB,
	 * if available only allowed for updating 
	 * @throws CompanyDetailsException 
	 * 
	*/
	@Transactional
	@Override
	public void updateInternPeriodicTesting(TestingReport testingReport) throws PeriodicTestingException, CompanyDetailsException {
		if (testingReport != null && testingReport.getTestingReportId() != null
				&& testingReport.getTestingReportId() != 0 && testingReport.getSiteId() != null
				&& testingReport.getSiteId() != 0) {
			Optional<TestingReport> periodicInspectionRepo = testingReportRepository
					.findById(testingReport.getTestingReportId());
//			Optional<Summary> summaryRepo=summaryRepository.findBySiteId(testingReport.getSiteId());
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getSiteId().equals(testingReport.getSiteId())) {
				
				testingReport.setUpdatedDate(LocalDateTime.now());
				testingReport.setUpdatedBy(userFullName.findByUserName(testingReport.getUserName()));
				testingReportRepository.save(testingReport);
				internSaveUpdate.updateSaveFlag(testingReport.getUserName(), "Testing", "IntSave",testingReport.getSiteId());
				logger.debug("Updated successfully internSaveflag");
				
				siteDetails.updateSite(testingReport.getSiteId(), testingReport.getUserName(),"Step4 completed");
//				Optional<TestingReport> periodicTetsingRepo = testingReportRepository
//						.findById(testingReport.getTestingReportId());
//				if(summaryRepo.isPresent()) {
//					Summary summary=summaryRepo.get();
//					try {
//						summaryRepository.save(setStatusInSummary(summary,periodicTetsingRepo));
//					}catch (Exception e) {
//						logger.error("Summary Observations Update Failed"+e.getMessage());
//						throw new PeriodicTestingException("Summary Observations Update Failed");
//					}
//					
//				}
			} else {
				logger.error("Given SiteId and ReportId is Invalid");
				throw new PeriodicTestingException("Given SiteId and ReportId is Invalid");
			}

		} else {
			logger.error("Invalid inputs");
			throw new PeriodicTestingException("Invalid inputs");
		}
	}
	
	private Summary setStatusInSummary(Summary summary,Optional<Testing> testing) throws PeriodicTestingException {
		 
//			for (Testing testing : testing.get().getTesting()) {
				for (TestDistRecords testDistRecords : testing.get().getTestDistRecords()) {
					for (TestingInnerObservation testingInnerObservation : testDistRecords
							.getTestingInnerObservation()) {

						SummaryObservation summaryObservation1 = new SummaryObservation();
						SummaryObservation summaryAvilable = isSUmmaryAvilable(summary, testingInnerObservation);
						if (summaryAvilable != null) {
							logger.debug("Update Testing Observation in Summary Observation Starts");
							summaryAvilable
									.setObervationStatus(testingInnerObservation.getTestingInnerObservationStatus());
							summaryAvilable.setObservations(testingInnerObservation.getObservationDescription());
							logger.debug("Update Testing Observation in Summary Observation Ends");
						} else {
							logger.debug("Add new Testing Observation in Summary Observation Starts");
							summaryObservation1.setReferenceId(testingInnerObservation.getTestingInnerObervationsId());
							summaryObservation1
									.setObervationStatus(testingInnerObservation.getTestingInnerObservationStatus());
							summaryObservation1.setObservations(testingInnerObservation.getObservationDescription());
							summaryObservation1.setSummary(summary);
							summaryObservation1.setObservationComponentDetails(
									testingInnerObservation.getObservationComponentDetails());
							summary.getSummaryObservation().add(summaryObservation1);
							logger.debug("Add new Testing Observation in Summary Observation Ends");
						}
					}
				}
//			}
		return summary;
		
	}

	private SummaryObservation isSUmmaryAvilable(Summary summary, TestingInnerObservation testingInnerObservation) {
		for (SummaryObservation summaryObservation : summary.getSummaryObservation()) {
			if(summaryObservation.getReferenceId()!=null && summaryObservation.getReferenceId().equals(testingInnerObservation.getTestingInnerObervationsId())) {
				logger.debug("Testing Observation Id Already in Summary Observations");
				return summaryObservation;	
			}
			
		}
		 return null;
		
	}

	@Override
	public void sendComments(String userName, Integer siteId, TestingReportComment testingReportComment)
			throws PeriodicTestingException {

		TestingReport testingReport = verifyCommentsInfo(userName, siteId, testingReportComment, Constants.SEND_COMMENT);
		if (testingReport != null) {
			testingReportRepository.save(testingReport);
		} else {
			logger.error("Testing-Information doesn't exist for given Site-Id");
			throw new PeriodicTestingException("Testing-Information doesn't exist for given Site-Id");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, TestingReportComment testingReportComment)
			throws PeriodicTestingException {
		TestingReport testingReport = verifyCommentsInfo(inspectorUserName, siteId, testingReportComment, Constants.REPLY_COMMENT);
		if (testingReport != null) {
			testingReportRepository.save(testingReport);
			return viewerName;
		} else {
			logger.error("Testing-Information doesn't exist for given Site-Id");
			throw new PeriodicTestingException("Testing-Information doesn't exist for given Site-Id");
		}
	}
	
	@Override
	public void approveComments(String userName, Integer siteId, TestingReportComment testingReportComment)
			throws PeriodicTestingException {
		TestingReport testingReport = verifyCommentsInfo(userName, siteId, testingReportComment, Constants.APPROVE_REJECT_COMMENT);
		if (testingReport != null) {
			testingReportRepository.save(testingReport);
		} else {
			logger.error("Testing-Information doesn't exist for given Site-Id");
			throw new PeriodicTestingException("Testing-Information doesn't exist for given Site-Id");
		}
	}
	
	private TestingReport verifyCommentsInfo(String userName, Integer siteId, TestingReportComment testingReportComment,
			String process) throws PeriodicTestingException {

		Boolean flagInspectionComment = true;
		if (userName != null && siteId != null && testingReportComment != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)
					&& siteRepo.get().getAssignedTo() != null && siteRepo.get().getUserName() != null
					&& siteRepo.get().getSite() != null) {
				Optional<TestingReport> testingReportRepo = testingReportRepository.findBySiteId(siteId);
				if (testingReportRepo.isPresent() && testingReportRepo.get() != null
						&& checkInspectorViewer(userName, process, siteRepo, testingReportRepo)) {
					TestingReport testingReport = testingReportRepo.get();
					testingReport.setUpdatedDate(LocalDateTime.now());
					testingReport.setUpdatedBy(userName);
					List<TestingReportComment> testingReportCommentRepo = testingReport.getTestingComment();

					for (TestingReportComment testingReportCommentItr : testingReportCommentRepo) {
						if (testingReportCommentItr != null && testingReportCommentItr.getCommentsId() != null
								&& testingReportCommentItr.getCommentsId()
										.equals(testingReportComment.getCommentsId())) {
							flagInspectionComment = false;

							testingReportCommentItr.setTestingReport(testingReport);

							if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
								testingReportCommentItr.setSiteName(siteRepo.get().getSite());
								testingReportCommentItr.setInspectorEmail(siteRepo.get().getUserName());
								testingReportCommentItr.setViewerUserEmail(siteRepo.get().getAssignedTo());
								testingReportCommentItr.setViewerDate(LocalDateTime.now());
								testingReportCommentItr.setViewerComment(testingReportComment.getViewerComment());
								testingReportCommentItr.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
								testingReportCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								testingReportCommentRepo.add(testingReportCommentItr);
								testingReport.setTestingComment(testingReportCommentRepo);
								return testingReport;
							}
							if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
								testingReportCommentItr.setInspectorDate(LocalDateTime.now());
								testingReportCommentItr.setInspectorUserName(userFullName.findByUserName(userName));
								testingReportCommentItr.setInspectorComment(testingReportComment.getInspectorComment());
								testingReportCommentItr.setInspectorFlag(Constants.INCREASED_FLAG_VALUE);
								testingReportCommentRepo.add(testingReportCommentItr);
								testingReport.setTestingComment(testingReportCommentRepo);
								return testingReport;
							}
							if (process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
								testingReportCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								testingReportCommentItr.setApproveOrReject(testingReportComment.getApproveOrReject());
								testingReportCommentRepo.add(testingReportCommentItr);
								testingReport.setTestingComment(testingReportCommentRepo);
								return testingReport;
							}
						}
					}
					if (flagInspectionComment) {

						if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
							testingReportComment.setNoOfComment(checkNoOfComments(testingReport.getTestingComment()));
							testingReportComment.setTestingReport(testingReport);
							testingReportComment.setSiteName(siteRepo.get().getSite());
							testingReportComment.setInspectorEmail(siteRepo.get().getUserName());
							testingReportComment.setViewerUserEmail(siteRepo.get().getAssignedTo());
							testingReportComment.setViewerDate(LocalDateTime.now());
							testingReportComment.setViewerUserName(userFullName.findByUserName(userName));
							testingReportComment.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
							testingReportComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
							testingReportCommentRepo.add(testingReportComment);
							testingReport.setTestingComment(testingReportCommentRepo);
							return testingReport;
						} else {
							logger.error("Sending viewer comments faild");
							throw new PeriodicTestingException("Sending viewer comments faild");
						}
					}
				} else {
					logger.error("Given username not have access for comments");
					throw new PeriodicTestingException("Given username not have access for comments");
				}

			} else {
				logger.error("Siteinformation doesn't exist, try with different Site-Id");
				throw new PeriodicTestingException("Siteinformation doesn't exist, try with different Site-Id");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new PeriodicTestingException("Invalid Inputs");
		}
		return null;
	}
	
	private void sortingDateTime(List<TestingReportComment> listOfComments) {
		if (listOfComments.size() > 1) {
			Collections.sort(listOfComments, (o1, o2) -> o1.getViewerDate().compareTo(o2.getViewerDate()));
		}
	}
	
	private Integer checkNoOfComments(List<TestingReportComment> listOfComments) {
		Integer maxNum = 0;
		String approveRejectedFlag = "";
		for (TestingReportComment testingReportCommentItr : listOfComments) {
			if (testingReportCommentItr != null && testingReportCommentItr.getNoOfComment() != null
					&& maxNum <= testingReportCommentItr.getNoOfComment()) {
				maxNum = testingReportCommentItr.getNoOfComment();
				approveRejectedFlag = testingReportCommentItr.getApproveOrReject();
			}
		}
		if (approveRejectedFlag != null && approveRejectedFlag.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
			return maxNum + 1;
		} else {
			return maxNum;
		}
	}
	
	private Boolean checkInspectorViewer(String userName, String process, Optional<Site> siteRepo,
			Optional<TestingReport> testingReportRepo) throws PeriodicTestingException {
		Boolean flag = false;
		if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
			if (siteRepo.get().getUserName().equalsIgnoreCase(userName)
					&& testingReportRepo.get().getUserName() != null
					&& siteRepo.get().getUserName().equalsIgnoreCase(testingReportRepo.get().getUserName())) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersonsItr : sitePersons) {
					if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null) {
						viewerName = sitePersonsItr.getPersonInchargeEmail();
						return flag = true;
					}
				}
			} else {
				logger.error("Given userName not allowing for " + process + " comment");
				throw new PeriodicTestingException("Given userName not allowing for " + process + " comment");
			}

		} else if (process.equalsIgnoreCase(Constants.SEND_COMMENT) || process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {

			Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
			for (SitePersons sitePersonsItr : sitePersons) {
				if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null
						&& sitePersonsItr.getPersonInchargeEmail().equalsIgnoreCase(userName)) {
					return flag = true;
				} else {
					logger.error("Given userName not allowing for " + process + " comment");
					throw new PeriodicTestingException("Given userName not allowing for " + process + " comment");
				}
			}
		}
		return flag;
	}
	

	private Boolean checkAllObject(List<TestDistRecords> testDistRecords) {

		Boolean flag = false;

		for (TestDistRecords testDistRecord : testDistRecords) {
			if (testDistRecord != null && testDistRecord.getTestDistribution() != null
					&& testDistRecord.getTestingRecords() != null && testDistRecord.getTestDistribution().size() > 0
					&& testDistRecord.getTestingRecords().size() > 0) {
				flag = true;
			}
		}
		return flag;

	}

	@Override
	public TestDistRecords updateDetailTesting(TestDistRecords testDistRecords,Integer testingId,Integer siteId) throws PeriodicTestingException {
		Optional<Testing> testing=testingRepository.findById(testingId);
		logger.info("TestDistribution Successfully Saved in DB");
		testDistRecords.setTesting(testing.get());
		TestDistRecords testingDist=detailTestingRepository.save(testDistRecords);
		testingDist.setTestingRecords(findNonRemovedObject.findNonRemoveTestingRecord(testingDist.getTestingRecords(),siteId));
		testingDist.setTestingInnerObservation(findNonRemovedObject.findNonRemoveTestingInnerObservationList(testingDist.getTestingInnerObservation()));
		Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);
		if(summaryRepo.isPresent()) {
			Summary summary=summaryRepo.get();
			try {
				summaryRepository.save(setStatusInSummary(summary,testing));
				logger.info("Summary updated done for detailed testing observation");
			}catch (Exception e) {
				logger.error("Detailed testing observation faild in Summary "+e.getMessage());
				throw new PeriodicTestingException("Observations Update Failed"+e.getMessage());
			}
			
		}
		return testingDist;
	}
 
}
