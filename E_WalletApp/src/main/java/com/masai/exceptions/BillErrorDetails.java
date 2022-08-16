package com.masai.exceptions;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillErrorDetails {

	private LocalDate timestamp;
	private String message;
	private String details;
	
}
