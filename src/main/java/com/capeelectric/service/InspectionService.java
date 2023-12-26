package com.capeelectric.service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.DbParentArray;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.PeriodicInspectionComment;
import com.capeelectric.model.SubDbParent;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface InspectionService {

	public void addInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException;;
	
	public void addInternInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException;;

	public PeriodicInspection retrieveInspectionDetails(String userName, Integer siteId)
			throws InspectionException;
	
	public PeriodicInspection retrieveInspectionDetails(Integer siteId)
			throws InspectionException;

	public void updateInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException;
	
	public void updateInternInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException;

	public void sendComments(String userName, Integer siteId, PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException;

	public String replyComments(String userName, Integer siteId, PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException;

	public void approveComments(String userName, Integer siteId, PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException;

	public String retrieveDbParentArrayLocation(String distributionBoard,
			String distributionLocation, String distributionSourceDetails) throws  Exception;

	public DbParentArray updateDisributionBoard(DbParentArray dbParentArray, Integer ipaoInspectionId, Integer siteId) throws InspectionException;
	
	public void addNewInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException;
	
	public void updateNewInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException;
	
	public DbParentArray addDisributionBoard(DbParentArray dbParentArray, Integer ipaoInspectionId, Integer siteId) throws InspectionException;

	public DbParentArray deleteDistributionBoard(Integer dbParentID, Integer siteId) throws InspectionException;

	public SubDbParent updateSubDBParent(SubDbParent subDbParentArr, Integer siteId, Integer ipaoInspectionId) throws InspectionException;

	public SubDbParent addSubDistributionBoard(SubDbParent subDbParent, Integer ipaoInspectionId, Integer siteId) throws InspectionException;

	public SubDbParent deleteSubDistributionBoard(Integer subDbParentID, Integer siteId) throws InspectionException;
	
	public void updateNewInternInspectionDetails(PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException;

	public void addNewInternInspectionDetails(PeriodicInspection periodicInspection)
			throws InspectionException, CompanyDetailsException;

	
//	public String retrieveLocation(Integer consumerId,String distributionDetails, String reference, String location);


}
