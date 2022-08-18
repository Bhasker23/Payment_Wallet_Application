package com.masai.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.BankAccount;
import com.masai.models.Customer;
import com.masai.services.BankAccountServicesImpl;

@RestController
@RequestMapping("/bank")
public class BankAccountController {
	
	
	@Autowired
	private BankAccountServicesImpl curd;

	@PostMapping("/addAccount/{uniqueId}")
      public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankAccount,@PathVariable String uniqueId ){
	     	BankAccount aaccountAdded = curd.addBank(bankAccount,uniqueId);
         	return new ResponseEntity<>(bankAccount,HttpStatus.ACCEPTED); 
       }
	
	@DeleteMapping("/removeBankAccount/{uniqueId}")
	public ResponseEntity<BankAccount> removeBankAccount(@PathVariable String uniqueId,@RequestParam Integer accountNumber){
		return new ResponseEntity<>(curd.removeBank(accountNumber, uniqueId),HttpStatus.OK);
	}
	
	@GetMapping("/viewAccount/{uniqueId}")
	public ResponseEntity<BankAccount> viewAccount(@RequestParam Integer accountNumber,@PathVariable String uniqueId){
		return new ResponseEntity<>(curd.viewBankAccountI(accountNumber, uniqueId),HttpStatus.OK);
	}
	
	@GetMapping("/viewAllAccount/{uniqueId}")
	public ResponseEntity<Set<BankAccount>> viewAllBankAccount(@PathVariable String uniqueId){
		return new ResponseEntity<>(curd.viewAllAccount(uniqueId),HttpStatus.OK);
	}

}
