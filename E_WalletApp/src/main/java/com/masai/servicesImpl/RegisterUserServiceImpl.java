package com.masai.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.UserAlreadyExistException;
import com.masai.models.BankAccount;
import com.masai.models.BeneficiaryDetails;
import com.masai.models.Customer;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.models.Wallet;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveBankAccDAL;
import com.masai.repositories.SaveBeneficiaryDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.repositories.SaveTransactionDAL;
import com.masai.repositories.SaveWalletDAL;
import com.masai.servicesIntr.RegisterUserServiceIntr;

@Service
public class RegisterUserServiceImpl implements RegisterUserServiceIntr {

	@Autowired
	private RegisterUserDAL userDB;
	
	@Autowired
	private SaveCustomerDAL customerDB;
	
	@Autowired
	private SaveBankAccDAL bankaccDB;
	
	@Autowired
	private SaveBeneficiaryDAL beneficiaryDB;
	
	@Autowired
	private SaveTransactionDAL transactionDB;
	
	@Autowired
	private SaveWalletDAL walletDB;
	

	@Override
	public UserAccountDetails registerUser(Customer customer) {
		
		if((userDB.findById(customer.getPhone())).isPresent()) {
			throw new UserAlreadyExistException("You are already SignedUp please Login.");
			}
		
		
		UserAccountDetails user = new UserAccountDetails();
		
		
		user.setId(customer.getPhone());
	    user.setCustomer(customer);
	    
		Wallet wallet = new Wallet();
		user.setWallet(wallet);
		
		Transaction transaction = new Transaction();
		BankAccount bankAcc = new BankAccount();
		
	    BeneficiaryDetails beniBeneficiaryDetail = new BeneficiaryDetails();
		

		customerDB.save(customer);
		walletDB.save(wallet);
		transactionDB.save(transaction);
		bankaccDB.save(bankAcc);
		beneficiaryDB.save(beniBeneficiaryDetail);
		return userDB.save(user);
	}
	//this method will be add in Add bank account serviceIMPL
//	public UserAccountDetails addBankAccount(BankAccount bankAccount) {
//		
//		UserAccountDetails user = (userDB.findById("67890")).get();
//		
//		user.getBankAccounts().add(bankAccount);
//		
//		userDB.save(user);
//		
//		return (userDB.findById("67890")).get();
//		
//	}

}