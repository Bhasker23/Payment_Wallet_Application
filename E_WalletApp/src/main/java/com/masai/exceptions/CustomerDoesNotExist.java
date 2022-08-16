package com.masai.exceptions;

public class CustomerDoesNotExist extends RuntimeException {

	public CustomerDoesNotExist(String msg){
		super(msg);
	}
	
}
