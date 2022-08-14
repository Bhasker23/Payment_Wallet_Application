package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.BillPayment;
import com.masai.models.Wallet;
import com.masai.servicesIntr.BillPaymentIntr;

@RestController
public class BillPaymentController {

	@Autowired
	private BillPaymentIntr billService;
	
	@PostMapping("/bill")
	public ResponseEntity<BillPayment> saveBillPayment(@RequestBody BillPayment billPayment) {
		
		BillPayment bill= billService.addBillPayment(billPayment);
		
		return new ResponseEntity<BillPayment>(bill, HttpStatus.OK);
	}
	
	@GetMapping("/viewBill/{walletId}")
	public ResponseEntity<List<BillPayment>> viewBillPayment(@PathVariable Integer walletId){
		
		List<BillPayment> billPayment = billService.viewBillPayment(walletId);
		
		return new ResponseEntity<List<BillPayment>>(billPayment, HttpStatus.OK);
		
	}
	
	
}
