package com.capeelectric.model;

import java.util.List;

/**
 *
 * @author capeelectricsoftware
 *
 */
public class AllComponentObservation {

	private List<SupplyOuterObservation> supplyOuterObservation;

	private List<IpaoInspection> ipaoInspection;

	private List<TestingInnerObservation> testingInnerObservation;

	public List<SupplyOuterObservation> getSupplyOuterObservation() {
		return supplyOuterObservation;
	}

	public void setSupplyOuterObservation(List<SupplyOuterObservation> supplyOuterObservation) {
		this.supplyOuterObservation = supplyOuterObservation;
	}
   
	
	public List<IpaoInspection> getIpaoInspection() {
		return ipaoInspection;
	}

	public void setIpaoInspection(List<IpaoInspection> ipaoInspection) {
		this.ipaoInspection = ipaoInspection;
	}

	public List<TestingInnerObservation> getTestingInnerObservation() {
		return testingInnerObservation;
	}

	public void setTestingInnerObservation(List<TestingInnerObservation> testingInnerObservation) {
		this.testingInnerObservation = testingInnerObservation;
	}

}
