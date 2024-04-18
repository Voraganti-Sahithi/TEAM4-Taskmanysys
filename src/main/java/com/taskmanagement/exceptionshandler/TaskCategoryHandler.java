package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.taskmanagement.exceptions.ErrorResponse;
import com.taskmanagement.exceptions.NoCategoryFoundException;
import com.taskmanagement.exceptions.NoTaskFoundException;
import com.taskmanagement.exceptions.TaskCategoryAlreadyExistsException;


@ControllerAdvice
public class TaskCategoryHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(NoTaskFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(NoCategoryFoundException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(TaskCategoryAlreadyExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("ADDFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
}
