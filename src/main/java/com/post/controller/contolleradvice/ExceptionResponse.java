package com.post.controller.contolleradvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ExceptionResponse {
	
	private Date timestamp = new Date();
	private List<ValidationErrors> errors = new ArrayList<>();

	public List<ValidationErrors> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationErrors> errors) {
		this.errors = errors;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}



}
