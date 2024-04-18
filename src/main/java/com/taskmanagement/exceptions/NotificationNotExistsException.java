package com.taskmanagement.exceptions;



public class NotificationNotExistsException extends RuntimeException{
	public NotificationNotExistsException(String message) {
		super(message);
	}

}
