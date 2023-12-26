package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.InspectionException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.DbParentArray;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.PeriodicInspectionComment;
import com.capeelectric.model.SubDbParent;
import com.capeelectric.service.InspectionService;
import com.capeelectric.util.SendReplyComments;
/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v2")
public class InspectionController {

	private static final Logger logger = LoggerFactory.getLogger(InspectionController.class);

	@Autowired
	private InspectionService inspectionService;
	
	@Autowired
	private SendReplyComments sendReplyComments;

	@PostMapping("/lv/addInspectionDetails")
	public ResponseEntity<String> addInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		logger.info("called addInspectionDetails function UserName : {},SiteId : {}", periodicInspection.getUserName(),
				periodicInspection.getSiteId());
		inspectionService.addInspectionDetails(periodicInspection);
		logger.debug("Ended addInspectionDetails function");
		return new ResponseEntity<String>("Inspection Details Are Successfully Saved",HttpStatus.CREATED);
	}
	
	@PostMapping("/lv/addInspectionDetails/intsave")
	public ResponseEntity<String> addInternInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		logger.info("called addInternInspectionDetails function UserName : {},SiteId : {}", periodicInspection.getUserName(),
				periodicInspection.getSiteId());
		inspectionService.addInternInspectionDetails(periodicInspection);
		logger.debug("Ended addInternInspectionDetails function");
		return new ResponseEntity<String>("Inspection Details Are Successfully Saved",HttpStatus.CREATED);
	}
	
	@GetMapping("/lv/retrieveInspectionDetails/{userName}/{siteId}")
	public ResponseEntity<PeriodicInspection> retrieveInspectionDetails(@PathVariable String userName,
			@PathVariable Integer siteId) throws InspectionException {
		logger.info("called addInspectionDetails function UserName : {},SiteId : {}", userName, siteId);
		return new ResponseEntity<PeriodicInspection>(inspectionService.retrieveInspectionDetails(userName, siteId),
				HttpStatus.OK);
	}
	
	@GetMapping("/lv/retrieveInspectionDetails/{siteId}")
	public ResponseEntity<PeriodicInspection> retrieveInspectionDetailsForSiteId(@PathVariable Integer siteId) throws InspectionException {
		logger.info("called retrieveInspectionDetailsForSiteId function SiteId : {}", siteId);
		return new ResponseEntity<PeriodicInspection>(inspectionService.retrieveInspectionDetails(siteId),
				HttpStatus.OK);
	}
	
	@PutMapping("/lv/updateInspectionDetails")
	public ResponseEntity<String> updateInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		logger.info("called updateInspectionDetails function UserName : {},SiteId : {},PeriodicInspectionId : {}",
				periodicInspection.getUserName(), periodicInspection.getSiteId(),
				periodicInspection.getPeriodicInspectionId());
		inspectionService.updateInspectionDetails(periodicInspection);
		logger.debug("Ended updateInspectionDetails function");
		return new ResponseEntity<String>("Report successfully Updated", HttpStatus.OK);
	}
	
	@PutMapping("/lv/updateInspectionDetails/intsave")
	public ResponseEntity<String> updateInternInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		logger.info("called updateInternInspectionDetails function UserName : {},SiteId : {},PeriodicInspectionId : {}",
				periodicInspection.getUserName(), periodicInspection.getSiteId(),
				periodicInspection.getPeriodicInspectionId());
		inspectionService.updateInternInspectionDetails(periodicInspection);
		logger.debug("Ended updateInternInspectionDetails function");
		return new ResponseEntity<String>("Report successfully Updated", HttpStatus.OK);
	}
	
	@PostMapping("/lv/sendInspectionComments/{userName}/{siteId}")
	public ResponseEntity<Void> sendComments(@PathVariable String userName, @PathVariable Integer siteId,
			@RequestBody PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException, RegistrationException, Exception {
		logger.info("called sendComments function UserName : {},SiteId : {}", userName, siteId);
		inspectionService.sendComments(userName, siteId, periodicInspectionComment);
		logger.debug("Ended sendComments function");
		sendReplyComments.sendComments(userName);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/lv/replyInspectionComments/{inspectorUserName}/{siteId}")
	public ResponseEntity<Void> replyComments(@PathVariable String inspectorUserName, @PathVariable Integer siteId,
			@RequestBody PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException, RegistrationException, Exception {

		logger.info("called replyComments function InspectorUserName : {},SiteId : {}", inspectorUserName, siteId);
		String viewerUserName = inspectionService.replyComments(inspectorUserName, siteId, periodicInspectionComment);
		if (viewerUserName != null) {
			sendReplyComments.replyComments(inspectorUserName, viewerUserName);
		} else {
			logger.error("No viewer userName avilable");
			throw new InspectionException("No viewer userName avilable");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/lv/approveInspectionComments/{userName}/{siteId}")
	public ResponseEntity<Void> approveComments(@PathVariable String userName, @PathVariable Integer siteId,
			@RequestBody PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException, RegistrationException, Exception {
		logger.info("called approveComments function UserName : {},SiteId : {}", userName, siteId);
		inspectionService.approveComments(userName, siteId, periodicInspectionComment);
		logger.debug("Ended sendComments function");
		sendReplyComments.approveComments(userName,periodicInspectionComment.getApproveOrReject());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
//	@GetMapping("/lv/retrieveLocationDetails/{consumersId}/{distributionDetails}/{referance}/{location}")
//	public String retrieveLocationDetails(@PathVariable String consumersId,@PathVariable String distributionDetails,
//			@PathVariable String referance, @PathVariable String location) throws InspectionException {
//		logger.info("called retrieveLocationDetails function Distribution Details : {},Reference : {},Location: {}, consumerId:{} ", distributionDetails, referance, location,consumersId);
//		System.out.println("Data from UI" +consumersId);
//
//		Integer consumerId = null;
//		if (consumersId!= null  && !consumersId.equalsIgnoreCase("null")){
//			consumerId = Integer.parseInt(consumersId);
//		}
//		System.out.println("Data from UI after parsing" +consumerId);
//		return inspectionService.retrieveLocation(consumerId, distributionDetails, referance, location);
//	}
	
	@GetMapping("/lv/retrieveDistributionLocationDetails/{distributionBoard}/{distributionLocation}/{distributionSourceDetails}")
	public String retrieveDbParentLocation(@PathVariable String distributionBoard, @PathVariable String distributionLocation, @PathVariable String distributionSourceDetails)
			throws Exception {
		logger.info(
				"called retrieveIsolationLocationDetails function distributionBoard : {},distributionLocation : {},distributionSourceDetails: {}",
				distributionBoard, distributionLocation, distributionSourceDetails);
		Integer isolationId = null;
		return inspectionService.retrieveDbParentArrayLocation(distributionBoard, distributionLocation, distributionSourceDetails);
	}
	
	/**
	 *  Updating Distribution Board
	 * @param ipaoInspectionId
	 * @param siteId
	 * @return dbParentArray
	 * @throws InspectionException
	 */
	@PutMapping("/lv/updateDistributionBoard/{ipaoInspectionId}/{siteId}")
	public ResponseEntity<DbParentArray> updateDistribution(@RequestBody DbParentArray dbParentArray,@PathVariable Integer ipaoInspectionId,@PathVariable Integer siteId) throws InspectionException{
		logger.error("updating Distribution service called SiteId : {},IpaoInspectionId : {},DbParentId : {}",siteId,ipaoInspectionId,dbParentArray.getDbParentID());
		return new ResponseEntity<DbParentArray>(inspectionService.updateDisributionBoard(dbParentArray,ipaoInspectionId,siteId),HttpStatus.OK);
	}

	/**
	 *  new Inspection save Call
	 * @param periodicInspection
	 * @return void
	 * @throws InspectionException
	 * @throws CompanyDetailsException
	 */
	@PostMapping("/lv/addNewInspectionDetails")
	public ResponseEntity<String> addNewInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		logger.info("called addInspectionDetails function UserName : {},SiteId : {}", periodicInspection.getUserName(),
				periodicInspection.getSiteId());
		inspectionService.addNewInspectionDetails(periodicInspection);
		logger.debug("Ended addInspectionDetails function");
		return new ResponseEntity<String>("Inspection Details Are Successfully Saved",HttpStatus.CREATED);
	}
	
	/**
	 *  new Inspection intermediate Update Call
	 * @param periodicInspection
	 * @return void
	 * @throws InspectionException
	 * @throws CompanyDetailsException
	 */
	 
	@PutMapping("/lv/updateNewInspectionDetails/intsave")
	public ResponseEntity<String> updateNewInternInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		logger.info("called updateInspectionDetails function UserName : {},SiteId : {},PeriodicInspectionId : {}",
				periodicInspection.getUserName(), periodicInspection.getSiteId(),
				periodicInspection.getPeriodicInspectionId());
		inspectionService.updateNewInternInspectionDetails(periodicInspection);
		logger.debug("Ended updateInspectionDetails function");
		return new ResponseEntity<String>("Report successfully Updated", HttpStatus.OK);
	}
	
	/**
	 *  new Inspection Update Call
	 * @param periodicInspection
	 * @return void
	 * @throws InspectionException
	 * @throws CompanyDetailsException
	 */
	 
	@PutMapping("/lv/updateNewInspectionDetails")
	public ResponseEntity<String> updateNewInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		logger.info("called updateInspectionDetails function UserName : {},SiteId : {},PeriodicInspectionId : {}",
				periodicInspection.getUserName(), periodicInspection.getSiteId(),
				periodicInspection.getPeriodicInspectionId());
		inspectionService.updateNewInspectionDetails(periodicInspection);
		logger.debug("Ended updateInspectionDetails function");
		return new ResponseEntity<String>("Report successfully Updated", HttpStatus.OK);
	}
	
	/**
	 *  new Inspection Intermediate save Call
	 * @param periodicInspection
	 * @return void
	 * @throws InspectionException
	 * @throws CompanyDetailsException
	 */
	@PostMapping("/lv/addNewInspectionDetails/intsave")
	public ResponseEntity<String> addNewInternInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException {
		logger.info("called addInspectionDetails function UserName : {},SiteId : {}", periodicInspection.getUserName(),
				periodicInspection.getSiteId());
		inspectionService.addNewInternInspectionDetails(periodicInspection);
		logger.debug("Ended addInspectionDetails function");
		return new ResponseEntity<String>("Inspection Details Are Successfully Saved",HttpStatus.CREATED);
	}
	
	@PostMapping("/lv/addDistributionBoard/{ipaoInspectionId}/{siteId}")
	public ResponseEntity<DbParentArray> addDistribution(@RequestBody DbParentArray dbParentArray,@PathVariable Integer ipaoInspectionId,@PathVariable Integer siteId) throws InspectionException{
		logger.debug("Distribution board added for SiteId : {},IpaoInspectionId : {}",siteId,ipaoInspectionId);
		return new ResponseEntity<DbParentArray>(inspectionService.addDisributionBoard(dbParentArray,ipaoInspectionId,siteId),HttpStatus.OK);
		
	}
	
	/**
	 * Deleting Distribution Board and Detailed Testing 
	 * @param dbParentID
	 * @return void
	 * @throws InspectionException 
	 */
	@PutMapping("/lv/removeDistributionBoard/{dbParentID}/{siteId}")
	public ResponseEntity<DbParentArray> deleteDistributionB(@PathVariable Integer dbParentID,@PathVariable Integer siteId) throws InspectionException{
		logger.debug("Delete DbParent Service Called DbParentId : {},SiteId : {}",dbParentID,siteId);
		return new ResponseEntity<DbParentArray>(inspectionService.deleteDistributionBoard(dbParentID, siteId),HttpStatus.OK);
	}

	/**
	 * Updating SubDBParentArray
	 * @param dbParentArr
	 * @param siteId
	 * @param ipaoInspectionId
	 * @return
	 * @throws InspectionException
	 */
	@PutMapping("/lv/updateSubDistributionBoard/{siteId}/{ipaoInspectionId}")
	public ResponseEntity<SubDbParent> updateSubDBParent(@RequestBody SubDbParent subDbParentArr,@PathVariable Integer siteId,@PathVariable Integer ipaoInspectionId) throws InspectionException{
		logger.debug("Update SubDistribution Service Called for SiteId : {},IpaoInspectionId : {}, SubDbParentId : {}",siteId,ipaoInspectionId,subDbParentArr.getSubDbParentID());
		return new ResponseEntity<SubDbParent>(inspectionService.updateSubDBParent(subDbParentArr,siteId,ipaoInspectionId),HttpStatus.OK);	
	}
	
	/**
	 * Adding New SubDistribuiton Board
	 * @param subDbParent
	 * @param ipaoInspectionId
	 * @param siteId
	 * @return
	 * @throws InspectionException 
	 */
	@PostMapping("/lv/addSubDIstributionBoard/{ipaoInspectionId}/{siteId}")
	public ResponseEntity<SubDbParent> addSubDistributionBoard(@RequestBody SubDbParent subDbParent,@PathVariable Integer ipaoInspectionId,@PathVariable Integer siteId) throws InspectionException{
		logger.debug("Add SubDistribution Service Called for SiteId : {},IpaoInspectionId : {}",siteId,ipaoInspectionId);
		return new ResponseEntity<SubDbParent>(inspectionService.addSubDistributionBoard(subDbParent,ipaoInspectionId,siteId),HttpStatus.OK);
	}
	
	/**
	 * Deleting SubDistribution Board
	 * @param subDbParentID
	 * @param siteId
	 * @return
	 * @throws InspectionException 
	 */
	@PutMapping("/lv/deleteSubDistributionBoard/{subDbParentID}/{siteId}")
	public ResponseEntity<SubDbParent> deleteSubDistributionBoard(@PathVariable Integer subDbParentID,@PathVariable Integer siteId) throws InspectionException{
		logger.debug("Delete SubDb service called SubDbParentId : {},SiteId : {}",subDbParentID,siteId); 
		return new ResponseEntity<SubDbParent>(inspectionService.deleteSubDistributionBoard(subDbParentID,siteId),HttpStatus.OK);
	}
	
}
