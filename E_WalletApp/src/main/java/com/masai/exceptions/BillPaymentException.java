package com.masai.exceptions;

public class BillPaymentException extends RuntimeException{

	public BillPaymentException() {
		
	}
	
	public BillPaymentException(String message) {
		super(message);
	}
	
}
