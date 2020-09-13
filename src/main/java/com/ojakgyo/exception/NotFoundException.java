package com.ojakgyo.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{
   public NotFoundException() {
      super("not found. Sorry!");
   }
   
   public NotFoundException(String message) {
	      super(message);
	   }

}