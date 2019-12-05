package com.clothes.inventory.clothinventoryservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(value = InvalidInputException.class)
	public ResponseEntity<Object> exception(InvalidInputException exception) {
		return new ResponseEntity<>("Please check your input and try again.", HttpStatus.BAD_REQUEST);
	}
}
