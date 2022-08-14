package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.servicesImpl.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TranscationController {

	@Autowired
	TransactionServiceImpl transactionServiceImpl;

	@PostMapping("/add")
	public UserAccountDetails addTransactionHandler(Transaction transaction) {

		return transactionServiceImpl.addTransactionService(transaction);

	}

}
