package com.masai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoustomerWithWallet {

	private String name;
	private String phoneNumber;
	private Wallet wallet;
}
