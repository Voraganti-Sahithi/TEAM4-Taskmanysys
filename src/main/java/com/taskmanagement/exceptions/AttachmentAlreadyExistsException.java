package com.taskmanagement.exceptions;



public class AttachmentAlreadyExistsException extends RuntimeException{
	public AttachmentAlreadyExistsException(String message) {
		super(message);
	}
}
