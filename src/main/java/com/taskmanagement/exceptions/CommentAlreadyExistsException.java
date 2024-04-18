package com.taskmanagement.exceptions;

public class CommentAlreadyExistsException extends RuntimeException
{
	public CommentAlreadyExistsException(String message)
	{
		super(message);
	}
 
}