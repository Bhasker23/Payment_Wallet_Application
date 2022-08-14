package com.masai.models;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillPayment {
  
	@Id
	private Integer billId;
	private Wallet wallet;
	private Double billBmount;
	private String billType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccountDetails user;
}
