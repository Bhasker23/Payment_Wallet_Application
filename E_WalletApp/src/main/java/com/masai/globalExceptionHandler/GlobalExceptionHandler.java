package com.masai.globalExceptionHandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.masai.exceptions.ExceptionDetails;
import com.masai.exceptions.UserAlreadyExistException;
import com.masai.exceptions.UserInputInvalidException;

@ControllerAdvice
public class GlobalExceptionHandler {
<<<<<<< HEAD

=======
	
	@ExceptionHandler(UserInputInvalidException.class)
	public ResponseEntity<ExceptionDetails> userInputIsInvaild(UserInputInvalidException ex,WebRequest wr){

		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());
		
		return new ResponseEntity<>(exceptionDetails,HttpStatus.BAD_REQUEST);
	
	}
	
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ExceptionDetails> userAlreadyExsit(UserAlreadyExistException ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());
<<<<<<< HEAD

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

=======
		
		return new ResponseEntity<>(exceptionDetails,HttpStatus.BAD_REQUEST);
	}
	
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDetails> parentsExceptionHandler(Exception ex, WebRequest wr) {
		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
	}

}
