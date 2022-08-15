package com.masai.servicesIntr;


import com.masai.LoginSignUp.Login;




public interface LoginUserServicIntr {

	public String userLogin(Login logincred);

	
	public String logOutUser(String uniqueId);

	

}
