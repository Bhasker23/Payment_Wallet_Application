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
<<<<<<< HEAD

=======
	
	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccountDetails user;
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
}
