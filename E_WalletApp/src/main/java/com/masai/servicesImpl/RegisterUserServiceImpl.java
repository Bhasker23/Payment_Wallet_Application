package com.masai.servicesImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	

	@Override
	public UserAccountDetails registerUser(Customer customer) {
		
		UserAccountDetails user = new UserAccountDetails();
		
		user.setId(customer.getPhone());
	    user.setCustomer(customer);
	    
		Wallet wallet = new Wallet();
		wallet.setBalance(108.1);
		wallet.setWalletId(1);
		user.setWallet(wallet);
		
		Set<BankAccount> bankAccounts = new HashSet<>();
		BankAccount bankAcc =new BankAccount(787845672, "EQIT23423", "Eqitas Finance Bank", 345680.3);
		bankAccounts.add(bankAcc);
		user.setBankAccounts(bankAccounts);//user will add their bank account after wallet account creation
	    
		Set<BeneficiaryDetails> beneficiaryDetails = new HashSet<>();
	    //BeneficiaryDetails beneficiaryDetail = new BeneficiaryDetails("7492004935", "Satym Kumar Jha");
	    BeneficiaryDetails beneficiaryDetail2 = new BeneficiaryDetails("8208038245", "Laxmi Didi");
		//beneficiaryDetails.add(beneficiaryDetail2);
		beneficiaryDetails.add(beneficiaryDetail2);
		user.setBeneficiaryDetails(beneficiaryDetails);;//user will add their beneficiary after wallet account creation
		
		Set<Transaction> transactions = new HashSet<>();
		Transaction transaction = new Transaction();
		
		transaction.setTransactionId(7874474);
		transaction.setTransationType("Charus and ganja");
		transaction.setTransactionAmount(3456.7);
		transaction.setDescription("Sauk badee");
		
		transactions.add(transaction);
		
		user.setTransactions(transactions);//user will add their transactions after wallet account creation
		 
		customerDB.save(customer);
		walletDB.save(wallet);
		transactionDB.save(transaction);
		bankaccDB.save(bankAcc);
		beneficiaryDB.save(beneficiaryDetail2);
	
		return userDB.save(user);
	}

}
