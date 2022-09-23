package com.masai.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate start = LocalDate.parse(from, formatter);
		LocalDate end = LocalDate.parse(to, formatter);

		Set<Transaction> allTransactions = (registerUserDAL
				.findById(currentSessionDB.findById(uniqueID).get().getUserId()).get()).getTransactions();

		Set<Transaction> byDateRangeTransactions = new HashSet<>();

		for (Transaction transaction : allTransactions) {

			LocalDateTime localDateTime = transaction.getLocalDateTime();
			String date = localDateTime.getDayOfMonth() + "-0" + localDateTime.getMonthValue() + "-"
					+ localDateTime.getYear();
			LocalDate temp = LocalDate.parse(date, formatter);

			if ((temp.isAfter(start) && temp.isBefore(end)) || temp.equals(start) || temp.equals(end)) {
				byDateRangeTransactions.add(transaction);
			}

		}

		return byDateRangeTransactions;
	}

	// ==========================================================================================

}
