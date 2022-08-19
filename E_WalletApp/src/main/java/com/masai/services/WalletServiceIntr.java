package com.masai.services;

import java.util.List;

import com.masai.models.BankAccount;
import com.masai.models.CoustomerWithWallet;
import com.masai.models.Customer;
import com.masai.models.Transaction;
import com.masai.models.Wallet;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveCustomerDAL;

public interface WalletServiceIntr {

	public CoustomerWithWallet showBalanceInWallet(String unqId);
	
	public Transaction fundTransferFromOneWalletToOtherWallet(String targetMobileNumber,Double amount,String unqId);

	public Transaction depostAmountFromWalletToBankAccount(BankAccount bk,double amount,String unqId);
	
	public List<Customer> getAllCustomer(String unqId);
	
	public Customer updateCustomer(Customer cus,String unqId);
	
	public Transaction addMoneyIntoWalletFromBankAccount(BankAccount bk,double amount,String unqId);
	
}
