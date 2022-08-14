package com.masai.servicesImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveTransactionDAL;
import com.masai.servicesIntr.TransactionServiceIntr;

@Service
public class TransactionServiceImpl implements TransactionServiceIntr {

	@Autowired
	SaveTransactionDAL saveTransactionDAL;

	@Autowired
	RegisterUserDAL registerUserDAL;

	@Override
	public Transaction addTransactionService(Transaction transaction, String userid) {

		UserAccountDetails users = registerUserDAL.findById(userid).get();
		users.getTransactions().add(transaction);
		registerUserDAL.save(users);
		return transaction;
	}

	@Override
	public Set<Transaction> displayAllTransactionsSevice(String userid) {

		// (registerUserDAL.findById(userid).get()).getTransactions();

		return (registerUserDAL.findById(userid).get()).getTransactions();

	}

	@Override
	public Set<Transaction> displayAllTransactionsByTypeSevice(String userId, String transactionType) {

		Set<Transaction> allTransactions = (registerUserDAL.findById(userId).get()).getTransactions();

		Set<Transaction> byTypeTransactions = new HashSet<>();

		for (Transaction sTransaction : allTransactions) {
			if (sTransaction.getTransationType().equals(transactionType)) {
				byTypeTransactions.add(sTransaction);
			}
		}

		return byTypeTransactions;

	}

}
