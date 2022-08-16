package com.masai.exceptions;

public class InsufficientBalance extends RuntimeException {

	public InsufficientBalance(String msg) {
		
		super(msg);
	}
}
