package com.ems.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdviceController {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> dataNootFound(ResourceNotFoundException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex) {

		return new ResponseEntity<>("Somethig went wrong, please try again later", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(EmployeeDetailsAlreadyExistException.class)
	public ResponseEntity<?> handleGlobalException(EmployeeDetailsAlreadyExistException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DepartmentDetailsAlreadyExistsException.class)
	public ResponseEntity<?> handleGlobalException(DepartmentDetailsAlreadyExistsException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}
}
