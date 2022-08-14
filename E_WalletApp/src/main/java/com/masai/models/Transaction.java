package com.masai.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccountDetails user;

	public Transaction() {
		this.localDateTime = localDateTime.now();
	}

}
