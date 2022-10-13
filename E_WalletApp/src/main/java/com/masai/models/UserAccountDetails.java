package com.masai.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
@Table(name = "User")
public class UserAccountDetails {

	@Id
	@JsonProperty(access = Access.WRITE_ONLY)
	private String id;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Customer customer;


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Transaction> transactions; 

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)	
	private Set<BankAccount> bankAccounts;


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<BeneficiaryDetails> beneficiaryDetails;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Wallet wallet;
	
    
}
