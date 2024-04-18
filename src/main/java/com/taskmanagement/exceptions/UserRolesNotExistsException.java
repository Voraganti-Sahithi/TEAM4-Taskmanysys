package com.taskmanagement.exceptions;

public class UserRolesNotExistsException extends RuntimeException{
   public UserRolesNotExistsException(String message)
   {
	   super(message);
   }
   
}