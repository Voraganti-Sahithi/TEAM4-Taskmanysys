package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.taskmanagement.exceptions.ErrorResponse;
import com.taskmanagement.exceptions.UserAlreadyExistsException;
import com.taskmanagement.exceptions.UserDoesNotExistsException;
import com.taskmanagement.exceptions.UserRoleAlreadyExistsException;
import com.taskmanagement.exceptions.UserRoleDoesNotExistsException;
import com.taskmanagement.exceptions.UserRoleListEmptyException;
import com.taskmanagement.exceptions.UserRoleNotExistsException;
import com.taskmanagement.exceptions.UserRoleNotExistsExistsException;

@ControllerAdvice
	public class UserRoleHandler {
	 
		@ExceptionHandler(UserRoleListEmptyException.class)
		public ResponseEntity<ErrorResponse> handlerException(UserRoleListEmptyException ex)
		{
			ErrorResponse err=new ErrorResponse();
			err.setCode("GETALLFAILS");
			err.setMessage(ex.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
	
	
 
	@ExceptionHandler(UserRoleNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserRoleNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserRoleAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserRoleAlreadyExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("ADDFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserRoleDoesNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserRoleDoesNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("UPDTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	
	
	}
	
	@ExceptionHandler(UserRoleNotExistsExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserRoleNotExistsExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("DLTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	
	
	}
	
}

