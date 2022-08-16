package com.masai.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.UserAlreadyExistException;
import com.masai.models.BankAccount;
import com.masai.models.BeneficiaryDetails;
import com.masai.models.Customer;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.models.Wallet;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveBankAccDAL;
import com.masai.repositories.SaveBeneficiaryDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.repositories.SaveTransactionDAL;
import com.masai.repositories.SaveWalletDAL;
import com.masai.servicesIntr.RegisterUserServiceIntr;
import com.masai.userInput.Credentials;
import com.masai.userInput.PasswordGenerator;
import com.masai.userInput.UserInput;

@Service
public class RegisterUserServiceImpl implements RegisterUserServiceIntr {

	@Autowired
	private RegisterUserDAL userDB;

	@Autowired
	private SaveCustomerDAL customerDB;

	@Autowired
	private SaveBankAccDAL bankaccDB;

	@Autowired
	private SaveBeneficiaryDAL beneficiaryDB;

	@Autowired
	private SaveTransactionDAL transactionDB;

	@Autowired
	private SaveWalletDAL walletDB;

	@Autowired
	private PasswordGenerator passGenerater;
	
	@Override
	public UserAccountDetails registerUser(UserInput input) {

		if ((userDB.findById(input.getPhone())).isPresent()) {
			throw new UserAlreadyExistException("You are already SignedUp please Login.");
		}

		Customer customer = new Customer();

		customer.setName(input.getName());
		customer.setPhone(input.getPhone());
		customer.setPassword(passGenerater.getPass(new Credentials(input.getPhone(),input.getPassword())));
		
	    BankAccount bankAccount = new BankAccount();
	    
	    BeneficiaryDetails beneficiaryDetails = new BeneficiaryDetails();
	    
	    Transaction transaction = new Transaction();
	    
	    Wallet wallet = new Wallet();
	    
	    UserAccountDetails userAccountDetails = new UserAccountDetails();
	    
	    userAccountDetails.setId(customer.getPhone());
	    userAccountDetails.setCustomer(customer);
	    userAccountDetails.setWallet(wallet);
        
	    wallet.setUser(userAccountDetails);
	    customer.setUser(userAccountDetails);
	    userDB.save(userAccountDetails);
	    
	    userAccountDetails.getCustomer().setPassword(input.getPassword());
	    return userAccountDetails;
	}
}