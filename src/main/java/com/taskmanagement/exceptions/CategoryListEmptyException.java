package com.taskmanagement.exceptions;

public class CategoryListEmptyException extends RuntimeException
{
	public CategoryListEmptyException(String message)
	{
		super(message);
	}
 
}