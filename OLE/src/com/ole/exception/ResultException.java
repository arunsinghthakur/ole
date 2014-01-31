package com.ole.exception;

import com.ole.constant.Constant;

public class ResultException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7285124181148415521L;
	private String message;
	
	public ResultException(){
		super();
	}
	
	public ResultException(Throwable t){
		super(t);
	}
	
	public ResultException(String message, Throwable t){
		super(message, t);
		this.message = message; 
	}
	
	public ResultException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message == null ? Constant.GENERAL_EXCEPTION : message;
	}
	
}
