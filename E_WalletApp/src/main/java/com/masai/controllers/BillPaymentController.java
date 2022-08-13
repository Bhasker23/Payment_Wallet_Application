package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.BillPayment;

@RestController
public class BillPaymentController {

//	@Autowired
//	private BillPaymentIntr billService;
//	
//	@PostMapping("/bill")
//	public ResponseEntity<BillPayment> saveBillPayment(@RequestBody BillPayment billPayment) {
//		
//		BillPayment bill= billService.addBillPayment(billPayment);
//		
//		return new ResponseEntity<BillPayment>(bill, HttpStatus.OK);
//	}
	
}
