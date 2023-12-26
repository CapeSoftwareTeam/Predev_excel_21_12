package com.capeelectric.exception;

public class DeleteDiagramShapesException extends Throwable {
private String message;
	
	public DeleteDiagramShapesException() {
		
	}

	public DeleteDiagramShapesException(String message) {
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
