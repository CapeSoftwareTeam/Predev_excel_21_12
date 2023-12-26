/**
 * 
 */
package com.capeelectric.exception;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class ProtectiveEarthConductorException extends Throwable {


	private String message;
	
	public ProtectiveEarthConductorException() {
		
	}

	public ProtectiveEarthConductorException(String message) {
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
