package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.BillPayment;
import com.masai.models.Transaction;
import com.masai.servicesImpl.BillPaymentImpl;

@RestController
@RequestMapping("/billPayment")
public class BillPaymentController {

	@Autowired
	private BillPaymentImpl billImpl;

	@PostMapping("/paybill/{uid}")
	public ResponseEntity<Transaction> payBill(@RequestBody BillPayment billPayment, @PathVariable String uid) {

		return new ResponseEntity<>(billImpl.payBill(billPayment, uid), HttpStatus.OK);
	
	}

}
