package com.taskmanagement.exceptions;

public class ProjectListEmptyException extends RuntimeException{
	public ProjectListEmptyException(String message)
	{
		super(message);
	}

}
