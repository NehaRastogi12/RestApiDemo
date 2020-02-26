package com.example.demo.exception;

public class NoSuchProductExistException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6870049109052662066L;
	
	public NoSuchProductExistException() {
		super();
	}
	public NoSuchProductExistException(String message) {
		super(message);
	}
}
