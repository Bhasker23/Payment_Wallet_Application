package com.masai.services;


import com.masai.userInput.Login;




public interface LoginUserServicIntr {

	public String userLogin(Login logincred);

	
	public String logOutUser(String uniqueId);

	

}
