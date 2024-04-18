package com.taskmanagement.exceptions;

public class TaskAlreadyExistsException extends RuntimeException{
	
	public TaskAlreadyExistsException(String message)
	{
		super(message);
	}

}
