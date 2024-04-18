package com.taskmanagement.exceptions;


//AUTHENTICATE SER DETAILS BY USING USERNAME AND PASSWORD
public class UserDoesNotExistsException extends RuntimeException{
	public UserDoesNotExistsException(String message) {
		super(message);
	}

}
