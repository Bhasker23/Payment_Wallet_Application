package com.masai.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class BankAccount {

	@Id
	//@Pattern(regexp = "[0-9]{9,18}",message = "Account Number")
	private Integer accountNumber;
	@Size(min = 6,max = 12)
	private String ifscCode;
	@Size(min = 3,max = 20)
	private String bankName;
	@Min(value = 0)
	private Double balance;
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JsonIgnore
	private UserAccountDetails user;

}
