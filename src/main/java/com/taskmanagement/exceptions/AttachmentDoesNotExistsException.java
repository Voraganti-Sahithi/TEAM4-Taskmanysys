package com.taskmanagement.exceptions;


public class AttachmentDoesNotExistsException extends RuntimeException{
	public AttachmentDoesNotExistsException(String message) {
		super(message);
	}
}
