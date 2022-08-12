package com.masai.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Transaction {
  
	@Id
	private Integer transactionId;
	private String transationType;
	private LocalDateTime localDateTime;
	private Double transactionAmount;
	private String description;
	
	public Transaction() {
		this.localDateTime=localDateTime.now();
	}
	
	
	
}
