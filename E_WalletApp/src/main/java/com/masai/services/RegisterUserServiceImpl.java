package com.masai.services;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.UserAlreadyExistException;
import com.masai.models.BankAccount;
import com.masai.models.BeneficiaryDetails;
import com.masai.models.Customer;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.models.Wallet;
import com.masai.payloads.Credentials;
import com.masai.payloads.PasswordGenerator;
import com.masai.payloads.UserDto;
import com.masai.repositories.RegisterUserDAL;

@Service
public class RegisterUserServiceImpl implements RegisterUserServiceIntr {

	@Autowired
	private RegisterUserDAL userDB;

	@Autowired
	private PasswordGenerator passGenerater;
	
	@Override
	@Transactional
	public UserAccountDetails registerUser(UserDto userDto) {

		if ((userDB.findById(userDto.getPhone())).isPresent()) {
			throw new UserAlreadyExistException("You are already SignedUp please Login.");
		}
        
		ModelMapper modelMapper = new ModelMapper();
		
		Customer customer = modelMapper.map(userDto, Customer.class);
		customer.setPassword(passGenerater.getPass(new Credentials(customer.getPhone(), customer.getPassword())));
		
		UserAccountDetails userAccountDetails = new UserAccountDetails();
		
		userAccountDetails.setCustomer(customer);
		userAccountDetails.setWallet(new Wallet());
		userAccountDetails.setBankAccounts(new HashSet<BankAccount>());
		userAccountDetails.setBeneficiaryDetails(new HashSet<BeneficiaryDetails>());
		userAccountDetails.setTransactions(new HashSet<Transaction>());
		
	    customer.setUser(userAccountDetails);
	    userAccountDetails.getWallet().setUser(userAccountDetails);
	    userAccountDetails.setId(userDto.getPhone());
	    
	    return userDB.save(userAccountDetails);
	}
}