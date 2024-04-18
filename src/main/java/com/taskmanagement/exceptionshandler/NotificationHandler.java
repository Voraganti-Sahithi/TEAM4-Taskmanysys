package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.taskmanagement.exceptions.ErrorResponse;
import com.taskmanagement.exceptions.NoNotificationFoundException;
import com.taskmanagement.exceptions.NotificationAlreadyExistsException;
import com.taskmanagement.exceptions.NotificationDoesNotExistsException;
import com.taskmanagement.exceptions.NotificationListEmptyException;
import com.taskmanagement.exceptions.NotificationNotExistsException;
@ControllerAdvice
public class NotificationHandler 
{
	@ExceptionHandler(NotificationAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(NotificationAlreadyExistsException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("POSTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);		
	}
	
	
	@ExceptionHandler(NotificationListEmptyException.class)
	public ResponseEntity<ErrorResponse> handlerException(NotificationListEmptyException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);		
	}
	
	
	@ExceptionHandler(NoNotificationFoundException.class)
	public ResponseEntity<ErrorResponse> handlerException(NoNotificationFoundException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);		
	}
	
	
	@ExceptionHandler(NotificationNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(NotificationNotExistsException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("UPDTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);		
	}
	
	
	@ExceptionHandler(NotificationDoesNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(NotificationDoesNotExistsException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("DLTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);		
	}
	
}
