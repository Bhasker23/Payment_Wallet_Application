package com.masai.servicesIntr;

import com.masai.models.Customer;
import com.masai.models.UserAccountDetails;

public interface RegisterUserServiceIntr {
	
	public UserAccountDetails registerUser(Customer customer);

}
