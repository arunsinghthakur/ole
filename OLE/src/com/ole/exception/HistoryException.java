package com.ole.exception;

import com.ole.constant.Constant;

public class HistoryException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -386141363329482818L;
	private String message;
	
	public HistoryException(){
		super();
	}
	
	public HistoryException(Throwable t){
		super(t);
	}
	
	public HistoryException(String message, Throwable t){
		super(message, t);
		this.message = message; 
	}
	
	public HistoryException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message == null ? Constant.GENERAL_EXCEPTION : message;
	}
	
}
