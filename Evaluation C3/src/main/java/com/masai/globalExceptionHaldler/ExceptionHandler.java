package com.masai.globalExceptionHaldler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.masai.Exception.DepartmentNotExist;
import com.masai.Exception.EmployeeNotExist;
import com.masai.modal.MyError;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(DepartmentNotExist.class)
	public ResponseEntity<MyError> deptNotFund(DepartmentNotExist deptNot, WebRequest req) {

		MyError mError = new MyError();

		mError.setMsg(deptNot.getMessage());
		mError.setDateTime(LocalDate.now());
		mError.setPath(req.getDescription(false));

		return new ResponseEntity<>(mError, HttpStatus.NOT_FOUND);
	}
	
	

	@org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotExist.class)
	public ResponseEntity<MyError> deptNotFund(EmployeeNotExist empNot, WebRequest req) {

		MyError mError = new MyError();

		mError.setMsg(empNot.getMessage());
		mError.setDateTime(LocalDate.now());
		mError.setPath(req.getDescription(false));

		return new ResponseEntity<>(mError, HttpStatus.NOT_FOUND);
	}

}
