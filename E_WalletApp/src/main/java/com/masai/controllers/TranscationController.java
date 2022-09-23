package com.masai.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Transaction;
import com.masai.services.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TranscationController {

	@Autowired
	TransactionServiceImpl transactionServiceImpl;

	public Transaction addTransactionHandler(Transaction transaction) {

		return transactionServiceImpl.addTransactionService(transaction);

	}
	// =============================================================================================

	@GetMapping("/transaction_History/{uniqueId}")
	public Set<Transaction> getAllTransactionsHistory(@PathVariable String uniqueId) {

		return transactionServiceImpl.displayAllTransactionsSevice(uniqueId);

	}

//=============================================================================================
	@GetMapping("/transaction_History/{uniqueId}/{typeOftransaction}")
	public Set<Transaction> getAllTransactionsHistoryByType(@PathVariable String uniqueId,
			@PathVariable String typeOftransaction) {

		return transactionServiceImpl.displayAllTransactionsByTypeSevice(uniqueId, typeOftransaction);

	}
	// =============================================================================================

	@GetMapping("/transactionsBetweenDateRange/{uniqueId}")
	public Set<Transaction> getTransactionsBetweenDateRangeService(@PathVariable String uniqueId,
			@RequestParam String from, @RequestParam String to) {

		return transactionServiceImpl.getTransactionsBetweenDateRangeService(uniqueId, from, to);

	}

}
