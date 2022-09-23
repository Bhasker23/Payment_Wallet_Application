package com.masai.services;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BankAccountNotExsists;
import com.masai.exceptions.BankAlreadyAdded;
import com.masai.exceptions.NotAnyBankAddedYet;
import com.masai.exceptions.UserNotLogedinException;
import com.masai.models.BankAccount;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.LoginDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveBankAccDAL;

@Service
public class BankAccountServicesImpl implements BankServicesIntr {

	@Autowired
	private RegisterUserDAL userDB;
	
	@Autowired
	private LoginDAL loginDb;
	
	@Autowired
	private SaveBankAccDAL bankDetrailsDb;

	@Override
	public BankAccount addBank(BankAccount bankAccount, String uniqueId) throws UserNotLogedinException,BankAlreadyAdded {
		
		UserAccountDetails user = userDB.findById((loginDb.findById(uniqueId).get()).getUserId()).get();
		
		if(user==null) {
			throw new UserNotLogedinException("Please Login first");
		}
		
		
		if((bankDetrailsDb.findById(bankAccount.getAccountNumber())).isPresent()) {
			throw new BankAlreadyAdded("Bank with "+bankAccount.getAccountNumber()+" this Account Nuber Already Exist");
		}
		
		bankAccount.setUser(user);
		user.getBankAccounts().add(bankAccount);
		
		userDB.save(user);
		return bankAccount;
	}

	@Override
	public BankAccount removeBank(Integer accountNumber,String uniqueId) throws BankAccountNotExsists, UserNotLogedinException{
		UserAccountDetails user = userDB.findById((loginDb.findById(uniqueId).get()).getUserId()).get();
		
		if(user==null) {
			throw new UserNotLogedinException("Please Login first");
		}
		
		if((bankDetrailsDb.findById(accountNumber)).isEmpty()) {
			throw new BankAccountNotExsists("Bank with "+accountNumber+" this Account Nuber not Exist");
		}
		
		BankAccount removedBankAccount = bankDetrailsDb.findById(accountNumber).get(); 
		
		System.out.println(removedBankAccount.getAccountNumber());
		System.out.println(user.getBankAccounts().size());
		if(!user.getBankAccounts().contains(removedBankAccount)) {
			throw new BankAccountNotExsists("Bank with "+accountNumber+" Account Number does not present");
		}
		
		bankDetrailsDb.delete(removedBankAccount);
		
		return removedBankAccount;
	}

	@Override
	public BankAccount viewBankAccountI(Integer accountNumber, String uniqueId) throws BankAccountNotExsists, UserNotLogedinException {
		UserAccountDetails user = userDB.findById((loginDb.findById(uniqueId).get()).getUserId()).get();
		if(user==null) {
			throw new UserNotLogedinException("Please Login first");
		}
		
		BankAccount bankAccount = bankDetrailsDb.findById(accountNumber).get();
	

		if(!user.getBankAccounts().contains(bankAccount)) {
			throw new BankAccountNotExsists("Bank with "+accountNumber+" this Account Nuber not Exist");
		}
		
		
		return bankAccount;
	}

	@Override
	public Set<BankAccount> viewAllAccount(String uniqueId) throws UserNotLogedinException, NotAnyBankAddedYet {
		UserAccountDetails user = userDB.findById((loginDb.findById(uniqueId).get()).getUserId()).get();
		if(user==null) {
			throw new UserNotLogedinException("Please Login first");
		}
		
	    Set<BankAccount> banks =  user.getBankAccounts();
	    
	    if(banks.isEmpty()) {
	    	throw new BankAccountNotExsists("Not Any BankAccount Added Yet");
	    }
		
		return banks;
	}
	
	
	
	
	
	
	
	
	
}
