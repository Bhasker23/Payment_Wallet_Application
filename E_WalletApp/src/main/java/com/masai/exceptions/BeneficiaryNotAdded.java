package com.masai.exceptions;

import net.bytebuddy.implementation.bind.annotation.Super;

public class BeneficiaryNotAdded extends RuntimeException {

	public BeneficiaryNotAdded( String msg) {
		
		super(msg);
	}
}
