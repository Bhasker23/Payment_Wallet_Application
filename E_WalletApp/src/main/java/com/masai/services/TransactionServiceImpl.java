package com.masai.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.UserNotLogedinException;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.LoginDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveTransactionDAL;
import com.masai.userInput.CurrentSession;

@Service
public class TransactionServiceImpl implements TransactionServiceIntr {

	@Autowired
	private SaveTransactionDAL saveTransactionDAL;

	@Autowired
	private RegisterUserDAL registerUserDAL;

	@Autowired
	private LoginDAL currentSessionDB;
	// ==========================================================================================

	@Override
	public Transaction addTransactionService(Transaction transaction) {

     	UserAccountDetails users = transaction.getUser();
     	users.getTransactions().add(transaction);
      	registerUserDAL.save(users);
		return transaction;
	}

//==========================================================================================
	@Override
	public Set<Transaction> displayAllTransactionsSevice(String uniqueID) {
		System.out.println(uniqueID);

		Optional<CurrentSession> currentSessionOp = currentSessionDB.findById(uniqueID);
		
		if(currentSessionOp.isEmpty()) {
			throw new UserNotLogedinException("You are not loged in");
		}
	
		UserAccountDetails user = registerUserDAL.findById(currentSessionOp.get().getUserId()).get();
		
		return user.getTransactions();
	}

//===================================================================================
	@Override
	public Set<Transaction> displayAllTransactionsByTypeSevice(String uniqueID, String transactionType) {

		Set<Transaction> allTransactions = (registerUserDAL
				.findById(currentSessionDB.findById(uniqueID).get().getUserId()).get()).getTransactions();

		Set<Transaction> byTypeTransactions = new HashSet<>();

		for (Transaction sTransaction : allTransactions) {
			if (sTransaction.getTransationType().equals(transactionType)) {
				byTypeTransactions.add(sTransaction);
			}
		}

		return byTypeTransactions;

	}
	// ==========================================================================================

	@Override
	public Set<Transaction> getTransactionsBetweenDateRangeService(String uniqueID, String from, String to) {

		LocalDate start = LocalDate.parse(from);
		LocalDate end = LocalDate.parse(to);

		Set<Transaction> allTransactions = (registerUserDAL
				.findById(currentSessionDB.findById(uniqueID).get().getUserId()).get()).getTransactions();

		Set<Transaction> byDateRangeTransactions = new HashSet<>();

		for (Transaction transaction : allTransactions) {

			String s = start.getYear() + "" + start.getMonthValue() + "" + "" + start.getDayOfMonth();
			String e = end.getYear() + "" + end.getMonthValue() + "" + "" + end.getDayOfMonth();

			// System.out.println(s.compareTo(e));

			if (s.compareTo(e) <= 0 && s.compareTo(s) > 0) {
				byDateRangeTransactions.add(transaction);
			}
		}

		return byDateRangeTransactions;
	}

	// ==========================================================================================

}
