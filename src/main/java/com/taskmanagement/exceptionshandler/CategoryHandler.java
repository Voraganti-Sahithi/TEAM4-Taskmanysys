package com.taskmanagement.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.taskmanagement.exceptions.CategoryAlreadyExistsException;
import com.taskmanagement.exceptions.CategoryDoesNotExistExistsException;
import com.taskmanagement.exceptions.CategoryDoesNotExistsException;
import com.taskmanagement.exceptions.CategoryListEmptyException;
import com.taskmanagement.exceptions.CategoryListNotExistsException;
import com.taskmanagement.exceptions.CategoryNotExistsException;
import com.taskmanagement.exceptions.ErrorResponse;

@ControllerAdvice
public class CategoryHandler 
{
	@ExceptionHandler(CategoryAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(CategoryAlreadyExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("ADDFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CategoryListEmptyException.class)
	public ResponseEntity<ErrorResponse> handlerException(CategoryListEmptyException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETALLFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CategoryListNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(CategoryListNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CategoryNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(CategoryNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("GETFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CategoryDoesNotExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(CategoryDoesNotExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("UPDTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CategoryDoesNotExistExistsException.class)
	public ResponseEntity<ErrorResponse> handlerException(CategoryDoesNotExistExistsException ex)
	{
		ErrorResponse err=new ErrorResponse();
		err.setCode("DLTFAILS");
		err.setMessage(ex.getMessage());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
}
