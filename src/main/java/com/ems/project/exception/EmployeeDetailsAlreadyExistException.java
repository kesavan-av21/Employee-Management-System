package com.ems.project.exception;

public class EmployeeDetailsAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeDetailsAlreadyExistException(String msg) {
		super(msg);
	}

}
