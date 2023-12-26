/**
 * 
 */
package com.capeelectric.exception;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class ACBException extends Throwable {


	private String message;
	
	public ACBException() {
		
	}

	public ACBException(String message) {
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
