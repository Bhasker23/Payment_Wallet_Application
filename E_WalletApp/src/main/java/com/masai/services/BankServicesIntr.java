package com.masai.services;

import java.util.Set;

import com.masai.exceptions.BankAccountNotExsists;
import com.masai.exceptions.BankAlreadyAdded;
import com.masai.exceptions.NotAnyBankAddedYet;
import com.masai.exceptions.UserNotLogedinException;
import com.masai.models.BankAccount;

public interface BankServicesIntr {
	
	public BankAccount addBank(BankAccount bankAccount, String uniqueId) throws UserNotLogedinException,BankAlreadyAdded;

	public BankAccount removeBank(Integer accountNumber, String uniqueId) throws BankAccountNotExsists,UserNotLogedinException;
	
	public BankAccount viewBankAccountI(Integer accountNumber,String uniqueId) throws BankAccountNotExsists,UserNotLogedinException ;
	
	public Set<BankAccount> viewAllAccount(String uniqueId) throws UserNotLogedinException,NotAnyBankAddedYet;
	
}
