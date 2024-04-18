package com.taskmanagement.controllers;
 
public class SuccessResponse {
	private String message;
	private String code;
	public SuccessResponse(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	public SuccessResponse() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
 
}