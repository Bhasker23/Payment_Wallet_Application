package com.masai.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BillPaymentException.class)
	public ResponseEntity<BillErrorDetails> billHandler1(BillPaymentException be, WebRequest wq){
		
		BillErrorDetails err = new BillErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(be.getMessage());
		err.setDetails(wq.getDescription(false));
		
		return new ResponseEntity<BillErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<BillErrorDetails> billHandler2(NoHandlerFoundException be, WebRequest wq){
		
		BillErrorDetails err = new BillErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(be.getMessage());
		err.setDetails(wq.getDescription(false));
		
		return new ResponseEntity<BillErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BillErrorDetails> billHandler3(Exception be, WebRequest wq){
		
		BillErrorDetails err = new BillErrorDetails();
		err.setTimestamp(LocalDate.now());
		err.setMessage(be.getMessage());
		err.setDetails(wq.getDescription(false));
		
		return new ResponseEntity<BillErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
}
