package com.taskmanagement.exceptions;

public class UserRoleDoesNotExistsException extends RuntimeException {
	public UserRoleDoesNotExistsException(String message)
	{
		super(message);
	}
 
}
