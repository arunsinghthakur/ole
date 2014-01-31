package com.ole.exception;

import com.ole.constant.Constant;

public class AnswerException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7430777810834169593L;
	private String message;
	
	public AnswerException(){
		super();
	}
	
	public AnswerException(Throwable t){
		super(t);
	}
	
	public AnswerException(String message, Throwable t){
		super(message, t);
		this.message = message; 
	}
	
	public AnswerException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message == null ? Constant.GENERAL_EXCEPTION : message;
	}
	
}
