package com.masai.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BillPaymentException;
import com.masai.exceptions.InsufficientBalance;
import com.masai.models.BillPayment;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.models.ViewBill;
import com.masai.models.Wallet;
import com.masai.repositories.BillPaymentDAO;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveWalletDAL;
import com.masai.userInput.CurrentSession;

@Service
public class BillPaymentImpl implements BillPaymentIntr {

	@Autowired
	private BillPaymentDAO bDao;

	@Autowired
	private RegisterUserDAL rDao;

	@Autowired
	private SaveWalletDAL wDao;

	@Autowired
	private CurrentSessionDAL curDao;

	@Autowired
	private TransactionServiceImpl saveTransaction;

	Random random = new Random();

	@Override
	public BillPayment addBillPayment(BillPayment billPayment, String uniqId) {

		UserAccountDetails user = rDao.findById((curDao.findById(uniqId).get()).getUserId()).get();

		if (user == null) {
			throw new BillPaymentException("first you have to login..");
		}

		if (user.getWallet().getBalance() < billPayment.getBillAmount() || user.getWallet().getBalance() == null) {
			throw new InsufficientBalance("Insufficent Balance in Wallet");
		}

		BillPayment billPayment2 = new BillPayment();
		billPayment2.setBillAmount(billPayment.getBillAmount());
		billPayment2.setBillId(billPayment.getBillId());
		billPayment2.setBillType(billPayment.getBillType());
		billPayment2.setUser(user);


		
		Random random = new Random();

		Transaction transaction = new Transaction();

		transaction.setTransactionId(random.nextInt());
		transaction.setTransactionAmount(billPayment.getBillAmount());
		transaction.setTransationType(billPayment.getBillType());
		transaction.setDescription(transaction.getTransationType()+" BIll Paid");
		transaction.setUser(user);

		user.getWallet().setBalance((user.getWallet().getBalance() - billPayment.getBillAmount()));

		saveTransaction.addTransactionService(transaction);
		bDao.save(billPayment2);
		return billPayment;
		// return transaction;

	}

	@Override
	public List<ViewBill> viewBillPayment(String uniqId) throws BillPaymentException {

		Optional<CurrentSession> opt1 = curDao.findById(uniqId);

		if (!opt1.isPresent()) {
			throw new BillPaymentException("first you have to login..");
		}

		UserAccountDetails user = rDao.findById((curDao.findById(uniqId).get()).getUserId()).get();

		// Set<BillPayment> bao

		Wallet wallet = user.getWallet();

		List<BillPayment> bills2 = bDao.findByUserId(user.getId());

		List<ViewBill> viewBills = new ArrayList<>();

		for (BillPayment b : bills2) {
			viewBills.add(new ViewBill(b.getBillId(), b.getBillAmount(), b.getBillType(), wallet.getWalletId()));
		}

		return viewBills;
	}
}
