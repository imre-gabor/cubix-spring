package hu.cubix.airport.controller;

import java.util.List;

import org.springframework.validation.FieldError;

public class MyError {

	private String message;
	private List<FieldError> fieldErrors;
	

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public MyError(String message) {		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
