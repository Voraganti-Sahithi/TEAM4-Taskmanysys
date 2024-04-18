package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.taskmanagement.exceptions.CategoryAlreadyExistsException;
import com.taskmanagement.exceptions.CategoryDoesNotExistExistsException;
import com.taskmanagement.exceptions.CategoryDoesNotExistsException;
import com.taskmanagement.exceptions.CategoryListEmptyException;
import com.taskmanagement.exceptions.CommentAlreadyExistsException;
import com.taskmanagement.exceptions.CommentDoesNotExistExistsException;
import com.taskmanagement.exceptions.CommentDoesNotExistsException;
import com.taskmanagement.exceptions.CommentListEmptyException;
import com.taskmanagement.exceptions.CommentNotExistsException;
import com.taskmanagement.exceptions.ErrorResponse;

@ControllerAdvice
public class CommentHandler 
{
	@ExceptionHandler(CommentAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(CommentAlreadyExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("ADDFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CommentListEmptyException.class)
	public ResponseEntity<ErrorResponse> handlerException(CommentListEmptyException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CommentNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(CommentNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CommentDoesNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(CommentDoesNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("UPDTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CommentDoesNotExistExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(CommentDoesNotExistExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("DLTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
}
