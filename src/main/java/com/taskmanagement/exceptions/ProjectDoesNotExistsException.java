package com.taskmanagement.exceptions;

public class ProjectDoesNotExistsException extends RuntimeException {
    public ProjectDoesNotExistsException (String message)
    {
    	super(message);
    }
}
