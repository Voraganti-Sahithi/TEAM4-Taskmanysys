package com.taskmanagement.exceptions;

public class CategoryDoesNotExistsException extends RuntimeException
{
	public CategoryDoesNotExistsException(String message)
	{
		super(message);
	}
 
}
