/**
 * 
 */
package com.capeelectric.exception;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class FanException extends Throwable {


	private String message;
	
	public FanException() {
		
	}

	public FanException(String message) {
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
