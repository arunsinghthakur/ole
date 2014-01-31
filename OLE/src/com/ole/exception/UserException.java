package com.ole.exception;

import com.ole.constant.Constant;

public class UserException extends Exception {
	private static final long serialVersionUID = -4738579186332357904L;
	
	private String message;
	
	public UserException(){
		super();
	}
	
	public UserException(Throwable t){
		super(t);
	}
	
	public UserException(String message, Throwable t){
		super(message, t);
		this.message = message; 
	}
	
	public UserException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message == null ? Constant.GENERAL_EXCEPTION : message;
	}
	
}
