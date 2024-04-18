package com.taskmanagement.exceptions;


public class NotificationListEmptyException extends RuntimeException{
	public NotificationListEmptyException(String message) {
		super(message);
	}

}
