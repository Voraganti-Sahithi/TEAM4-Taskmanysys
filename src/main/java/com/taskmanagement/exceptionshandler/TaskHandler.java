package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.taskmanagement.exceptions.ErrorResponse;
import com.taskmanagement.exceptions.TaskAlreadyExistsException;
import com.taskmanagement.exceptions.TaskListEmptyException;
import com.taskmanagement.exceptions.TaskNotExistsException;
import com.taskmanagement.exceptions.TaskNotExistsExistsException;

@ControllerAdvice
public class TaskHandler {

	@ExceptionHandler(TaskListEmptyException.class)
	public ResponseEntity<ErrorResponse> handlerException(TaskListEmptyException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TaskNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(TaskNotExistsException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setCode("UPDTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TaskNotExistsExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(TaskNotExistsExistsException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setCode("DLTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TaskAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(TaskAlreadyExistsException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setCode("ADDFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}

}
