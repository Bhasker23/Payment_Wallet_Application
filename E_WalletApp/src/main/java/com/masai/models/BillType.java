package com.masai.models;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillType {

	Map<String, Integer> bill_amount = new HashMap<>();
	 
	 String[] BillType = {"Internet","LPG gas","Water","Electricity"};
	 int[] amount = {249,1100,2500,3000};
	 
	public void differentTypes(Double total) {
		
		for(int i=0; i<BillType.length; i++) {
			if(total<amount[i]) {
				total-= amount[i];
				bill_amount.put(BillType[i], amount[i]);
			}
		}

	}
	
}
