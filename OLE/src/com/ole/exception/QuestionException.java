package com.ole.exception;

import com.ole.constant.Constant;

public class QuestionException extends Exception {
	private static final long serialVersionUID = -4738579186332357904L;
	
	private String message;
	
	public QuestionException(){
		super();
	}
	
	public QuestionException(Throwable t){
		super(t);
	}
	
	public QuestionException(String message, Throwable t){
		super(message, t);
		this.message = message; 
	}
	
	public QuestionException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message == null ? Constant.GENERAL_EXCEPTION : message;
	}
	
}
