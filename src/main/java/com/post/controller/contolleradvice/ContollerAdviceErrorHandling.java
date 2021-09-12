package com.post.controller.contolleradvice;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.post.exception.ResourceNotFoundException;

@ControllerAdvice
class ControllerAdviceErrorHandling {

	@Autowired
	private ExceptionResponse error;

	@Autowired
	private CustomError customError;

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ExceptionResponse onConstraintValidationException(ConstraintViolationException e) {
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			error.getErrors().add(new ValidationErrors(violation.getPropertyPath().toString(), violation.getMessage()));
		}
		return error;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	ExceptionResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.getErrors().add(new ValidationErrors(fieldError.getField(), fieldError.getDefaultMessage()));
		}
		return error;
	}

//	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//	@ResponseBody
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	ValidationFailedResponse onSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
//
//		ValidationFailedResponse errors = new ValidationFailedResponse();
//		errors.getErrors().add(new ViolationErrors(e.getLocalizedMessage()) );
//
//		return errors;
//	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	ResponseEntity<CustomError> onSQLIntegrityConstraintViolationException(
			SQLIntegrityConstraintViolationException e) {

		customError.setMessage(e.getLocalizedMessage());

		return new ResponseEntity<CustomError>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<Object> onResourceNotFoundException(ResourceNotFoundException e) {

		customError.setMessage(e.getMessage());

		return new ResponseEntity<Object>(customError, HttpStatus.NOT_FOUND);
	}

}
