package com.capeelectric.exception;

public class TransformerException extends Throwable {


	private String message;
	
	public TransformerException() {
		
	}

	public TransformerException(String message) {
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
