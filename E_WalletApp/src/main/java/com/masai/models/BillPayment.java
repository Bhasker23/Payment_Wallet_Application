package com.masai.models;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
=======
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42

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
	
<<<<<<< HEAD
	
	
=======
	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccountDetails user;
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
}
