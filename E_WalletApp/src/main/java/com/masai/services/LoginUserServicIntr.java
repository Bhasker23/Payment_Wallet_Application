package com.masai.services;


import com.masai.payloads.Login;




public interface LoginUserServicIntr {

	public String userLogin(Login logincred);

	
	public String logOutUser(String uniqueId);

	

}
