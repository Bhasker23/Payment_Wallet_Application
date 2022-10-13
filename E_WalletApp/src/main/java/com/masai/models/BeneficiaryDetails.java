package com.masai.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Beneficiaries")
public class BeneficiaryDetails {

	@Id
	@Pattern(regexp = "^[6-9][0-9]{9}", message = "Invaild Phone Number")
	private String phoneNumber;
	@Size(min = 3, max = 20)
	private String name;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnore
	private UserAccountDetails user;

}
