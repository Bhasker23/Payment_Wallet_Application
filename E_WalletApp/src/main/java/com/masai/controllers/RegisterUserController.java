package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.BankAccount;
import com.masai.models.Customer;
import com.masai.models.UserAccountDetails;
import com.masai.servicesImpl.RegisterUserServiceImpl;

@RestController
@RequestMapping("/user")
public class RegisterUserController {

	@Autowired
	private RegisterUserServiceImpl curd;
	
	@PostMapping("/register")
	public ResponseEntity<UserAccountDetails> registerUser(@RequestBody Customer customer) {
		
		UserAccountDetails savedCustomer = curd.registerUser(customer);
		
		
		
		return new ResponseEntity<>(savedCustomer,HttpStatus
				.CREATED);
	}
//	//this method will be add in Add bank account controller;
	@PostMapping("/addBank")
	public UserAccountDetails addBankAccount(@RequestBody BankAccount bankAccount){
		
		 return curd.addBankAccount(bankAccount);
		
	}
	
}
