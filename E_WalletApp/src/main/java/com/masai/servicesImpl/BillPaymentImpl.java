package com.masai.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BillPaymentException;
import com.masai.models.BillPayment;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.models.Wallet;
import com.masai.repositories.BillPaymentDAO;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveWalletDAL;
import com.masai.servicesIntr.BillPaymentIntr;

@Service
public class BillPaymentImpl implements BillPaymentIntr{

	@Autowired
	private BillPaymentDAO bDao;
	
	@Autowired
	private RegisterUserDAL rDao;
	
	@Autowired
	private SaveWalletDAL wDao;
	
	
	
	@Override
	public BillPayment addBillPayment(BillPayment billPayment) {

		Optional<Wallet> wallet= wDao.findById(billPayment.getWallet().getWalletId());
		
		if(!wallet.isPresent()) {
			throw new BillPaymentException("Wallet doesn't exist by Id"+billPayment.getWallet().getWalletId());
		}
		
		Wallet wal= wallet.get();
		
		if(wal.getBalance()<billPayment.getBillBmount()) {
			throw new BillPaymentException("Insufficent balance in wallet");
		}
		
		 wal.setBalance(wal.getBalance()-billPayment.getBillBmount());
		
		 wDao.save(wal);
		 
		 Transaction tr= new Transaction();
		 tr.setTransationType(billPayment.getBillType());
		 tr.setTransactionAmount(billPayment.getBillBmount());
		 
		 List<UserAccountDetails> user= rDao.findAll();
		 
		 for(UserAccountDetails uas: user) {
			 if(uas.getWallet().getWalletId()==billPayment.getWallet().getWalletId()) {
				 uas.getTransactions().add(tr);
				 break;
			 }
		 }
		

		return bDao.save(billPayment);
	}

	@Override
	public List<BillPayment> viewBillPayment(Integer walletId) throws BillPaymentException {
		
		Optional<Wallet> opt= wDao.findById(walletId);
		
		List<BillPayment> totalbill= bDao.findAll();
		
		System.out.println(totalbill);
		List<BillPayment> viewBill = new ArrayList<>();
		
		if(opt.isPresent()) {
			for(BillPayment bill: totalbill) {
				if(bill.getWallet()!=null) {
					if(bill.getWallet().getWalletId()==walletId) {
						viewBill.add(bill);
					}
				}
			}
		}
		else {
			throw new BillPaymentException("Bill not found");
		}
		
		return viewBill;
	}

}
