package com.ole.exception;

import com.ole.constant.Constant;

public class RegistrationException extends Exception {
	private static final long serialVersionUID = -4738579186332357904L;
	
	private String message;
	
	public RegistrationException(){
		super();
	}
	
	public RegistrationException(Throwable t){
		super(t);
	}
	
	public RegistrationException(String message, Throwable t){
		super(message, t);
		this.message = message; 
	}
	
	public RegistrationException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message == null ? Constant.GENERAL_EXCEPTION : message;
	}
	
}
