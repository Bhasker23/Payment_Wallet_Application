package com.masai.servicesIntr;

import java.util.List;

import com.masai.models.CoustomerWithWallet;
import com.masai.models.Customer;
import com.masai.models.Wallet;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveCustomerDAL;

public interface WalletServiceIntr {

	public CoustomerWithWallet showBalanceInWallet(RegisterUserDAL regDao,CurrentSessionDAL csDal,String unqId);
	
	public Wallet fundTransferFromOneWalletToOtherWallet(String targetMobileNumber,Double amount,RegisterUserDAL regDao,CurrentSessionDAL csDal,String unqId);

	public CoustomerWithWallet depostAmountFromWalletToBankAccount(double amount,RegisterUserDAL regDao,CurrentSessionDAL csDal,String unqId);
	
	public List<Customer> getAllCustomer(SaveCustomerDAL cusDep,CurrentSessionDAL csDal,String unqId);
	
	public Customer updateCustomer(Customer cus,RegisterUserDAL regDao,CurrentSessionDAL csDal,String unqId);
	
	public CoustomerWithWallet addMoneyIntoWalletFromBankAccount(double amount,RegisterUserDAL regDao,CurrentSessionDAL csDal,String unqId);
	
}
