package com.taskmanagement.exceptions;

public class NoCategoryFoundException extends RuntimeException{
	
	public NoCategoryFoundException(String message)
	{
		super(message);
	}

}
