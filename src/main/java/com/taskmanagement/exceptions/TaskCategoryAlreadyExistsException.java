package com.taskmanagement.exceptions;

public class TaskCategoryAlreadyExistsException extends RuntimeException {
	public TaskCategoryAlreadyExistsException(String message)
	{
		super(message);
	}

}
