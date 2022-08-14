package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Transaction;
import com.masai.servicesImpl.TransactionServiceImpl;

@RestController
public class TranscationController {

	@Autowired
	TransactionServiceImpl transactionServiceImpl;

	public Transaction addTransactionHandler(Transaction transaction, String userId) {

		return transactionServiceImpl.addTransactionService(transaction, userId);

	}

}
