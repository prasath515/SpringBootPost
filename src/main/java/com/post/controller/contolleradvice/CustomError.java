package com.post.controller.contolleradvice;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CustomError {

	private String message;
	private Date timestamp = new Date();

	public CustomError() {
	}

	public CustomError(String message, Date timestamp) {
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
