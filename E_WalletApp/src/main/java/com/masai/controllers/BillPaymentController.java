package com.masai.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.BillPayment;
import com.masai.models.ViewBill;
import com.masai.services.BillPaymentIntr;

@RestController
@RequestMapping("/billPayment")
public class BillPaymentController {

	@Autowired
	private BillPaymentIntr billService;
	
	@PostMapping("/bill/{uniqId}")
	public ResponseEntity<BillPayment> payBill(@PathVariable("uniqId") String uniqId, @RequestBody BillPayment billPayment) {
		
		BillPayment bill= billService.addBillPayment(billPayment, uniqId);
		
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}
	
	@GetMapping("/viewBill/{uniqId}")
	public ResponseEntity<List<ViewBill>> viewBillPayment(@PathVariable("uniqId") String uniqId){
		
		List<ViewBill > billPayment = billService.viewBillPayment(uniqId);
		
		return new ResponseEntity<List<ViewBill>>(billPayment, HttpStatus.OK);
		
	}
}
	


