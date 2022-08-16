package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.services.LoginUserServiceImpl;
import com.masai.userInput.Login;

@RestController
@RequestMapping("/user")
public class UserloginController {

	@Autowired
	private LoginUserServiceImpl curd;

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Login login){
		
         
		System.out.println("satyam");
		return new ResponseEntity<>(curd.userLogin(login), HttpStatus.OK);

	}

	
	@DeleteMapping("/logout/{uniqueId}")
	public ResponseEntity<String> logOutUser(@PathVariable String uniqueId ){
		
		return new ResponseEntity<>(curd.logOutUser(uniqueId) , HttpStatus.OK); 
	}
	

}
