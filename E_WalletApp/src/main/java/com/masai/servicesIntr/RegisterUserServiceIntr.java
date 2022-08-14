package com.masai.servicesIntr;

import com.masai.models.Customer;
import com.masai.models.UserAccountDetails;
import com.masai.userInput.UserInput;

public interface RegisterUserServiceIntr {
	
	public UserAccountDetails registerUser(UserInput input);

}
