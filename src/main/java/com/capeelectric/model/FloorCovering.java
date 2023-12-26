package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "FLOORCOVERING_TABLE")
public class FloorCovering implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FLOORCOVERING_ID")
	private Integer floorCoveringId;

	@Column(name = "FC_TYPE")
	private String fcType;

	@Column(name = "FC_MANUFACTOR")
	private String fcManufactor;

	@Column(name = "FC_DESCRIPTION")
	private String fcDescription;

	@Column(name = "FC_WOVEN")
	private String fcWoven;

	@Column(name = "FC_CHEMICAL")
	private String fcChemical;

	@Column(name = "FC_NONE")
	private String fcNone;

	@Column(name = "FC_OTHER_DESCRIPTION")
	private String fcOtherDecription;

	@Column(name = "WALL_TYPE")
	private String wallType;

	@Column(name = "WALL_MATERIAL")
	private String wallMaterial;

	@Column(name = "WALL_COVERING_TYPE")
	private String wallCoveringType;

	@Column(name = "WALL_HUMUDITY")
	private String wallHumidity;

	@Column(name = "WALL_SEALING")
	private String wallSealing;

	@Column(name = "WALL_DESCRIPTION")
	private String wallDesc;

	@Column(name = "CC_FALSE_DESCRIPTION")
	private String ccFalseDesc;

	@Column(name = "CC_FALSE_HUMIDITY")
	private String ccFalseHumidity;

	@Column(name = "CC_FALSE_HEIGHT")
	private String ccFalseHeight;

	@Column(name = "CC_UTILISATION")
	private String ccUtilisation;
	
	@Column(name = "CC_TRUE_DESCRIPTION")
	private String ccTrueDesc;

	@Column(name = "CC_TRUE_HUMIDITY")
	private String ccTrueHumidity;
	
	@Column(name = "CC_SURFACE_DESCRIPTION")
	private String ccSurfaceDesc;

	@Column(name = "WINDOWS_EXTERNAL")
	private String windowsExternal;

	@Column(name = "WINDOWS_DESCRIPTION")
	private String windowsDescription;

	@Column(name = "WINDOWS_COVERING")
	private String windowsCovering;

	@Column(name = "WINDOWS_OTHER_DESC")
	private String windowsOtherDesc;

	@Column(name = "WINDOWS_INTERNAL_DESCRIPTION")
	private String windowsInternalDesc;

	@Column(name = "DOORS_MATERIAL")
	private String doorsMaterial;

	@Column(name = "DOORS_NUMBER")
	private String doorsNumber;

	@Column(name = "DOORS_WIDTH")
	private String doorsWidth;

	@Column(name = "DOORS_HEIGHT")
	private String doorsHeight;

	@Column(name = "DOORS_CLOSER_MECHANISH")
	private String doorsCloserMechanish;

	@Column(name = "DOORS_QULITY_SEALING")
	private String doorsQualitySealing;

	@Column(name = "DOORS_DESCRIPTION")
	private String doorsDesc;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "FACILITYDATA_ID")
	private FacilityData facilityData;

	public Integer getFloorCoveringId() {
		return floorCoveringId;
	}

	public void setFloorCoveringId(Integer floorCoveringId) {
		this.floorCoveringId = floorCoveringId;
	}

	public String getFcType() {
		return fcType;
	}

	public void setFcType(String fcType) {
		this.fcType = fcType;
	}

	public String getFcManufactor() {
		return fcManufactor;
	}

	public void setFcManufactor(String fcManufactor) {
		this.fcManufactor = fcManufactor;
	}

	public String getFcDescription() {
		return fcDescription;
	}

	public void setFcDescription(String fcDescription) {
		this.fcDescription = fcDescription;
	}

	public String getFcWoven() {
		return fcWoven;
	}

	public void setFcWoven(String fcWoven) {
		this.fcWoven = fcWoven;
	}

	public String getFcChemical() {
		return fcChemical;
	}

	public void setFcChemical(String fcChemical) {
		this.fcChemical = fcChemical;
	}

	public String getFcNone() {
		return fcNone;
	}

	public void setFcNone(String fcNone) {
		this.fcNone = fcNone;
	}

	public String getFcOtherDecription() {
		return fcOtherDecription;
	}

	public void setFcOtherDecription(String fcOtherDecription) {
		this.fcOtherDecription = fcOtherDecription;
	}

	public String getWallType() {
		return wallType;
	}

	public void setWallType(String wallType) {
		this.wallType = wallType;
	}

	public String getWallMaterial() {
		return wallMaterial;
	}

	public void setWallMaterial(String wallMaterial) {
		this.wallMaterial = wallMaterial;
	}

	public String getWallCovering_Type() {
		return wallCoveringType;
	}

	public void setWallCovering_Type(String wallCovering_Type) {
		this.wallCoveringType = wallCovering_Type;
	}

	public String getWallHumidity() {
		return wallHumidity;
	}

	public void setWallHumidity(String wallHumidity) {
		this.wallHumidity = wallHumidity;
	}

	public String getWallSealing() {
		return wallSealing;
	}

	public void setWallSealing(String wallSealing) {
		this.wallSealing = wallSealing;
	}

	public String getWallDesc() {
		return wallDesc;
	}

	public void setWallDesc(String wallDesc) {
		this.wallDesc = wallDesc;
	}

	public String getCcFalseDesc() {
		return ccFalseDesc;
	}

	public void setCcFalseDesc(String ccFalseDesc) {
		this.ccFalseDesc = ccFalseDesc;
	}

	public String getCcFalseHumidity() {
		return ccFalseHumidity;
	}

	public void setCcFalseHumidity(String ccFalseHumidity) {
		this.ccFalseHumidity = ccFalseHumidity;
	}

	public String getCcFalseHeight() {
		return ccFalseHeight;
	}

	public void setCcFalseHeight(String ccFalseHeight) {
		this.ccFalseHeight = ccFalseHeight;
	}

	public String getCcUtilisation() {
		return ccUtilisation;
	}

	public void setCcUtilisation(String ccUtilisation) {
		this.ccUtilisation = ccUtilisation;
	}

	public String getCcTrueHumidity() {
		return ccTrueHumidity;
	}

	public void setCcTrueHumidity(String ccTrueHumidity) {
		this.ccTrueHumidity = ccTrueHumidity;
	}

	public String getCcTrueDesc() {
		return ccTrueDesc;
	}

	public void setCcTrueDesc(String ccTrueDesc) {
		this.ccTrueDesc = ccTrueDesc;
	}

	public String getWindowsExternal() {
		return windowsExternal;
	}

	public void setWindowsExternal(String windowsExternal) {
		this.windowsExternal = windowsExternal;
	}

	public String getWindowsDescription() {
		return windowsDescription;
	}

	public void setWindowsDescription(String windowsDescription) {
		this.windowsDescription = windowsDescription;
	}

	public String getWindowsCovering() {
		return windowsCovering;
	}

	public void setWindowsCovering(String windowsCovering) {
		this.windowsCovering = windowsCovering;
	}

	public String getWindowsOtherDesc() {
		return windowsOtherDesc;
	}

	public void setWindowsOtherDesc(String windowsOtherDesc) {
		this.windowsOtherDesc = windowsOtherDesc;
	}

	public String getWindowsInternalDesc() {
		return windowsInternalDesc;
	}

	public void setWindowsInternalDesc(String windowsInternalDesc) {
		this.windowsInternalDesc = windowsInternalDesc;
	}

	public String getDoorsMaterial() {
		return doorsMaterial;
	}

	public void setDoorsMaterial(String doorsMaterial) {
		this.doorsMaterial = doorsMaterial;
	}

	public String getDoorsNumber() {
		return doorsNumber;
	}

	public void setDoorsNumber(String doorsNumber) {
		this.doorsNumber = doorsNumber;
	}

	public String getDoorsWidth() {
		return doorsWidth;
	}

	public void setDoorsWidth(String doorsWidth) {
		this.doorsWidth = doorsWidth;
	}

	public String getDoorsHeight() {
		return doorsHeight;
	}

	public void setDoorsHeight(String doorsHeight) {
		this.doorsHeight = doorsHeight;
	}

	public String getDoorsCloserMechanish() {
		return doorsCloserMechanish;
	}

	public void setDoorsCloserMechanish(String doorsCloserMechanish) {
		this.doorsCloserMechanish = doorsCloserMechanish;
	}

	public String getDoorsQualitySealing() {
		return doorsQualitySealing;
	}

	public void setDoorsQualitySealing(String doorsQualitySealing) {
		this.doorsQualitySealing = doorsQualitySealing;
	}

	public String getDoorsDesc() {
		return doorsDesc;
	}

	public void setDoorsDesc(String doorsDesc) {
		this.doorsDesc = doorsDesc;
	}

	public FacilityData getFacilityData() {
		return facilityData;
	}

	public void setFacilityData(FacilityData facilityData) {
		this.facilityData = facilityData;
	}

	public String getWallCoveringType() {
		return wallCoveringType;
	}

	public void setWallCoveringType(String wallCoveringType) {
		this.wallCoveringType = wallCoveringType;
	}

	public String getCcSurfaceDesc() {
		return ccSurfaceDesc;
	}

	public void setCcSurfaceDesc(String ccSurfaceDesc) {
		this.ccSurfaceDesc = ccSurfaceDesc;
	}
	
	

}
