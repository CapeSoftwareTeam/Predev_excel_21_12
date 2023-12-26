/**
 * 
 */
package com.capeelectric.exception;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class DiagramComponentException extends Throwable {


	private String message;
	
	public DiagramComponentException() {
		
	}

	public DiagramComponentException(String message) {
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
