package com.taskmanagement.exceptions;



public class NotificationDoesNotExistsException extends RuntimeException{
	public NotificationDoesNotExistsException(String message) {
		super(message);
	}

}
