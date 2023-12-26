package com.capeelectric.exception;

public class EarthElectrodeException  extends Throwable {


	private String message;
	
	public EarthElectrodeException() {
		
	}

	public EarthElectrodeException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
