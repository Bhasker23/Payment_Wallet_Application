package com.masai.servicesIntr;

import com.masai.models.BillPayment;
import com.masai.models.Transaction;

public interface BillPaymentIntr {
	public Transaction payBill(BillPayment bill, String uid);

}
