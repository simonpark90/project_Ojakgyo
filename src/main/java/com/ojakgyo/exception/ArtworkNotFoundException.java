package com.ojakgyo.exception;

public class ArtworkNotFoundException extends RuntimeException{
	public ArtworkNotFoundException() {
		super("Artwork not found. Sorry!");
	} 

}
