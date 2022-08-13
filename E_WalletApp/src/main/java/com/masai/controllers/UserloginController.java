package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.LoginSignUp.Login;
import com.masai.models.Customer;
import com.masai.models.UserAccountDetails;
import com.masai.servicesImpl.LoginUserServiceImpl;

@RestController
@RequestMapping("/login")
public class UserloginController {

	@Autowired
	private LoginUserServiceImpl curd;
	
	

	@PostMapping("/user")
	public ResponseEntity<String> loginUser(@RequestBody Login login){
		
		return new ResponseEntity<>(curd.userLogin(login), HttpStatus.OK);
		
	}
	
}
