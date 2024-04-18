package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.taskmanagement.exceptions.ErrorResponse;
import com.taskmanagement.exceptions.ProjectListEmptyException;
import com.taskmanagement.exceptions.UserRolesAlreadyExistException;
import com.taskmanagement.exceptions.UserRolesDoesNotExistsException;
import com.taskmanagement.exceptions.UserRolesListEmptyException;
import com.taskmanagement.exceptions.UserRolesNotExistsException;
@ControllerAdvice
public class UserRolesHandler {
	
	@ExceptionHandler(UserRolesAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserRolesAlreadyExistException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("ADDFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserRolesListEmptyException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserRolesListEmptyException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserRolesNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserRolesNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserRolesDoesNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserRolesDoesNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("DLTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(Exception ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setCode("ENDPOINTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
}

