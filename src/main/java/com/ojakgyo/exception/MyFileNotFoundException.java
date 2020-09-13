package com.ojakgyo.exception;

@SuppressWarnings("serial")
public class MyFileNotFoundException extends RuntimeException{

	public MyFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyFileNotFoundException(String message) {
		super(message);
	}
	
}
