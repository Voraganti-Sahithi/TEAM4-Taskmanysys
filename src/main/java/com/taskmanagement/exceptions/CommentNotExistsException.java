package com.taskmanagement.exceptions;

public class CommentNotExistsException extends RuntimeException
{
	public CommentNotExistsException(String message)
	{
		super(message);
	}
 
}