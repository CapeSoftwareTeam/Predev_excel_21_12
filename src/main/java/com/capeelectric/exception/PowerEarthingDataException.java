package com.capeelectric.exception;

public class PowerEarthingDataException  extends Throwable {

	private static final long serialVersionUID = 1L;	
	private String message;
	public PowerEarthingDataException() {
	
	}
	public PowerEarthingDataException(String message) {
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
