package com.ems.project.exception;

public class DepartmentDetailsAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DepartmentDetailsAlreadyExistsException(String msg) {
		super(msg);
	}

}
