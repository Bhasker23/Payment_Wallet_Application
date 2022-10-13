package com.masai.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = "Invalid Phone Number")
	private String phone;
	private String name;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JsonIgnore
	public UserAccountDetails user;

}
