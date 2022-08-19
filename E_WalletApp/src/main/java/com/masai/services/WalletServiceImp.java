package com.masai.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.masai.exceptions.CustomerDoesNotExist;
import com.masai.exceptions.InsufficientBalance;
import com.masai.models.BankAccount;
import com.masai.models.BeneficiaryDetails;
import com.masai.models.CoustomerWithWallet;
import com.masai.models.Customer;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.userInput.Credentials;
import com.masai.userInput.CurrentSession;
import com.masai.userInput.PasswordGenerator;

@Service
@RequestMapping("/wallet")
public class WalletServiceImp implements WalletServiceIntr {

	@Autowired
	private TransactionServiceImpl trasactionCurd;
	
	@Autowired
	private PasswordGenerator passGenerater;

	Random random = new Random();

	@Override
	public CoustomerWithWallet showBalanceInWallet(RegisterUserDAL regDao, CurrentSessionDAL csDal, String unqId) {

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
	public Transaction fundTransferFromOneWalletToOtherWallet(String targetMobileNumber, Double amount,
			RegisterUserDAL regDao, CurrentSessionDAL csDal, String unqId) {

		CurrentSession csc = csDal.getById(unqId);

		if (csc == null) {

			throw new CustomerDoesNotExist("you are not sign in");
		}

		UserAccountDetails sourceUser = regDao.getById(csc.getUserId());

		Set<BeneficiaryDetails> bdSet = sourceUser.getBeneficiaryDetails();

		boolean f = true;
		for (BeneficiaryDetails bn : bdSet) {

			if (bn.getPhoneNumber().equals(targetMobileNumber)) {
				f = false;
				break;
			}
		}

		if (f) {

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

		Transaction tr = new Transaction();

		tr.setTransactionId(Math.abs(random.nextInt() + 8769797));

		tr.setTransactionAmount(amount);

		tr.setTransationType("Money Transfer");
		tr.setDescription("Rs " + amount + " has been send to " + targetMobileNumber);
		tr.setUser(sourceUser);
		// findUser.getTransactions().add(tr);
		trasactionCurd.addTransactionService(tr);

		regDao.save(sourceUser);

		regDao.save(targetUser);

		return tr;
	}

	@Override
	public Transaction depostAmountFromWalletToBankAccount(double amount, RegisterUserDAL regDao,
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

		tr.setTransactionId(Math.abs(random.nextInt() + 8769797));

		tr.setTransactionAmount(amount);

		tr.setTransationType("Wallet to Bank");
		tr.setDescription("Rs " + amount + " has been added to your Bank");
		tr.setUser(user);

		// findUser.getTransactions().add(tr);
		trasactionCurd.addTransactionService(tr);

		regDao.save(user);

		return tr;
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
		
		String password = passGenerater.getPass(new Credentials(user.getCustomer().getName(),user.getCustomer().getPassword()));

		user.getCustomer().setPassword(password);

		regDao.save(user);

		csc.setUserId(cus.getPhone());

		csDal.save(csc);

		return user.getCustomer();
	}

	@Override
	public Transaction addMoneyIntoWalletFromBankAccount(double amount, RegisterUserDAL regDao, CurrentSessionDAL csDal,
			String unqId) {

		CurrentSession csc = csDal.getById(unqId);

		if (csc == null) {

			throw new CustomerDoesNotExist("you are not sign in");
		}

		Optional<UserAccountDetails> opt = regDao.findById(csc.getUserId());

		UserAccountDetails findUser = opt.get();

		Set<BankAccount> bks = findUser.getBankAccounts();

		if (bks.size() == 0) {

			throw new CustomerDoesNotExist("Bank account not added");
		}

		boolean b2 = true;

		for (BankAccount bk : bks) {

			if (bk.getAccountNumber() != null) {

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

		tr.setTransactionId(Math.abs(random.nextInt() + 8769797));

		tr.setTransactionAmount(amount);


		tr.setTransationType("Money Added to Wallet");

		tr.setDescription("Rs " + amount + " has been added to your wallet");
		tr.setUser(findUser);

	    trasactionCurd.addTransactionService(tr);
		regDao.save(findUser);

		return tr;
	}

}
