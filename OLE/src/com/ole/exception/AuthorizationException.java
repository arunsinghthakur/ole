package com.ole.exception;

import com.ole.constant.Constant;

public class AuthorizationException extends Exception {
	private static final long serialVersionUID = -4738579186332357904L;
	
	private String message;
	
	public AuthorizationException(){
		super();
	}
	
	public AuthorizationException(Throwable t){
		super(t);
	}
	
	public AuthorizationException(String message, Throwable t){
		super(message, t);
		this.message = message; 
	}
	
	public AuthorizationException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message == null ? Constant.GENERAL_EXCEPTION : message;
	}
	
}
