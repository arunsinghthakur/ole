package com.ole.exception;

import com.ole.constant.Constant;

public class ProfileException extends Exception {
	private static final long serialVersionUID = -4738579186332357904L;
	
	private String message;
	
	public ProfileException(){
		super();
	}
	
	public ProfileException(Throwable t){
		super(t);
	}
	
	public ProfileException(String message, Throwable t){
		super(message, t);
		this.message = message; 
	}
	
	public ProfileException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message == null ? Constant.GENERAL_EXCEPTION : message;
	}
	
}
