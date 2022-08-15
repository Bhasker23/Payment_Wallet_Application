package com.masai.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.masai.LoginSignUp.CurrentSession;
import com.masai.exceptions.CustomerDoesNotExist;
import com.masai.exceptions.InsufficientBalance;
import com.masai.models.BankAccount;
import com.masai.models.BeneficiaryDetails;
import com.masai.models.CoustomerWithWallet;
import com.masai.models.Customer;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.models.Wallet;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.servicesIntr.WalletServiceIntr;

@Service

public class WalletServiceImp implements WalletServiceIntr {

	@Override
	public CoustomerWithWallet showBalanceInWallet( RegisterUserDAL regDao,
			CurrentSessionDAL csDal, String unqId) {

		CurrentSession csc = csDal.getById(unqId);

		if (csc == null) {

			throw new CustomerDoesNotExist("you are not sign in");
		}

		Optional<UserAccountDetails> userAccountDeatil = regDao.findById(csc.getUserId());

		UserAccountDetails userActDel = userAccountDeatil.get();

		CoustomerWithWallet cust = new CoustomerWithWallet();

		cust.setName(userActDel.getCustomer().getName());

		cust.setPhoneNumber(userActDel.getCustomer().getPhone());

		cust.setWallet(userActDel.getWallet());

		return cust;

	}

	@Override
	public Wallet fundTransferFromOneWalletToOtherWallet( String targetMobileNumber, Double amount,
			RegisterUserDAL regDao, CurrentSessionDAL csDal, String unqId) {

		CurrentSession csc = csDal.getById(unqId);

		if (csc == null) {

			throw new CustomerDoesNotExist("you are not sign in");
		}

		UserAccountDetails sourceUser = regDao.getById(csc.getUserId());


		Set<BeneficiaryDetails> bdSet=sourceUser.getBeneficiaryDetails();
		
		
		boolean f=true;
		for(BeneficiaryDetails bn:bdSet) {
			
			if(bn.getPhoneNumber().equals(targetMobileNumber)) {
				f=false;
				break;
			}
		}
		
		if(f) {
			
			throw new CustomerDoesNotExist("Target mobile number does not added in Beneficiary list");
		}

		UserAccountDetails targetUser = regDao.getById(targetMobileNumber);


		if (sourceUser.getWallet().getBalance() == null) {

			throw new InsufficientBalance("Insufficient Balance in Wellat");
		}

		if (sourceUser.getWallet().getBalance() < amount) {

			throw new InsufficientBalance("Insufficient Balance in Wellet");
		}

		sourceUser.getWallet().setBalance(sourceUser.getWallet().getBalance() - amount);

		if (targetUser.getWallet().getBalance() == null) {

			targetUser.getWallet().setBalance(amount);
		} else {

			targetUser.getWallet().setBalance(targetUser.getWallet().getBalance() + amount);

		}

		Transaction st = new Transaction();

		st.setTransationType("Amount Transfer from wallet");

		st.setTransactionAmount(amount);

		sourceUser.getTransactions().add(st);

		regDao.save(sourceUser);

		Transaction dt = new Transaction();

		dt.setTransationType("Amount Recive by wallet");

		dt.setTransactionAmount(amount);

		targetUser.getTransactions().add(dt);

		regDao.save(targetUser);

		return targetUser.getWallet();
	}

	@Override
	public CoustomerWithWallet depostAmountFromWalletToBankAccount(double amount, RegisterUserDAL regDao,
			CurrentSessionDAL csDal, String unqId) {

		CurrentSession csc = csDal.getById(unqId);

		if (csc == null) {

			throw new CustomerDoesNotExist("you are not sign in");
		}

		UserAccountDetails user = regDao.getById(csc.getUserId());

		
		if (user.getBankAccounts().size() == 0) {

			throw new CustomerDoesNotExist("Bank Account not added first add the bank account");
		}

		if (user.getWallet().getBalance() == null) {

			throw new InsufficientBalance("In your wallet Insufficient Balance");
		}

		if (user.getWallet().getBalance() < amount) {

			throw new InsufficientBalance("In your wallet Insufficient Balance");
		}

		user.getWallet().setBalance(user.getWallet().getBalance() - amount);

		Set<BankAccount> bkSet = user.getBankAccounts();

		for (BankAccount bk : bkSet) {

			if (bk.getAccountNumber() != null) {

				bk.setBalance(bk.getBalance() + amount);

				break;
			}

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
	public List<Customer> getAllCustomer(SaveCustomerDAL cusDep, CurrentSessionDAL csDal, String unqId) {

		CurrentSession csc = csDal.getById(unqId);

		if (csc == null) {

			throw new CustomerDoesNotExist("you are not sign in");
		}
		
		return cusDep.findAll();
	}

	@Override
	public Customer updateCustomer(Customer cus, RegisterUserDAL regDao, CurrentSessionDAL csDal, String unqId) {

		CurrentSession csc = csDal.getById(unqId);

		if (csc == null) {

			throw new CustomerDoesNotExist("you are not sign in");
		}
		
		UserAccountDetails user = regDao.getById(csc.getUserId());
		
		user.getCustomer().setName(cus.getName());

		user.getCustomer().setPassword(cus.getPassword());
		
		regDao.save(user);
		
		csc.setUserId(cus.getPhone());
		
		csDal.save(csc);

		return user.getCustomer();
	}

	@Override
	public CoustomerWithWallet addMoneyIntoWalletFromBankAccount(double amount, RegisterUserDAL regDao,
			CurrentSessionDAL csDal, String unqId) {

		CurrentSession csc = csDal.getById(unqId);

		if (csc == null) {

			throw new CustomerDoesNotExist("you are not sign in");
		}
		

		Optional<UserAccountDetails> opt = regDao.findById(csc.getUserId());
		
		UserAccountDetails findUser=opt.get();

		Set<BankAccount> bks = findUser.getBankAccounts();

		if (bks.size()==0) {

			throw new CustomerDoesNotExist("Bank account not added");
		}

		boolean b2 = true;

		for (BankAccount bk : bks) {

			if(bk.getAccountNumber()!=null) {
				
				if (bk.getBalance() >= amount) {

					bk.setBalance(bk.getBalance() - amount);

					b2 = false;

					break;
				}
				
			}
			
		}

		if (b2) {
			throw new InsufficientBalance("Insufficient Balance in Bank Account");
		}

		if (findUser.getWallet().getBalance() == null) {

			findUser.getWallet().setBalance(amount);
		} else {

			findUser.getWallet().setBalance(findUser.getWallet().getBalance() + amount);
		}

		Transaction tr = new Transaction();

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
