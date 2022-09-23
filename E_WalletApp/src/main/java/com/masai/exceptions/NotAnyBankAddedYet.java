package com.masai.exceptions;

public class NotAnyBankAddedYet extends RuntimeException {

	public NotAnyBankAddedYet() {
	}

	public NotAnyBankAddedYet(String message) {
		super(message);
	}


}
