package com.masai.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.LoginSignUp.CurrentSession;
import com.masai.exceptions.BillPaymentException;
import com.masai.models.BillPayment;
import com.masai.models.Transaction;
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

	@Override
	public ViewBill addBillPayment(BillPayment billPayment, String uniqId) {

		Optional<CurrentSession> opt = curDao.findById(uniqId);

		if (!opt.isPresent()) {
			throw new BillPaymentException("first you have to login..");
		}
		
//		System.out.println("bill amount"+billPayment.getBillBmount());
		
		Optional<UserAccountDetails> User=rDao.findById(opt.get().getUserId());

		Optional<Wallet> wallet = wDao.findById(User.get().getWallet().getWalletId());
//		
//		if(!wallet.isPresent()) {
//			throw new BillPaymentException("Wallet doesn't exist by Id"+billPayment.getWallet().getWalletId());
//		}
//		
		Wallet wal = wallet.get();
		
		if (wal.getBalance() ==null) {
			throw new BillPaymentException("Insufficent balance in wallet");
		}

		if (wal.getBalance() < billPayment.getBillBmount()) {
			throw new BillPaymentException("Insufficent balance in wallet");
		}

		wal.setBalance(wal.getBalance() - billPayment.getBillBmount());

		wDao.save(wal);

		Transaction tr = new Transaction();
		tr.setTransationType(billPayment.getBillType());
		tr.setTransactionAmount(billPayment.getBillBmount());

		Optional<CurrentSession> opt2 = curDao.findById(uniqId);

		Optional<UserAccountDetails> user = rDao.findById(opt2.get().getUserId());

		UserAccountDetails uad = user.get();

		uad.getTransactions().add(tr);

		rDao.save(uad);
		
		ViewBill vBill=new ViewBill();
		vBill.setBillId(billPayment.getBillId());
		vBill.setBillType(billPayment.getBillType());
		vBill.setBillAmount(billPayment.getBillBmount());
		vBill.setWalletId(wal.getWalletId());

		 System.out.println("billvjhvjv"+billPayment);
		billPayment.getWallet().setWalletId(wal.getWalletId());
		
		 bDao.save(billPayment);
		 
		 return vBill;
	}

	@Override
	public List<ViewBill> viewBillPayment(String uniqId) throws BillPaymentException {

		Optional<CurrentSession> opt1 = curDao.findById(uniqId);

		if (!opt1.isPresent()) {
			throw new BillPaymentException("first you have to login..");
		}

		Optional<UserAccountDetails> user = rDao.findById(opt1.get().getUserId());

		Optional<Wallet> opt = wDao.findById(user.get().getWallet().getWalletId());

		List<BillPayment> totalbill = bDao.findAll();

//		System.out.println(totalbill);
		List<ViewBill> viewBill = new ArrayList<>();
		

		for (BillPayment bill : totalbill) {
			
			if (bill.getWallet().getWalletId() == opt.get().getWalletId()) {
			
				ViewBill vBill = new ViewBill();
				vBill.setBillId(bill.getBillId());
				vBill.setBillAmount(bill.getBillBmount());
				vBill.setBillType(bill.getBillType());
				vBill.setWalletId(bill.getWallet().getWalletId());
				
//				System.out.println(bill);
//				bipy.getWallet().setWalletId(bill.getWallet().getWalletId());
				
				viewBill.add(vBill);
			}

		}

		return viewBill;
	}

}
