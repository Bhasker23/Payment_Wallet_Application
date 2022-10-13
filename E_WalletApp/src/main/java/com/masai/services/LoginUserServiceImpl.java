package com.masai.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.UserNotFindException;
import com.masai.models.Customer;
import com.masai.payloads.Credentials;
import com.masai.payloads.CurrentSession;
import com.masai.payloads.Login;
import com.masai.payloads.PasswordGenerator;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.SaveCustomerDAL;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginUserServiceImpl implements LoginUserServicIntr {

	@Autowired
	private SaveCustomerDAL customerDB;

	@Autowired
	private CurrentSessionDAL currentUserDB;
	
	@Autowired
	private PasswordGenerator passGenerater;

	@Override
	public String userLogin(Login logincred) {
		
		Optional<Customer> opt = customerDB.findById(logincred.getUserid());
		
		if (opt.isEmpty()) {
			throw new UserNotFindException("Please signUp First..");
		}

		String p1 = passGenerater.getPass(new Credentials(logincred.getUserid(),logincred.getPassword()));
		String p2 = opt.get().getPassword();

		if (!p1.equals(p2)) {
			throw new UserNotFindException("Password is incorrect");
		}

		CurrentSession currentSession = new CurrentSession();

		currentSession.setName(opt.get().getName());
		currentSession.setUserId(opt.get().getPhone());
  
		UUID uuid = UUID.randomUUID();
		
		currentSession.setUniqueid(uuid.toString().replace("-", ""));

		currentUserDB.save(currentSession);

		return uuid.toString().replace("-", "");

	}

	@Override
	public String logOutUser(String uniqueId) {

		String name = (currentUserDB.findById(uniqueId).get()).getName();

		currentUserDB.deleteById(uniqueId);

		return name + " has been loged out.";

	}

}
