package com.masai.globleExceptionHendler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.masai.exceptions.CustomerDoesNotExist;
import com.masai.exceptions.InsufficientBalance;

@ControllerAdvice
public class GlobleExceptionHandler {

	
	@ExceptionHandler(CustomerDoesNotExist.class)
	public ResponseEntity<String> custDoesNotExist(CustomerDoesNotExist cde){
		
		return new ResponseEntity<String>(cde.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InsufficientBalance.class)
	public ResponseEntity<String> insfBal( InsufficientBalance isb){
		
		return new ResponseEntity<String>(isb.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
