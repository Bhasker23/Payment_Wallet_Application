package com.masai.services;


import java.util.List;

import com.masai.exceptions.BillPaymentException;
import com.masai.models.BillPayment;
import com.masai.models.ViewBill;
import com.masai.models.Wallet;

public interface BillPaymentIntr {

	public BillPayment addBillPayment(BillPayment billPayment,String uniqId);
	
	public List<ViewBill> viewBillPayment(String uniqId) throws BillPaymentException;
	
}


