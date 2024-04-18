package com.taskmanagement.exceptions;


public class NotificationAlreadyExistsException extends RuntimeException{
	public NotificationAlreadyExistsException(String message) {
		super(message);
	}

}
