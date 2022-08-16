package com.masai.services;

import com.masai.models.UserAccountDetails;
import com.masai.userInput.UserInput;

public interface RegisterUserServiceIntr {

	public UserAccountDetails registerUser(UserInput input);

}
