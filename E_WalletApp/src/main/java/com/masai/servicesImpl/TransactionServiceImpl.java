package com.masai.servicesImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.LoginDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveTransactionDAL;
import com.masai.servicesIntr.TransactionServiceIntr;

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
	public Transaction addTransactionService(Transaction transaction, String userid) {

		UserAccountDetails users = registerUserDAL.findById(userid).get();
		//
//		UserAccountDetails user1 = new UserAccountDetails();
//		user1.setId(userid);
		// transaction.setUser(users);

		users.getTransactions().add(transaction);

		registerUserDAL.save(users);
		return transaction;
	}

//==========================================================================================
	@Override
	public Set<Transaction> displayAllTransactionsSevice(String uniqueID) {

		System.out.println(uniqueID);

		UserAccountDetails user = registerUserDAL.findById((currentSessionDB.findById(uniqueID).get()).getUserId())
				.get();

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

		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
