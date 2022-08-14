package com.masai.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillPayment {
  
	@Id
	private Integer billId;
	
	@OneToOne
	private Wallet wallet;
	private Double billBmount;
	private String billType;
	
	
	
}
