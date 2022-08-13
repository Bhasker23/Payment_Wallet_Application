package com.masai.servicesIntr;

import java.util.List;

import com.masai.models.CoustomerWithWallet;
import com.masai.models.Customer;
import com.masai.models.Wallet;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveCustomerDAL;

public interface WalletServiceIntr {

	public CoustomerWithWallet showBalanceByPhoneNumber(String phoneNumber,RegisterUserDAL regDao);
	
	public Wallet fundTransfer(String sourceMobileNumber,String targetMobileNumber,Double amount,RegisterUserDAL regDao);

	public CoustomerWithWallet depostAmount(String phoneNumber,double amount,RegisterUserDAL regDao);
	
	public List<Customer> getAllCustomer(SaveCustomerDAL cusDep);
	
	public Customer updateCust(Customer cus,RegisterUserDAL regDao);
	
	public CoustomerWithWallet addMoneyIntoWallet(Wallet wal, double amount,RegisterUserDAL regDao);
	
}
