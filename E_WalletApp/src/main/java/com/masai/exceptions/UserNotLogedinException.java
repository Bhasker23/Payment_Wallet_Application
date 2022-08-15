package com.masai.exceptions;

public class UserNotLogedinException extends RuntimeException {

	
	public UserNotLogedinException() {}

	public UserNotLogedinException(String message) {
		super(message);
	}
	
}
