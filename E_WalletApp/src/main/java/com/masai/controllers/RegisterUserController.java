package com.masai.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
=======
import com.masai.exceptions.UserInputInvalidException;
import com.masai.models.BankAccount;
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
import com.masai.models.Customer;
import com.masai.models.UserAccountDetails;
import com.masai.servicesImpl.RegisterUserServiceImpl;
import com.masai.userInput.UserInput;

@RestController
@RequestMapping("/user")
public class RegisterUserController {

	@Autowired
	private RegisterUserServiceImpl curd;

	@PostMapping("/register")
<<<<<<< HEAD
	public ResponseEntity<UserAccountDetails> registerUser(@RequestBody Customer customer) {

		UserAccountDetails savedCustomer = curd.registerUser(customer);

		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
=======
	public ResponseEntity<UserAccountDetails> registerUser(@RequestBody UserInput input) {
		
		//if(error.hasErrors()) {throw new UserInputInvalidException("Invalid Input");}
		
		UserAccountDetails savedCustomer = curd.registerUser(input);
		
		return new ResponseEntity<>(savedCustomer,HttpStatus
				.CREATED);
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
	}
}
