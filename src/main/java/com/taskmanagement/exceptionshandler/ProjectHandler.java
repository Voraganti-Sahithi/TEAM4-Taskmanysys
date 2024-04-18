package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.taskmanagement.exceptions.ErrorResponse;
import com.taskmanagement.exceptions.ProjectAlreadyExistException;
import com.taskmanagement.exceptions.ProjectDoesNotExistsException;
import com.taskmanagement.exceptions.ProjectListEmptyException;
import com.taskmanagement.exceptions.ProjectNotExistsException;

@ControllerAdvice
public class ProjectHandler {
	@ExceptionHandler(ProjectAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handlerException(ProjectAlreadyExistException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("ADDFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProjectListEmptyException.class)
	public ResponseEntity<ErrorResponse> handlerException(ProjectListEmptyException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProjectNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(ProjectNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("UPDTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProjectDoesNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(ProjectDoesNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("DLTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}	
	
}
