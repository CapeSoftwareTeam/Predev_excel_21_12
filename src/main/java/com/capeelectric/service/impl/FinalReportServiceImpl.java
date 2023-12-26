
package com.capeelectric.service.impl;

import java.text.Collator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.AllComponentObservation;
import com.capeelectric.model.FinalReport;
import com.capeelectric.model.InspectionOuterObservation;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.Site;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.repository.InstalReportDetailsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.repository.TestingRepository;
import com.capeelectric.service.FinalReportService;
import com.capeelectric.util.FindNonRemovedObject;
import com.capeelectric.util.UpdateDBdetailsTesting;

/**
 * This FinalReportServiceImpl class to doing retrieve_site and
 * retrieve_allFinalinformations based on siteId and userName
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class FinalReportServiceImpl implements FinalReportService {

	private static final Logger logger = LoggerFactory.getLogger(FinalReportServiceImpl.class);

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private InstalReportDetailsRepository instalReportDetailsRepository;

	@Autowired
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;

	@Autowired
	private InspectionRepository inspectionRepository;

	@Autowired
	private TestingReportRepository testingReportRepository;

	@Autowired
	private SummaryRepository summaryRepository;

	private FinalReport finalReport;
	
	@Autowired
	private FindNonRemovedObject findNonRemovedObject;
	
	@Autowired
	private UpdateDBdetailsTesting updateDBdetailsTotesting;

	/**
	 * @param userName and departmentName also string retrieveSiteDetails method to
	 *                 retrieve site details based on userName and departmentName
	 * @return List of sites
	 * 
	 */
	@Override
	public List<Site> retrieveListOfSite(String userName) throws FinalReportException {

		if (userName != null) {
			try {
				logger.info("Site fetching process started");
				return sortSiteDetailsBasedOnUpdatedDate(siteRepository.findByUserName(userName), userName);
			} catch (Exception e) {
				logger.info("Site fetching process faild");
				throw new FinalReportException("Fetching site process faild");
			}
		} else {
			throw new FinalReportException("Invaild Input");
		}
	}

	/**
	 * @param userName and siteId retrieveFinalReport method to retrieve
	 *                 InstallReport_Information,Supplycharacteristic,PriodicInspection,PriodicTesting
	 *                 and Summary record based on userName & SiteId
	 * @return finalReport model object
	 * 
	 */

	@Override
	public Optional<FinalReport> retrieveFinalReport(String userName, Integer siteId) throws FinalReportException {
		if (userName != null && siteId != null) {
			finalReport = new FinalReport();
			Optional<Site> siteDetails = siteRepository.findById(siteId);
			if (siteDetails.isPresent() && siteDetails.get() != null && siteDetails.get().getAllStepsCompleted() != null
					&& !siteDetails.get().getAllStepsCompleted().isEmpty()) {
				finalReport.setAllStepsCompleted(siteDetails.get().getAllStepsCompleted());
			}

			finalReport.setUserName(userName);
			finalReport.setSiteId(siteId);

			logger.debug("fetching process started for InstallReport_Information");
			Optional<ReportDetails> reportDetails = instalReportDetailsRepository.findBySiteId(siteId);
			logger.debug("InstallReport_Information fetching ended");

			logger.debug("fetching process started for SupplyCharacteristic");
			Optional<SupplyCharacteristics> supplyCharacteristics = supplyCharacteristicsRepository
					.findBySiteId(siteId);
			logger.debug("SupplyCharacteristic_fetching ended");

			logger.debug("fetching process started for PriodicInspection");
			Optional<PeriodicInspection> periodicInspection = inspectionRepository.findBySiteId(siteId);
			logger.debug("PriodicInspection_fetching ended");

			logger.debug("fetching process started for PriodicTesting");
			Optional<TestingReport> testingReport = testingReportRepository.findBySiteId(siteId);
			logger.debug("PriodicTesting_fetching ended");

			logger.debug("fetching process started for Summary");
			Optional<Summary> summary = summaryRepository.findBySiteId(siteId);
			logger.debug("Summary_fetching ended");

			if (reportDetails.isPresent() && reportDetails != null) {
				reportDetails.get().setSignatorDetails(
						findNonRemovedObject.findNonRemovedReport(reportDetails.get().getSignatorDetails()));
				finalReport.setReportDetails(reportDetails.get());
			}

			if (supplyCharacteristics.isPresent() && supplyCharacteristics != null) {

				supplyCharacteristics.get().setInstalLocationReport(
						findNonRemovedObject.findNonRemovedInstallLocation(supplyCharacteristics.get()));
				supplyCharacteristics.get().setBoundingLocationReport(
						findNonRemovedObject.findNonRemovedBondingLocation(supplyCharacteristics.get()));
				supplyCharacteristics.get().setEarthingLocationReport(
						findNonRemovedObject.findNonRemovedEarthingLocation(supplyCharacteristics.get()));
				supplyCharacteristics.get().setCircuitBreaker(findNonRemovedObject
						.findNonRemovedCircuitBreaker(supplyCharacteristics.get().getCircuitBreaker()));
				supplyCharacteristics.get().setSupplyParameters(findNonRemovedObject
						.findNonRemovedSupplyParameters(supplyCharacteristics.get().getSupplyParameters()));
				supplyCharacteristics.get().setSupplyOuterObservation(findNonRemovedObject
						.findNonRemovedSupplyOuterObservation(supplyCharacteristics.get().getSupplyOuterObservation()));
				finalReport.setSupplyCharacteristics(supplyCharacteristics.get());

			}
			if (periodicInspection.isPresent()) {
				// Inspection
				if (periodicInspection.get().getCreatedDate().toLocalDate()
						.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate())
						&& (periodicInspection.get().getStatus() == null || periodicInspection.get().getStatus() != null
								&& !periodicInspection.get().getStatus().equalsIgnoreCase("R"))) {

					periodicInspection.get().setStatus("R");
					inspectionRepository.save(periodicInspection.get());

				} else if (periodicInspection.get().getStatus() == null || periodicInspection.get().getStatus() != null
						&& !periodicInspection.get().getStatus().equalsIgnoreCase("R")) {
					periodicInspection.get().setIpaoInspection(
							findNonRemovedObject.findNonRemovedInspectionLocation(periodicInspection.get()));
					finalReport.setPeriodicInspection(periodicInspection.get());
				} else {
					finalReport.setPeriodicInspection(null);
				}

				// Testing

				if (testingReport.isPresent() && testingReport != null) {

					if (testingReport.get().getCreatedDate().toLocalDate()
							.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate())
							&& (testingReport.get().getStatus() == null || testingReport.get().getStatus() != null
									&& !testingReport.get().getStatus().equalsIgnoreCase("R"))) {
						testingReport.get().setStatus("R");
						testingReportRepository.save(testingReport.get());
					} else if (testingReport.get().getStatus() == null || testingReport.get().getStatus() != null
							&& !testingReport.get().getStatus().equalsIgnoreCase("R")) {
						testingReport.get().setTesting(
								findNonRemovedObject.findNonRemoveTesting(testingReport.get().getTesting(), siteId));
						finalReport.setTestingReport(testingReport.get());
					} else {
						 
                        //updating db, location details in testing table
						finalReport.setTestingReport(updateDBdetailsTotesting.saveTestingReport(periodicInspection));
					}

					if (summary.isPresent() && summary != null) {

						if (summary.get().getCreatedDate().toLocalDate()
								.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate())
								&& (summary.get().getStatus() == null || summary.get().getStatus() != null
										&& !summary.get().getStatus().equalsIgnoreCase("R"))) {
							summary.get().setStatus("R");
							summaryRepository.save(summary.get());

						} else if (summary.get().getStatus() == null || summary.get().getStatus() != null
								&& !summary.get().getStatus().equalsIgnoreCase("R")) {
							summary.get().setAllComponentObservation(allComponentObservation(siteId));
							finalReport.setSummary(summary.get());
						} else {
							finalReport.setSummary(null);
						}

						logger.debug("Successfully Five_Steps fetching Operation done");
						return Optional.of(finalReport);

					}
				}
			}
		} else {
			throw new FinalReportException("Invalid Input");
		}
		return Optional.of(finalReport);

	}

	public Optional<FinalReport> retrieveFinalReport(Integer siteId) throws FinalReportException {
		if (siteId != null) {
			finalReport = new FinalReport();
			Optional<Site> siteDetails = siteRepository.findById(siteId);
			if (siteDetails.isPresent() && siteDetails.get() != null && siteDetails.get().getAllStepsCompleted() != null
					&& !siteDetails.get().getAllStepsCompleted().isEmpty()) {
				finalReport.setAllStepsCompleted(siteDetails.get().getAllStepsCompleted());
			}

			finalReport.setUserName(siteDetails.get().getUserName());
			finalReport.setSiteId(siteId);

			logger.debug("fetching process started for InstallReport_Information");
			Optional<ReportDetails> reportDetails = instalReportDetailsRepository.findBySiteId(siteId);
			logger.debug("InstallReport_Information fetching ended");

			logger.debug("fetching process started for SupplyCharacteristic");
			Optional<SupplyCharacteristics> supplyCharacteristics = supplyCharacteristicsRepository
					.findBySiteId(siteId);
			logger.debug("SupplyCharacteristic_fetching ended");

			logger.debug("fetching process started for PriodicInspection");
			Optional<PeriodicInspection> periodicInspection = inspectionRepository.findBySiteId(siteId);
			logger.debug("PriodicInspection_fetching ended");

			logger.debug("fetching process started for PriodicTesting");
			Optional<TestingReport> testingReport = testingReportRepository.findBySiteId(siteId);
			logger.debug("PriodicTesting_fetching ended");

			logger.debug("fetching process started for Summary");
			Optional<Summary> summary = summaryRepository.findBySiteId(siteId);
			logger.debug("Summary_fetching ended");

			if (reportDetails.isPresent() && reportDetails != null) {
				reportDetails.get().setSignatorDetails(
						findNonRemovedObject.findNonRemovedReport(reportDetails.get().getSignatorDetails()));
				finalReport.setReportDetails(reportDetails.get());
			}

			if (supplyCharacteristics.isPresent() && supplyCharacteristics != null) {

				supplyCharacteristics.get().setInstalLocationReport(
						findNonRemovedObject.findNonRemovedInstallLocation(supplyCharacteristics.get()));
				supplyCharacteristics.get().setBoundingLocationReport(
						findNonRemovedObject.findNonRemovedBondingLocation(supplyCharacteristics.get()));
				supplyCharacteristics.get().setEarthingLocationReport(
						findNonRemovedObject.findNonRemovedEarthingLocation(supplyCharacteristics.get()));
				supplyCharacteristics.get().setCircuitBreaker(findNonRemovedObject
						.findNonRemovedCircuitBreaker(supplyCharacteristics.get().getCircuitBreaker()));
				supplyCharacteristics.get().setSupplyParameters(findNonRemovedObject
						.findNonRemovedSupplyParameters(supplyCharacteristics.get().getSupplyParameters()));
				supplyCharacteristics.get().setSupplyOuterObservation(findNonRemovedObject
						.findNonRemovedSupplyOuterObservation(supplyCharacteristics.get().getSupplyOuterObservation()));
				finalReport.setSupplyCharacteristics(supplyCharacteristics.get());

			}
			if (periodicInspection.isPresent() && periodicInspection != null) {
				// Inspection
				if (periodicInspection.get().getCreatedDate().toLocalDate()
						.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate())
						&& (periodicInspection.get().getStatus() == null || periodicInspection.get().getStatus() != null
								&& !periodicInspection.get().getStatus().equalsIgnoreCase("R"))) {

					periodicInspection.get().setStatus("R");
					inspectionRepository.save(periodicInspection.get());

				} else if (periodicInspection.get().getStatus() == null || periodicInspection.get().getStatus() != null
						&& !periodicInspection.get().getStatus().equalsIgnoreCase("R")) {
					periodicInspection.get().setIpaoInspection(
							findNonRemovedObject.findNonRemovedInspectionLocation(periodicInspection.get()));
					finalReport.setPeriodicInspection(periodicInspection.get());
				} else {
					 //updating db, location details in testing table
					finalReport.setTestingReport(updateDBdetailsTotesting.saveTestingReport(periodicInspection));
				}

				// Testing

				if (testingReport.isPresent() && testingReport != null) {

					if (testingReport.get().getCreatedDate().toLocalDate()
							.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate())
							&& (testingReport.get().getStatus() == null || testingReport.get().getStatus() != null
									&& !testingReport.get().getStatus().equalsIgnoreCase("R"))) {
						testingReport.get().setStatus("R");
						testingReportRepository.save(testingReport.get());
					} else if (testingReport.get().getStatus() == null || testingReport.get().getStatus() != null
							&& !testingReport.get().getStatus().equalsIgnoreCase("R")) {
						testingReport.get().setTesting(
								findNonRemovedObject.findNonRemoveTesting(testingReport.get().getTesting(), siteId));
						finalReport.setTestingReport(testingReport.get());
					}
//					else {
//						//updating db, location details in testing table
//						finalReport.setTestingReport(updateDBdetailsTotesting.saveTestingReport(periodicInspection));
//					}

					if (summary.isPresent() && summary != null) {

						if (summary.get().getCreatedDate().toLocalDate()
								.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate())
								&& (summary.get().getStatus() == null || summary.get().getStatus() != null
										&& !summary.get().getStatus().equalsIgnoreCase("R"))) {
							summary.get().setStatus("R");
							summaryRepository.save(summary.get());

						} else if (summary.get().getStatus() == null || summary.get().getStatus() != null
								&& !summary.get().getStatus().equalsIgnoreCase("R")) {
							summary.get().setAllComponentObservation(allComponentObservation(siteId));
							summary.get().setSummaryObservation(findNonRemovedObject.fileNonRemoveSummaryObservations(summary.get().getSummaryObservation()));
							finalReport.setSummary(summary.get());
						} else {
							finalReport.setSummary(null);
						}

						logger.debug("Successfully Five_Steps fetching Operation done");
						return Optional.of(finalReport);

					}
				}
				 else {
						//updating db, location details in testing table
//						finalReport.setTestingReport(testingReportRepository.save(updateDBdetailsTotesting.saveTestingReport(periodicInspection)));
				 }
			}
		} else {
			throw new FinalReportException("Invalid Input");
		}
		return Optional.of(finalReport);

	}

	@Override
	public List<Site> retrieveAllSites(String userName) throws FinalReportException {
		return sortSiteDetailsBasedOnUpdatedDateSuperAdmin((List<Site>)siteRepository.findAll(),userName);
	}
	
	private AllComponentObservation allComponentObservation(Integer siteId) {
		AllComponentObservation allComponentObservation = new AllComponentObservation();
		Optional<SupplyCharacteristics> supplyCharacteristics = supplyCharacteristicsRepository.findBySiteId(siteId);
		Optional<PeriodicInspection> periodicInspection = inspectionRepository.findBySiteId(siteId);
		Optional<TestingReport> testingReport = testingReportRepository.findBySiteId(siteId);

		if (supplyCharacteristics.isPresent() && supplyCharacteristics.get().getSupplyOuterObservation() != null) {
			allComponentObservation.setSupplyOuterObservation(findNonRemovedObject
					.findNonRemovedSupplyOuterObservation(supplyCharacteristics.get().getSupplyOuterObservation()));
		}
		if (periodicInspection.isPresent() && periodicInspection.get().getIpaoInspection() != null
				&& periodicInspection.get().getStatus() != null && !periodicInspection.get().getStatus().equalsIgnoreCase("R")
//				&& periodicInspection.get().getCreatedDate().toLocalDate()
//						.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate())
						
				) {

			List<IpaoInspection> nonRemovedInspectionLocation = findNonRemovedObject
					.findNonRemovedInspectionLocation(periodicInspection.get());
//			ArrayList<InspectionOuterObservation> outerObservationyList = new ArrayList<InspectionOuterObservation>();
//			for (IpaoInspection ipaoInspection : nonRemovedInspectionLocation) {
//				if (ipaoInspection.getInspectionOuterObervation() != null) {
//					for (InspectionOuterObservation inspectionOuterObservation : ipaoInspection.getInspectionOuterObervation()) {
//						outerObservationyList.add(inspectionOuterObservation);
//					}
//				}
//			}

			allComponentObservation.setIpaoInspection(nonRemovedInspectionLocation);
		}
		if (testingReport.isPresent() && periodicInspection.get().getStatus() != null && !testingReport.get().getStatus().equalsIgnoreCase("R")
//				&& testingReport.get().getCreatedDate().toLocalDate()
//						.isBefore(LocalDateTime.parse("2023-09-21T07:49:06.366").toLocalDate())
						) {
			allComponentObservation.setTestingInnerObservation(
					findNonRemovedObject.findNonRemoveTestingInnerObservationByReport(testingReport));
		}
		return allComponentObservation;
	}
	
	private List<Site> sortSiteDetailsBasedOnUpdatedDate(List<Site> siteDetails, String userName) {
		siteDetails.sort((o1, o2) -> o2.getUpdatedDate().compareTo(o1.getUpdatedDate()));
		return siteDetails;
	}
	
	private List<Site> sortSiteDetailsBasedOnUpdatedDateSuperAdmin(List<Site> siteDetails, String userName) {

		if (!userName.equalsIgnoreCase("awstesting@rushforsafety.com")) {
			siteDetails = siteDetails.stream().filter(site -> !site.getUserName().equalsIgnoreCase("awstesting@rushforsafety.com"))
					.collect(Collectors.toList());
		}

		siteDetails.sort((o1, o2) -> o2.getUpdatedDate().compareTo(o1.getUpdatedDate()));
		return siteDetails;
	}
}

