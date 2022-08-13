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
		
		if((userDB.findById(customer.getPhone())).isPresent()) {
			throw new UserAlreadyExistException("You are already SignedUp please Login.");
			}
		
		
		UserAccountDetails user = new UserAccountDetails();
		
		
		user.setId(customer.getPhone());
	    user.setCustomer(customer);
	    
		Wallet wallet = new Wallet();
<<<<<<< HEAD
		wallet.setBalance(108.1);
//		wallet.setWalletId(1);
		user.setWallet(wallet);
		
		Set<BankAccount> bankAccounts = new HashSet<>();
		BankAccount bankAcc =new BankAccount(657845672, "EQIT23423", "Eqitas Finance Bank", 345680.3);
		bankAccounts.add(bankAcc);
		user.setBankAccounts(bankAccounts);//user will add their bank account after wallet account creation
	    
		Set<BeneficiaryDetails> beneficiaryDetails = new HashSet<>();
	    //BeneficiaryDetails beneficiaryDetail = new BeneficiaryDetails("7492004935", "Satym Kumar Jha");
	    BeneficiaryDetails beneficiaryDetail2 = new BeneficiaryDetails("8263039245", "Laxmi Didi");
		//beneficiaryDetails.add(beneficiaryDetail2);
		beneficiaryDetails.add(beneficiaryDetail2);
		user.setBeneficiaryDetails(beneficiaryDetails);;//user will add their beneficiary after wallet account creation
		
		Set<Transaction> transactions = new HashSet<>();
=======
		user.setWallet(wallet);
		
>>>>>>> 99813231d59360cc287e1d52e841e5773544b846
		Transaction transaction = new Transaction();
		BankAccount bankAcc = new BankAccount();
	    BeneficiaryDetails beniBeneficiaryDetail = new BeneficiaryDetails();
		
<<<<<<< HEAD
		transaction.setTransactionId(7774474);
		transaction.setTransationType("Charus and ganja");
		transaction.setTransactionAmount(3456.7);
		transaction.setDescription("Sauk badee cheej hai... Tum kiya jano raesh babu...Kabhee haveli pe aao tab batayenge...Samjhe kee Nahee...?");
		
		transactions.add(transaction);
		
		user.setTransactions(transactions);//user will add their transactions after wallet account creation
		 
=======
>>>>>>> 99813231d59360cc287e1d52e841e5773544b846
		customerDB.save(customer);
		walletDB.save(wallet);
		transactionDB.save(transaction);
		bankaccDB.save(bankAcc);
		beneficiaryDB.save(beniBeneficiaryDetail);
		return userDB.save(user);
	}
	//this method will be add in Add bank account serviceIMPL
//	public UserAccountDetails addBankAccount(BankAccount bankAccount) {
//		
//		UserAccountDetails user = (userDB.findById("67890")).get();
//		
//		user.getBankAccounts().add(bankAccount);
//		
//		userDB.save(user);
//		
//		return (userDB.findById("67890")).get();
//		
//	}

}
