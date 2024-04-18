package com.taskmanagement.exceptions;

public class UserRolesAlreadyExistException extends RuntimeException {

	public UserRolesAlreadyExistException(String message) {
		super(message);
	}
}
