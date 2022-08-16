package com.masai.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.LoginSignUp.CurrentSession;
import com.masai.exceptions.BillPaymentException;
import com.masai.models.BillPayment;
import com.masai.models.UserAccountDetails;
import com.masai.models.ViewBill;
import com.masai.models.Wallet;
import com.masai.repositories.BillPaymentDAO;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveWalletDAL;

import com.masai.servicesIntr.BillPaymentIntr;

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

	@Override
	public BillPayment addBillPayment(BillPayment billPayment, String uniqId) {

		UserAccountDetails user = rDao.findById((curDao.findById(uniqId).get()).getUserId()).get();

		if (user == null) {
			throw new BillPaymentException("first you have to login..");
		}

		BillPayment billPayment2 = new BillPayment();
		billPayment2.setBillAmount(billPayment.getBillAmount());
		billPayment2.setBillId(billPayment.getBillId());
		billPayment2.setBillType(billPayment.getBillType());
		billPayment2.setUser(user);

		bDao.save(billPayment2);

		return billPayment;
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

		List<BillPayment> bills = bDao.findAll();
		List<ViewBill> viewBills = new ArrayList<>();

		for (BillPayment b : bills) {
			if (b.getUser().getId().equals(user.getTransactions())) {
				viewBills.add(new ViewBill(b.getBillId(), b.getBillAmount(), b.getBillType(), wallet.getWalletId()));

			}
		}

		return viewBills;
	}

	// Optional<CurrentSession> opt1 = curDao.findById(uniqId);
//
//		if (!opt1.isPresent()) {
//			throw new BillPaymentException("first you have to login..");
//		}
//
//		Optional<UserAccountDetails> user = rDao.findById(opt1.get().getUserId());
//
//		Optional<Wallet> opt = wDao.findById(user.get().getWallet().getWalletId());
//
//		List<BillPayment> totalbill = bDao.findAll();
//
////		System.out.println(totalbill);
//		List<ViewBill> viewBill = new ArrayList<>();
//		
//
//		for (BillPayment bill : totalbill) {
//			
//			if (bill.getWallet().getWalletId() == opt.get().getWalletId()) {
//			
//				ViewBill vBill = new ViewBill();
//				vBill.setBillId(bill.getBillId());
//				vBill.setBillAmount(bill.getBillAmount());
//				vBill.setBillType(bill.getBillType());
//				vBill.setWalletId(bill.getWallet().getWalletId());
//				
////				System.out.println(bill);
////				bipy.getWallet().setWalletId(bill.getWallet().getWalletId());
//				
//				viewBill.add(vBill);
//			}

}

//	private LoginDAL currentSessionDB;
//
//	@Autowired
//	private RegisterUserDAL userDB;
//
//	@Autowired
//	private TranscationController saveTransaction;
//
//	@Override
//	public Transaction payBill(BillPayment bill, String uid) {
//
//		UserAccountDetails user = (userDB.findById((currentSessionDB.findById(uid).get()).getUserId())).get();
//
//		if (user == null) {
//			throw new UserNotFindException("please Login first...");
//		}
//
//		Transaction transaction = new Transaction();
//
//		Random random = new Random();
//
//		transaction.setTransactionId(Math.abs(random.nextInt() + 8769797));
//
////		transaction.setDescription(bill.getBillType() + " bill of amount " + bill.getBillAmount() + " " + " paid on "
////				+ transaction.getLocalDateTime());
//		transaction.setDescription("bill paid");
//		transaction.setTransactionAmount(bill.getBillAmount());
//		transaction.setTransationType(bill.getBillType());
//		// transaction.setUser(user);
//		return saveTransaction.addTransactionHandler(transaction, user.getId());
//>>>>>>> 301c524c7a34a0bc97843cfddd56816b99590b7b
