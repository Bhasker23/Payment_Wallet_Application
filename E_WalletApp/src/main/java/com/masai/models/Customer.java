package com.masai.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
<<<<<<< HEAD

=======
	
   
   //@Pattern(regexp = "[6789]{1}[0-9]{9}",message = "Invalid Phone Number")
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
	@Id
	private String phone;
	private String name;
	private String password;
<<<<<<< HEAD

=======
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	public UserAccountDetails user;
	
	
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
}
