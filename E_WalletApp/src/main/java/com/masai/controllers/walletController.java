package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.CoustomerWithWallet;
import com.masai.models.Customer;
import com.masai.models.Transaction;
import com.masai.models.Wallet;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.services.WalletServiceImp;

@RestController
@RequestMapping("/wallet")
public class walletController {
	
	@Autowired
	private RegisterUserDAL regDao;
	
	@Autowired
	private SaveCustomerDAL cusDep;
	
	@Autowired
	WalletServiceImp imp;
	
	@Autowired
	CurrentSessionDAL csDal;
	
	
	
	

	@GetMapping("/balance/{unqId}")
	public CoustomerWithWallet showBalance(@PathVariable String unqId) {

		System.out.println(regDao);

		return imp.showBalanceInWallet(regDao, csDal,unqId);
	}
	
	
	
	
	
	@PutMapping("/fundTransfer/{tNumber}/{amount}/{unqId}")
	public Transaction fundTf(@PathVariable String tNumber, @PathVariable double amount,@PathVariable String unqId) {
		
		return imp.fundTransferFromOneWalletToOtherWallet( tNumber, amount, regDao, csDal, unqId);
	}
	
	
	
	
	
	@PutMapping("/depositAmount/{amount}/{unqId}")
	public Transaction depositAmountInWallet(@PathVariable double amount,@PathVariable String unqId) {
		
		return imp.depostAmountFromWalletToBankAccount(amount, regDao, csDal, unqId);
	}
	
	
	
	
	@GetMapping("/customers/{unqId}")
	public List<Customer> getAllCustomer(@PathVariable String unqId){
		
		return imp.getAllCustomer(cusDep, csDal, unqId);
	}
	
	
	
	
	@PutMapping("/updateCustomer/{unqId}")
	public Customer updateCustomer(@RequestBody Customer cus,@PathVariable String unqId) {
		
		return imp.updateCustomer(cus, regDao, csDal, unqId);
	}
	
	
	@PutMapping("/addMoney/{amount}/{unqId}")
	public Transaction addMoneyIntoWallet(@PathVariable double amount,@PathVariable String unqId) {
		
		return imp.addMoneyIntoWalletFromBankAccount(amount, regDao, csDal, unqId);
	}
	
}
