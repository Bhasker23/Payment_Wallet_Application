package com.masai.services;

import com.masai.models.UserAccountDetails;
import com.masai.payloads.UserDto;

public interface RegisterUserServiceIntr {

	public UserAccountDetails registerUser(UserDto input);

}
