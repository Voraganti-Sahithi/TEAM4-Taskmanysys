package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.taskmanagement.exceptions.AttachmentAlreadyExistsException;
import com.taskmanagement.exceptions.AttachmentDoesNotExistExistsException;
import com.taskmanagement.exceptions.AttachmentDoesNotExistsException;
import com.taskmanagement.exceptions.AttachmentListEmptyException;
import com.taskmanagement.exceptions.ErrorResponse;
import com.taskmanagement.exceptions.NoAttachmentFoundException;


@ControllerAdvice
public class AttachmentHandler
{
	@ExceptionHandler(AttachmentAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(AttachmentAlreadyExistsException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("POSTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(AttachmentListEmptyException.class)
	public ResponseEntity<ErrorResponse> handlerException(AttachmentListEmptyException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND); 
	}
	
	
	@ExceptionHandler(NoAttachmentFoundException.class)
	public ResponseEntity<ErrorResponse> handlerException(NoAttachmentFoundException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	
	}
	
	
	@ExceptionHandler(AttachmentDoesNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(AttachmentDoesNotExistsException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("UPDATEFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	
	}
	
	
	@ExceptionHandler(AttachmentDoesNotExistExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(AttachmentDoesNotExistExistsException ex){
		ErrorResponse err=new ErrorResponse();
		err.setCode("DLTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	
	}


}
