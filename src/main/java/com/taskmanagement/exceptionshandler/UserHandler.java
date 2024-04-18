package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.taskmanagement.exceptions.ErrorResponse;
import com.taskmanagement.exceptions.UserAlreadyExistsException;
import com.taskmanagement.exceptions.UserDoesNotExistExistsException;
import com.taskmanagement.exceptions.UserDoesNotExistsException;
import com.taskmanagement.exceptions.UserListEmptyException;
import com.taskmanagement.exceptions.UserNotExistsException;
import com.taskmanagement.exceptions.UserNotExistsExistsException;

@ControllerAdvice

	public class UserHandler {	 
		@ExceptionHandler(UserListEmptyException.class)
		public ResponseEntity<ErrorResponse> handlerException(UserListEmptyException ex)
		{
			ErrorResponse err=new ErrorResponse();
			err.setCode("GETALLFAILS");
			err.setMessage(ex.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
	
	
		
	@ExceptionHandler(UserNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserAlreadyExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("ADDFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserDoesNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserDoesNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("UPDTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserDoesNotExistExistsException.class)
	public ResponseEntity<ErrorResponse> handlerexception(UserDoesNotExistExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("DLTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
		
	//AUTHENTICATE USER DETAILS BY USING USERNAME AND PASSWORD
	@ExceptionHandler(UserNotExistsExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserNotExistsExistsException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("ADDFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
}

	


