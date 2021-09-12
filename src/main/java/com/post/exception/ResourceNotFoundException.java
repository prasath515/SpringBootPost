package com.post.exception;


public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private Object message;
	
	public ResourceNotFoundException(String fieldName, Object message) {
		this.fieldName = fieldName;
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "ResourceNotFoundException [fieldName=" + fieldName + ", message=" + message + "]";
	}

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message.toString();
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	

}
