package hu.cubix.airport.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import hu.cubix.airport.service.NonUniqueIataException;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(NonUniqueIataException.class)
	public ResponseEntity<MyError> handleNonUniqueIata(NonUniqueIataException e, WebRequest request) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new MyError(e.getMessage()));
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> handleMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest request) {
		
		MyError myError = new MyError(e.getMessage());
		myError.setFieldErrors(e.getBindingResult().getFieldErrors());
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(myError);
		
	}
}
