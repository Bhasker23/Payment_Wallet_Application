package com.masai.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Transaction;
import com.masai.servicesImpl.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TranscationController {

	@Autowired
	TransactionServiceImpl transactionServiceImpl;

	public Transaction addTransactionHandler(Transaction transaction, String userId) {

		return transactionServiceImpl.addTransactionService(transaction, userId);

	}

	@GetMapping("/transaction_History/{userId}")
	public Set<Transaction> getAllTransactionsHistory(@PathVariable String userId) {

		return transactionServiceImpl.displayAllTransactionsSevice(userId);

	}

	@GetMapping("/transaction_History/{userId}/{typeOftransaction}")
	public Set<Transaction> getAllTransactionsHistoryByType(@PathVariable String userId,
			@PathVariable String typeOftransaction) {

		return transactionServiceImpl.displayAllTransactionsByTypeSevice(userId, typeOftransaction);

	}

}
