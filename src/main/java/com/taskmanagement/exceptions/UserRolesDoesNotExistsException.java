package com.taskmanagement.exceptions;

public class UserRolesDoesNotExistsException extends RuntimeException{
	public UserRolesDoesNotExistsException (String message) {
		super(message);
	}

}
