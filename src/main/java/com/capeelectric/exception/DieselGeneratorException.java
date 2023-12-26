/**
 * 
 */
package com.capeelectric.exception;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class DieselGeneratorException extends Throwable {


	private String message;
	
	public DieselGeneratorException() {
		
	}

	public DieselGeneratorException(String message) {
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
