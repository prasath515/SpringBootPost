package com.post.controller.contolleradvice;


public class ValidationErrors {
	private String fieldName;
	private String message;

	public ValidationErrors(String message) {
		this.message = message;
	}
    
	public ValidationErrors(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getMessage() {
		return message;
	}
	
}
