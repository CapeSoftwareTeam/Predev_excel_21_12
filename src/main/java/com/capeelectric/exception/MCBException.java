/**
 * 
 */
package com.capeelectric.exception;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class MCBException extends Throwable {


	private String message;
	
	public MCBException() {
		
	}

	public MCBException(String message) {
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
