package com.masai.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.UserNotFindException;
import com.masai.models.Customer;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.servicesIntr.LoginUserServicIntr;

@Service
public class LoginUserServiceImpl implements LoginUserServicIntr {

	@Autowired
	private SaveCustomerDAL customerdb;
	
	@Autowired
	private RegisterUserDAL userdb;

	
	
	@Override
	public UserAccountDetails userLogin(Customer customer) {
		
		if ((userdb.findById(customer.getPhone())).isEmpty()) {
			
			throw new UserNotFindException("Please SignUp first..");
		}
		
//		System.out.println(userdb.findById(customer.getPhone()).get());
		
		return (userdb.findById(customer.getPhone())).get();
	}

	
}
