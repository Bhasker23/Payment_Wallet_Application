package com.masai.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerDoesNotExist;
import com.masai.exceptions.InsufficientBalance;
import com.masai.models.BankAccount;
import com.masai.models.CoustomerWithWallet;
import com.masai.models.Customer;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.models.Wallet;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.servicesIntr.WalletServiceIntr;

@Service

public class WalletServiceImp implements WalletServiceIntr {

	@Override
	public CoustomerWithWallet showBalanceByPhoneNumber(String phoneNumber, RegisterUserDAL regDao) {

		System.out.println(regDao);

		Optional<UserAccountDetails> userAccountDeatil = regDao.findById(phoneNumber);

		if (userAccountDeatil.isPresent()) {

			UserAccountDetails userActDel = userAccountDeatil.get();

			CoustomerWithWallet cust = new CoustomerWithWallet();

			cust.setName(userActDel.getCustomer().getName());

			cust.setPhoneNumber(userActDel.getCustomer().getPhone());

			cust.setWallet(userActDel.getWallet());

			return cust;

		}
		throw new CustomerDoesNotExist("Customer does not Exist");

	}

	@Override
	public Wallet fundTransfer(String sourceMobileNumber, String targetMobileNumber, Double amount,
			RegisterUserDAL regDao) {

		UserAccountDetails sourceUser = regDao.getById(sourceMobileNumber);

		if (sourceUser == null) {

			throw new CustomerDoesNotExist("source user does not exist");
		}

		UserAccountDetails targetUser = regDao.getById(targetMobileNumber);

		if (targetUser == null) {

			throw new CustomerDoesNotExist("target user does not exist");
		}

		if (sourceUser.getWallet().getBalance() < amount) {

			throw new InsufficientBalance("Insufficient Balance");
		}

		sourceUser.getWallet().setBalance(sourceUser.getWallet().getBalance() - amount);

		targetUser.getWallet().setBalance(targetUser.getWallet().getBalance() + amount);

		Transaction st = new Transaction();

		st.setTransationType("Amount Transfer");

		st.setTransactionAmount(amount);

		sourceUser.getTransactions().add(st);

		regDao.save(sourceUser);

		Transaction dt = new Transaction();

		dt.setTransationType("Amount Recive");

		dt.setTransactionAmount(amount);

		targetUser.getTransactions().add(dt);

		regDao.save(targetUser);

		return targetUser.getWallet();
	}

	@Override
	public CoustomerWithWallet depostAmount(String phoneNumber, double amount, RegisterUserDAL regDao) {

		UserAccountDetails user = regDao.getById(phoneNumber);

		if (user == null) {
			throw new CustomerDoesNotExist("User Account does not exist");
		}
		if (user.getBankAccounts().isEmpty()) {

			throw new CustomerDoesNotExist("Bank Account not added first add the bank account");
		}

		if (user.getWallet().getBalance() < amount) {

			throw new InsufficientBalance("In your accounts Insufficient Balance");
		}

		user.getWallet().setBalance(user.getWallet().getBalance() - amount);

		Set<BankAccount> bkSet = user.getBankAccounts();

		for (BankAccount bk : bkSet) {

			bk.setBalance(bk.getBalance() + amount);

			break;

		}

		Transaction tr = new Transaction();

		tr.setTransactionAmount(amount);

		tr.setTransationType("wallet to Bank account transfer");

		user.getTransactions().add(tr);

		regDao.save(user);

		CoustomerWithWallet cww = new CoustomerWithWallet();

		cww.setName(user.getCustomer().getName());

		cww.setPhoneNumber(user.getCustomer().getPhone());

		cww.setWallet(user.getWallet());

		return cww;
	}

	@Override
	public List<Customer> getAllCustomer(SaveCustomerDAL cusDep) {

		return cusDep.findAll();
	}

	@Override
	public Customer updateCust(Customer cus, RegisterUserDAL regDao) {

		UserAccountDetails user = regDao.getById(cus.getPhone());

		if (user == null) {
			throw new CustomerDoesNotExist("customer does not exist");
		}

		user.getCustomer().setName(cus.getName());

		user.getCustomer().setPassword(cus.getPassword());

		regDao.save(user);

		return user.getCustomer();
	}

	@Override
	public CoustomerWithWallet addMoneyIntoWallet(Wallet wal, double amount, RegisterUserDAL regDao) {
	
		List<UserAccountDetails> users=regDao.findAll();
		
		boolean f=true;
		
		UserAccountDetails findUser = null;
		
		for(UserAccountDetails user:users) {
			
			if(user.getWallet().getWalletId()==wal.getWalletId()) {
				
				findUser=user;
				
				f=false;
				
				break;
			}
		}
		
		
		if(f) {
			
			throw new CustomerDoesNotExist("customer doses not exist");
		}
		
		Set<BankAccount>bks=findUser.getBankAccounts();
		
		if(bks.isEmpty()) {
			
			throw new CustomerDoesNotExist("Bank account not added");
		}
		
		boolean b2=true;
		
		for(BankAccount bk:bks) {
			
			if(bk.getBalance()>amount) {
				
				bk.setBalance(bk.getBalance()-amount);
				
				b2=false;
				
				break;
			}
		}
		
		if(b2) {
			throw new InsufficientBalance("InsufficientBalance");
		}
		
		findUser.getWallet().setBalance(findUser.getWallet().getBalance()+amount);
		
		Transaction tr =new Transaction();
		
		tr.setTransactionAmount(amount);
		
		tr.setTransationType("bank to wallet money transfer");
		
		findUser.getTransactions().add(tr);
		
		regDao.save(findUser);
		
		CoustomerWithWallet cus = new CoustomerWithWallet();
		
		cus.setName(findUser.getCustomer().getName());
		
		cus.setPhoneNumber(findUser.getCustomer().getPhone());
		
		cus.setWallet(findUser.getWallet());
		
		return cus;
	}

}
