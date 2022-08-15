package com.masai.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Transaction {

	@Id
	private Integer transactionId;
	private String transationType;
	private LocalDateTime localDateTime;
	private Double transactionAmount;
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private UserAccountDetails user;

	public Transaction() {
		this.localDateTime = localDateTime.now();
	}

}
