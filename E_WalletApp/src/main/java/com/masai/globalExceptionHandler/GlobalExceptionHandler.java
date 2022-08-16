package com.masai.globalExceptionHandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.masai.exceptions.BillErrorDetails;
import com.masai.exceptions.BillPaymentException;
import com.masai.exceptions.ExceptionDetails;
import com.masai.exceptions.UserAlreadyExistException;
import com.masai.exceptions.UserInputInvalidException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserInputInvalidException.class)
	public ResponseEntity<ExceptionDetails> userInputIsInvaild(UserInputInvalidException ex,WebRequest wr){

		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());
		
		return new ResponseEntity<>(exceptionDetails,HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ExceptionDetails> userAlreadyExsit(UserAlreadyExistException ex,WebRequest wr){
		
		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());
		
		return new ResponseEntity<>(exceptionDetails,HttpStatus.BAD_REQUEST);
	}
	
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
	public ResponseEntity<ExceptionDetails> parentsExceptionHandler(Exception ex, WebRequest wr){
		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());
		
		return new ResponseEntity<>(exceptionDetails,HttpStatus.BAD_REQUEST);
	}

}
