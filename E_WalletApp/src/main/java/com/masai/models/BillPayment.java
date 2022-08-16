package com.masai.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillPayment {
  
	@Id
	private Integer billId;
	
	@OneToOne
	private Wallet wallet;
	private Double billBmount;
	private String billType;

	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccountDetails user;

}
