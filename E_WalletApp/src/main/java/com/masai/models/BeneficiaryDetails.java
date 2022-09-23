package com.masai.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class BeneficiaryDetails {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer id;
	@Id
	@Pattern(regexp = "^[6-9][0-9]{9}", message = "Invaild Phone Number")
	private String phoneNumber;
	@Size(min = 3, max = 20)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private UserAccountDetails user;

}
