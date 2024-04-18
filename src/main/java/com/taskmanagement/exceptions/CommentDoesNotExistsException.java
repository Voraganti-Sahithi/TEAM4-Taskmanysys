package com.taskmanagement.exceptions;

public class CommentDoesNotExistsException extends RuntimeException
{
	public CommentDoesNotExistsException(String message)
	{
		super(message);
	}
 
}