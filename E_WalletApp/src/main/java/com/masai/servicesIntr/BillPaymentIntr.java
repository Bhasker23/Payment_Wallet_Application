package com.masai.servicesIntr;

import java.util.List;

import com.masai.exceptions.BillPaymentException;
import com.masai.models.BillPayment;
import com.masai.models.Wallet;

public interface BillPaymentIntr {

	public BillPayment addBillPayment(BillPayment billPayment);
	
	public List<BillPayment> viewBillPayment(Integer walletId) throws BillPaymentException;
	
}
