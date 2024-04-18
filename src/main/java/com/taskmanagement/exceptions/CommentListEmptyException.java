package com.taskmanagement.exceptions;

public class CommentListEmptyException extends RuntimeException
{
	public CommentListEmptyException(String message)
	{
		super(message);
	}
 
}