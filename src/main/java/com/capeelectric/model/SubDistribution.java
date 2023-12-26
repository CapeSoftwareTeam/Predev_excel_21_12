package com.capeelectric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author gokul
 *
 */

@Entity
@Table(name = "ins_sub_distribution_board_table")
public class SubDistribution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUB_DISTRIBUTION_ID")
	private Integer subDistributionBoardId;
	
	@Column(name = "SUB_DISTRIBUTIONA")
	private String subDistributionA;

	@Column(name = "SUB_DISTRIBUTIONB")
	private String subDistributionB;

	@Column(name = "SUB_DISTRIBUTIONC")
	private String subDistributionC;

	@Column(name = "SUB_DISTRIBUTIOND")
	private String subDistributionD;

	@Column(name = "SUB_DISTRIBUTIONE")
	private String subDistributionE;

	@Column(name = "SUB_DISTRIBUTIONF")
	private String subDistributionF;

	@Column(name = "SUB_DISTRIBUTIONG")
	private String subDistributionG;

	@Column(name = "SUB_DISTRIBUTIONH")
	private String subDistributionH;

	@Column(name = "SUB_DISTRIBUTIONI")
	private String subDistributionI;

	@Column(name = "SUB_DISTRIBUTIONJ")
	private String subDistributionJ;

	@Column(name = "SUB_DISTRIBUTIONK")
	private String subDistributionK;
	
	@Column(name = "DISTRIBUTION_EQUIPA")
	private String distributionEquipA;
	
	@Column(name = "DISTRIBUTION_EQUIPB")
	private String distributionEquipB;
	
	@Column(name = "DISTRIBUTION_EQUIPC")
	private String distributionEquipC;
	
	@Column(name = "DISTRIBUTION_EQUIPD")
	private String distributionEquipD;
	
	@Column(name = "DISTRIBUTION_EQUIPE")
	private String distributionEquipE;
	
	@Column(name = "DISTRIBUTION_EQUIPF")
	private String distributionEquipF;
	
	@Column(name = "DISTRIBUTION_EQUIPG")
	private String distributionEquipG;
	
	@Column(name = "DISTRIBUTION_EQUIPH")
	private String distributionEquipH;
	
	@Column(name = "DISTRIBUTION_EQUIPI")
	private String distributionEquipI;
	
	@Column(name = "DISTRIBUTION_EQUIPJ")
	private String distributionEquipJ;
	
    @Column(name = "WARNING_NOTICEA")
    private String warningNoticeA;
    
    @Column(name = "WARNING_NOTICEB")
    private String warningNoticeB;
    
    @Column(name = "WARNING_NOTICEC")
    private String warningNoticeC;
    
    @Column(name = "WARNING_NOTICED")
    private String warningNoticeD;
    
    @Column(name = "WARNING_NOTICEE")
    private String warningNoticeE;
    
    @Column(name = "WARNING_NOTICEF")
    private String warningNoticeF;
    
    @Column(name = "WARNING_NOTICEG")
    private String warningNoticeG;
    
    @Column(name = "WARNING_NOTICEH")
    private String warningNoticeH;
    
    @Column(name = "WARNING_NOTICEI")
    private String warningNoticeI;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SUB_DB_PARENT_ID")
	private SubDbParent subDbParentSub;
	
	public Integer getSubDistributionBoardId() {
		return subDistributionBoardId;
	}

	public void setSubDistributionBoardId(Integer subDistributionBoardId) {
		this.subDistributionBoardId = subDistributionBoardId;
	}

	public String getSubDistributionA() {
		return subDistributionA;
	}

	public void setSubDistributionA(String subDistributionA) {
		this.subDistributionA = subDistributionA;
	}

	public String getSubDistributionB() {
		return subDistributionB;
	}

	public void setSubDistributionB(String subDistributionB) {
		this.subDistributionB = subDistributionB;
	}

	public String getSubDistributionC() {
		return subDistributionC;
	}

	public void setSubDistributionC(String subDistributionC) {
		this.subDistributionC = subDistributionC;
	}

	public String getSubDistributionD() {
		return subDistributionD;
	}

	public void setSubDistributionD(String subDistributionD) {
		this.subDistributionD = subDistributionD;
	}

	public String getSubDistributionE() {
		return subDistributionE;
	}

	public void setSubDistributionE(String subDistributionE) {
		this.subDistributionE = subDistributionE;
	}

	public String getSubDistributionF() {
		return subDistributionF;
	}

	public void setSubDistributionF(String subDistributionF) {
		this.subDistributionF = subDistributionF;
	}

	public String getSubDistributionG() {
		return subDistributionG;
	}

	public void setSubDistributionG(String subDistributionG) {
		this.subDistributionG = subDistributionG;
	}

	public String getSubDistributionH() {
		return subDistributionH;
	}

	public void setSubDistributionH(String subDistributionH) {
		this.subDistributionH = subDistributionH;
	}

	public String getSubDistributionI() {
		return subDistributionI;
	}

	public void setSubDistributionI(String subDistributionI) {
		this.subDistributionI = subDistributionI;
	}

	public String getSubDistributionJ() {
		return subDistributionJ;
	}

	public void setSubDistributionJ(String subDistributionJ) {
		this.subDistributionJ = subDistributionJ;
	}

	public String getSubDistributionK() {
		return subDistributionK;
	}

	public void setSubDistributionK(String subDistributionK) {
		this.subDistributionK = subDistributionK;
	}

	public String getDistributionEquipA() {
		return distributionEquipA;
	}

	public void setDistributionEquipA(String distributionEquipA) {
		this.distributionEquipA = distributionEquipA;
	}

	public String getDistributionEquipB() {
		return distributionEquipB;
	}

	public void setDistributionEquipB(String distributionEquipB) {
		this.distributionEquipB = distributionEquipB;
	}

	public String getDistributionEquipC() {
		return distributionEquipC;
	}

	public void setDistributionEquipC(String distributionEquipC) {
		this.distributionEquipC = distributionEquipC;
	}

	public String getDistributionEquipD() {
		return distributionEquipD;
	}

	public void setDistributionEquipD(String distributionEquipD) {
		this.distributionEquipD = distributionEquipD;
	}

	public String getDistributionEquipE() {
		return distributionEquipE;
	}

	public void setDistributionEquipE(String distributionEquipE) {
		this.distributionEquipE = distributionEquipE;
	}

	public String getDistributionEquipF() {
		return distributionEquipF;
	}

	public void setDistributionEquipF(String distributionEquipF) {
		this.distributionEquipF = distributionEquipF;
	}

	public String getDistributionEquipG() {
		return distributionEquipG;
	}

	public void setDistributionEquipG(String distributionEquipG) {
		this.distributionEquipG = distributionEquipG;
	}

	public String getDistributionEquipH() {
		return distributionEquipH;
	}

	public void setDistributionEquipH(String distributionEquipH) {
		this.distributionEquipH = distributionEquipH;
	}

	public String getDistributionEquipI() {
		return distributionEquipI;
	}

	public void setDistributionEquipI(String distributionEquipI) {
		this.distributionEquipI = distributionEquipI;
	}

	public String getDistributionEquipJ() {
		return distributionEquipJ;
	}

	public void setDistributionEquipJ(String distributionEquipJ) {
		this.distributionEquipJ = distributionEquipJ;
	}

	public String getWarningNoticeA() {
		return warningNoticeA;
	}

	public void setWarningNoticeA(String warningNoticeA) {
		this.warningNoticeA = warningNoticeA;
	}

	public String getWarningNoticeB() {
		return warningNoticeB;
	}

	public void setWarningNoticeB(String warningNoticeB) {
		this.warningNoticeB = warningNoticeB;
	}

	public String getWarningNoticeC() {
		return warningNoticeC;
	}

	public void setWarningNoticeC(String warningNoticeC) {
		this.warningNoticeC = warningNoticeC;
	}

	public String getWarningNoticeD() {
		return warningNoticeD;
	}

	public void setWarningNoticeD(String warningNoticeD) {
		this.warningNoticeD = warningNoticeD;
	}

	public String getWarningNoticeE() {
		return warningNoticeE;
	}

	public void setWarningNoticeE(String warningNoticeE) {
		this.warningNoticeE = warningNoticeE;
	}

	public String getWarningNoticeF() {
		return warningNoticeF;
	}

	public void setWarningNoticeF(String warningNoticeF) {
		this.warningNoticeF = warningNoticeF;
	}

	public String getWarningNoticeG() {
		return warningNoticeG;
	}

	public void setWarningNoticeG(String warningNoticeG) {
		this.warningNoticeG = warningNoticeG;
	}

	public String getWarningNoticeH() {
		return warningNoticeH;
	}

	public void setWarningNoticeH(String warningNoticeH) {
		this.warningNoticeH = warningNoticeH;
	}

	public String getWarningNoticeI() {
		return warningNoticeI;
	}

	public void setWarningNoticeI(String warningNoticeI) {
		this.warningNoticeI = warningNoticeI;
	}

	public SubDbParent getSubDbParentSub() {
		return subDbParentSub;
	}

	public void setSubDbParentSub(SubDbParent subDbParentSub) {
		this.subDbParentSub = subDbParentSub;
	}

}
