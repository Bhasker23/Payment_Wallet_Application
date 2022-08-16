package com.masai.servicesImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.controllers.TranscationController;
import com.masai.exceptions.UserNotFindException;
import com.masai.models.BillPayment;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.LoginDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.servicesIntr.BillPaymentIntr;

@Service
public class BillPaymentImpl implements BillPaymentIntr {

	@Autowired
	private LoginDAL currentSessionDB;

	@Autowired
	private RegisterUserDAL userDB;

	@Autowired
	private TranscationController saveTransaction;

	@Override
	public Transaction payBill(BillPayment bill, String uid) {

		UserAccountDetails user = (userDB.findById((currentSessionDB.findById(uid).get()).getUserId())).get();

		if (user == null) {
			throw new UserNotFindException("please Login first...");
		}

		Transaction transaction = new Transaction();

		Random random = new Random();

		transaction.setTransactionId(Math.abs(random.nextInt() + 8769797));

//		transaction.setDescription(bill.getBillType() + " bill of amount " + bill.getBillAmount() + " " + " paid on "
//				+ transaction.getLocalDateTime());
		transaction.setDescription("bill paid");
		transaction.setTransactionAmount(bill.getBillAmount());
		transaction.setTransationType(bill.getBillType());
		transaction.setUser(user);
		return saveTransaction.addTransactionHandler(transaction);
	}

}
