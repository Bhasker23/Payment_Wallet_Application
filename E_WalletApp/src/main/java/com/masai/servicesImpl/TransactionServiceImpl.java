package com.masai.servicesImpl;

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
	public UserAccountDetails addTransactionService(Transaction transaction) {

		UserAccountDetails users = (registerUserDAL.findById("67890")).get();
		users.getTransactions().add(transaction);
		registerUserDAL.save(users);
		return users;
	}

}
