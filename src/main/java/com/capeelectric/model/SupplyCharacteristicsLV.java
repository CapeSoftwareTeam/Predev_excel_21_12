package com.capeelectric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLY_CHARACTERISTICS_LV")
public class SupplyCharacteristicsLV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUPPLYCHARACTERISTICS_LV_ID")
	private Integer supplyCharacteristicsLVId;
	
	@Column(name = "REFERENCE_NAME")
	private String referenceName;
	
	@Column(name = "TOTAL_NO_OF_INCOMERS")
	private Integer totalNoOfIncomers;
		
	@Column(name = "TOTAL_NO_OF_BUSCOUPLER")
	private Integer totalNoOfBuscoupler;
	
	@Column(name = "FEEDERTYPE_A")
	private String feederTypeA;
	
	@Column(name = "TOTAL_NO_OF_OUTGOING")
	private Integer totalNoOfOutgoing;
	
	@Column(name = "TOTAL_NO_OF_SPARE_OUTGOING")
	private Integer totalNoOfSpareOutgoing;
	
	@Column(name = "FEEDERTYPE_B")
	private String feederTypeB;
	
	
	
}
