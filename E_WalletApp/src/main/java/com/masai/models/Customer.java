package com.masai.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
   
   //@Pattern(regexp = "[6789]{1}[0-9]{9}",message = "Invalid Phone Number")
	@Id
	private String phone;
	private String name;
	private String password;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	public UserAccountDetails user;
	
	
}
