package com.masai.models;

import javax.persistence.Id;

<<<<<<< HEAD
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillPayment {
  
	@Id
	private Integer billId;
	private Wallet wallet;
	private Double billBmount;
	private String billType;
	
	
>>>>>>> 8836cb471e0d17f9750b9dd2ce54b8f8b4334397
	
	
}
