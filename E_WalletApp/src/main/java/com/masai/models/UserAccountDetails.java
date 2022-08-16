package com.masai.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class UserAccountDetails {

	@Id
	@JoinColumn(name = "uerId")
	private String id;

	@OneToOne(cascade = CascadeType.ALL)
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
