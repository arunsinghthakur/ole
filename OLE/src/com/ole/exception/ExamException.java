package com.ole.exception;

import com.ole.constant.Constant;

public class ExamException extends Exception {
	private static final long serialVersionUID = -4738579186332357904L;
	
	private String message;
	
	public ExamException(){
		super();
	}
	
	public ExamException(Throwable t){
		super(t);
	}
	
	public ExamException(String message, Throwable t){
		super(message, t);
		this.message = message; 
	}
	
	public ExamException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message == null ? Constant.GENERAL_EXCEPTION : message;
	}
	
}
