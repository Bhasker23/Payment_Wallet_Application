package com.masai.models;

import javax.persistence.Id;

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

}
