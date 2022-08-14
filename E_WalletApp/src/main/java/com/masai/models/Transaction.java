package com.masai.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
<<<<<<< HEAD
=======

import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42

import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transactionId;
	private String transationType;
	private LocalDateTime localDateTime;
	private Double transactionAmount;
	private String description;
<<<<<<< HEAD

	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccountDetails user;

=======
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private UserAccountDetails user;
	
>>>>>>> e5f0c02c5f3b88a56c1b4a41b28995bde9827e42
	public Transaction() {
		this.localDateTime = localDateTime.now();
	}

}
