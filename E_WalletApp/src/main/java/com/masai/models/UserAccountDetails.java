package com.masai.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDetails {

	@Id
	@JoinColumn(name = "uerId")
	private String id;
<<<<<<< HEAD

	@OneToOne
=======
	
	@OneToOne(cascade = CascadeType.ALL)
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
	@JoinColumn(name = "customerId")
	private Customer customer;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Transaction> transactions;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<BankAccount> bankAccounts;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<BeneficiaryDetails> beneficiaryDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;

}
