package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.CoustomerWithWallet;
import com.masai.models.Customer;
import com.masai.models.Wallet;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.servicesImpl.WalletServiceImp;

@RestController
public class walletController {
	
	@Autowired
	private RegisterUserDAL regDao;
	
	@Autowired
	private SaveCustomerDAL cusDep;
	
	@Autowired
	WalletServiceImp imp;
	
	
	
	

	@GetMapping("/balance/{mobileNumber}")
	public CoustomerWithWallet showBalance(@PathVariable String mobileNumber) {

		System.out.println(regDao);

		return imp.showBalanceByPhoneNumber(mobileNumber,regDao);
	}
	
	
	
	
	
	@PutMapping("/fundTransfer/{sNumber}/{tNumber}/{amount}")
	public Wallet fundTf(@PathVariable String sNumber,@PathVariable String tNumber, @PathVariable double amount) {
		
		return imp.fundTransfer(sNumber, tNumber, amount, regDao);
	}
	
	
	
	
	
	@PutMapping("/depositAmount/{pNumber}/{amount}")
	public CoustomerWithWallet depositAmountInWallet(@PathVariable String pNumber,@PathVariable double amount) {
		
		return imp.depostAmount(pNumber, amount, regDao);
	}
	
	
	
	
	@GetMapping("/customer")
	public List<Customer> getAllCustomer(){
		
		return imp.getAllCustomer(cusDep);
	}
	
	
	
	
	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer cus) {
		
		return imp.updateCust(cus, regDao);
	}
	
	
	@PutMapping("/addMoney/{amount}")
	public CoustomerWithWallet addMoneyIntoWallet(@RequestBody Wallet wal,@PathVariable double amount) {
		
		return imp.addMoneyIntoWallet(wal, amount, regDao);
	}
	
}
