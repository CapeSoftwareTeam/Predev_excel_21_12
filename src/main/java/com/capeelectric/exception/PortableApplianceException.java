/**
 * 
 */
package com.capeelectric.exception;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class PortableApplianceException extends Throwable {


	private String message;
	
	public PortableApplianceException() {
		
	}

	public PortableApplianceException(String message) {
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
