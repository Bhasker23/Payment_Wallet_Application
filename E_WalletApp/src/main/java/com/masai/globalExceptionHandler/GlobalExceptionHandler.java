package com.masai.globalExceptionHandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.masai.exceptions.BankAccountNotExsists;
import com.masai.exceptions.BankAlreadyAdded;
import com.masai.exceptions.CustomerDoesNotExist;
import com.masai.exceptions.ExceptionDetails;
import com.masai.exceptions.InsufficientBalance;
import com.masai.exceptions.NotAnyBankAddedYet;
import com.masai.exceptions.UserAlreadyExistException;
import com.masai.exceptions.UserInputInvalidException;
import com.masai.exceptions.UserNotFindException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserInputInvalidException.class)
	public ResponseEntity<ExceptionDetails> userInputIsInvaild(UserInputInvalidException ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ExceptionDetails> userAlreadyExsit(UserAlreadyExistException ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());
		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFindException.class)
	public ResponseEntity<ExceptionDetails> userNotFind(UserNotFindException ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();

		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setLocalDate(LocalDate.now());
		exceptionDetails.setDescription(wr.getDescription(false));

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotAnyBankAddedYet.class)
	public ResponseEntity<ExceptionDetails> userAccountNotExsis(BankAccountNotExsists ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BankAlreadyAdded.class)
	public ResponseEntity<ExceptionDetails> bankAccountAlreadyAdded(BankAlreadyAdded ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(CustomerDoesNotExist.class)
	public ResponseEntity<String> custDoesNotExist(CustomerDoesNotExist cde) {

		return new ResponseEntity<String>(cde.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InsufficientBalance.class)
	public ResponseEntity<ExceptionDetails> insfBal(InsufficientBalance isb, WebRequest wr) {

		ExceptionDetails exp = new ExceptionDetails();
		exp.setMessage(isb.getMessage());
		exp.setLocalDate(LocalDate.now());
		exp.setDescription(wr.getDescription(false));

		return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(NotAnyBankAddedYet.class)
//	public ResponseEntity<ExceptionDetails> notAnyBankAdded(NotAnyBankAddedYet ex,WebRequest wr){
//		ExceptionDetails exp = new ExceptionDetails();
//		exp.setMessage(ex.getMessage());
//		exp.setLocalDate(LocalDate.now());
//		exp.setDescription(wr.getDescription(false));
//		
//		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDetails> parentsExceptionHandler(Exception ex, WebRequest wr) {
		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
	}

}
