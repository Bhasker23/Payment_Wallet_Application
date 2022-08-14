package com.masai.servicesIntr;

import java.util.Set;

import com.masai.models.Transaction;

public interface TransactionServiceIntr {

	public Transaction addTransactionService(Transaction transaction, String userid);

	public Set<Transaction> displayAllTransactionsSevice(String userid);

	public Set<Transaction> displayAllTransactionsByTypeSevice(String userId, String transactionType);

}
