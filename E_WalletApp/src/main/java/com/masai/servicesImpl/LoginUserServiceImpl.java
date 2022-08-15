package com.masai.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.LoginSignUp.CurrentSession;
import com.masai.LoginSignUp.Login;
import com.masai.exceptions.UserNotFindException;
import com.masai.models.Customer;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.servicesIntr.LoginUserServicIntr;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginUserServiceImpl implements LoginUserServicIntr {

	@Autowired
	private SaveCustomerDAL customerDB;

	@Autowired
	private CurrentSessionDAL currentUserDB;

	@Override
	public String userLogin(Login logincred) {

		Optional<Customer> opt = customerDB.findById(logincred.getUserid());

		if (opt.isEmpty()) {
			throw new UserNotFindException("Please signUp First..");
		}

		if (!((opt.get().getPassword()).equals(logincred.getPassword()))) {

			throw new UserNotFindException("Password is incorrect");
		}

		CurrentSession currentSession = new CurrentSession();

		currentSession.setName(opt.get().getName());
		currentSession.setUserId(opt.get().getPhone());

		
		String uniqueID = RandomString.make(5);
		currentSession.setUniqueid(uniqueID);
		
		currentUserDB.save(currentSession);
		
		return uniqueID;
		
	}


	@Override
	public String logOutUser( String uniqueId) {
		
	 String name = (currentUserDB.findById(uniqueId).get()).getName();
	
	  currentUserDB.deleteById(uniqueId);
		
	  return name + " has been loged out.";
	  
	}


	
	


		String uniqueID = RandomString.make(5);
		currentSession.setUniqueId(uniqueID);

		currentUserDB.save(currentSession);

		return uniqueID;

	}

}
