package com.capeelectric.exception;

public class SwitchBoardException extends Throwable {


	private String message;
	
	public SwitchBoardException() {
		
	}

	public SwitchBoardException(String message) {
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
