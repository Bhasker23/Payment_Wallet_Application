package com.masai.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Transaction {

	@Id
	private Integer transactionId;
	@Size(min = 3)
	private String transationType;
	private LocalDateTime localDateTime;
	@Min(value = 0)
	private Double transactionAmount;
	@Size(min = 10)
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private UserAccountDetails user;

	public Transaction() {
		this.localDateTime = localDateTime.now();
	}

}
