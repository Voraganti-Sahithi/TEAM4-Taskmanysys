package com.taskmanagement.exceptions;

public class TaskListEmptyException extends RuntimeException {
	public TaskListEmptyException(String message)
	{
		super(message);
	}

}
