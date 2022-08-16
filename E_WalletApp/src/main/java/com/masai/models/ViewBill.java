package com.masai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewBill {

	private Integer billId;
	private double BillAmount;
	private String BillType;
	private Integer WalletId;
}
