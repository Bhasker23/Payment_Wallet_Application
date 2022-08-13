package com.masai.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
	
	@Id
	private Integer accountNumber;
	private String ifscCode;
	private String bankName;
	private Double balance;

}
