package com.masai.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BankAccountNotAdded;
import com.masai.exceptions.BankAccountNotExsists;
import com.masai.exceptions.BeneficiaryNotAdded;
import com.masai.exceptions.BeneficiaryNotExist;
import com.masai.exceptions.BeneficiaryNotFound;
import com.masai.exceptions.InsufficientBalance;
import com.masai.models.BankAccount;
import com.masai.models.BeneficiaryDetails;
import com.masai.models.CoustomerWithWallet;
import com.masai.models.Customer;
import com.masai.models.Transaction;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.BeneficiaryDetailsDao;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.repositories.SaveBankAccDAL;
import com.masai.repositories.SaveCustomerDAL;
import com.masai.userInput.CurrentSession;

@Service

public class WalletServiceImp implements WalletServiceIntr {

	@Autowired
	private TransactionServiceImpl trasactionCurd;

	@Autowired
	private SaveBankAccDAL bankRepo;

	@Autowired
	private RegisterUserDAL regDao;

	@Autowired
	private CurrentSessionDAL csDal;

	@Autowired
	private SaveCustomerDAL cusDep;
	
	@Autowired
	private BeneficiaryDetailsDao benefiREpo;

	Random random = new Random();

	@Override
	public CoustomerWithWallet showBalanceInWallet(String unqId) {

		Optional<CurrentSession> opt = csDal.findById(unqId);

		if (!opt.isPresent()) {
			throw new BeneficiaryNotFound("you are not login");
		}

		Optional<UserAccountDetails> userAccountDeatil = regDao.findById(opt.get().getUserId());

		UserAccountDetails userActDel = userAccountDeatil.get();

		CoustomerWithWallet cust = new CoustomerWithWallet();

		cust.setName(userActDel.getCustomer().getName());

		cust.setPhoneNumber(userActDel.getCustomer().getPhone());

		cust.setWallet(userActDel.getWallet());

		return cust;

	}

	@Override
	public Transaction fundTransferFromOneWalletToOtherWallet(String targetMobileNumber, Double amount, String unqId) {

		Optional<CurrentSession> opt = csDal.findById(unqId);

		if (!opt.isPresent()) {
			throw new BeneficiaryNotFound("you are not login");
		}
		
		
		Optional<BeneficiaryDetails> benefiOpt=benefiREpo.findById(targetMobileNumber);
		
		if(!benefiOpt.isPresent()) {
			
			throw new BeneficiaryNotExist("Beneficiary Not Exist");
		}

		UserAccountDetails sourceUser = regDao.getById(opt.get().getUserId());

		Set<BeneficiaryDetails> bdSet = sourceUser.getBeneficiaryDetails();
		
		if(!bdSet.contains(benefiOpt.get())) {
			
			throw new BeneficiaryNotAdded("Beneficiary Not Added");
		}
		

		if (sourceUser.getWallet().getBalance() == null) {

			throw new InsufficientBalance("Insufficient Balance in Wellat");
		}

		if (sourceUser.getWallet().getBalance() < amount) {

			throw new InsufficientBalance("Insufficient Balance in Wellet");
		}

		sourceUser.getWallet().setBalance(sourceUser.getWallet().getBalance() - amount);
		
		regDao.save(sourceUser);
		
		UserAccountDetails targetUser = regDao.getById(targetMobileNumber);

		if (targetUser.getWallet().getBalance() == null) {

			targetUser.getWallet().setBalance(amount);
		} else {

			targetUser.getWallet().setBalance(targetUser.getWallet().getBalance() + amount);

		}

		regDao.save(targetUser);
		
		
		Transaction tr = new Transaction();

		tr.setTransactionId(Math.abs(random.nextInt() + 8769797));

		tr.setTransactionAmount(amount);

		tr.setTransationType("Amount transfer");
		tr.setDescription("Rs " + amount + " has been send to " + targetMobileNumber);
		tr.setUser(sourceUser);
		// findUser.getTransactions().add(tr);
		trasactionCurd.addTransactionService(tr);

		return tr;
	}

	@Override
	public Transaction depostAmountFromWalletToBankAccount(BankAccount bk, double amount, String unqId) {

		Optional<CurrentSession> opt = csDal.findById(unqId);

		if (!opt.isPresent()) {
			throw new BeneficiaryNotFound("you are not login");
		}

		// bank account find

		Optional<BankAccount> bkOpt = bankRepo.findById(bk.getAccountNumber());

		if (bkOpt.isEmpty()) {

			throw new BankAccountNotExsists("Bank account Not Exist");
		}

		UserAccountDetails user = regDao.getById(opt.get().getUserId());

		if (user.getWallet().getBalance() == null) {

			throw new InsufficientBalance("In your wallet Insufficient Balance");
		}

		if (user.getWallet().getBalance() < amount) {

			throw new InsufficientBalance("In your wallet Insufficient Balance");
		}
		

		if (!user.getBankAccounts().contains(bkOpt.get())) {

			throw new BankAccountNotAdded("Bank Account Not link to Wallet");
		}

		user.getWallet().setBalance(user.getWallet().getBalance() - amount);

		BankAccount bnkAccount = bkOpt.get();

		bnkAccount.setBalance(bnkAccount.getBalance() + amount);
		
		regDao.save(user);

		Transaction tr = new Transaction();

		tr.setTransactionId(Math.abs(random.nextInt() + 8769797));

		tr.setTransactionAmount(amount);

		tr.setTransationType("WalletToBank");
		tr.setDescription("Rs " + amount + " has been added to your Bank");
		tr.setUser(user);

		trasactionCurd.addTransactionService(tr);

		return tr;
	}

	@Override
	public List<Customer> getAllCustomer(String unqId) {

		Optional<CurrentSession> opt = csDal.findById(unqId);

		if (!opt.isPresent()) {
			
			throw new BeneficiaryNotFound("you are not login");
		}

		return cusDep.findAll();
	}

	@Override
	public Customer updateCustomer(Customer cus, String unqId) {

		Optional<CurrentSession> opt = csDal.findById(unqId);

		if (!opt.isPresent()) {
			throw new BeneficiaryNotFound("you are not login");
		}

		UserAccountDetails user = regDao.getById(opt.get().getUserId());

		user.getCustomer().setName(cus.getName());

		user.getCustomer().setPassword(cus.getPassword());

		regDao.save(user);
		
		CurrentSession csc=opt.get();

		csc.setUserId(cus.getPhone());

		csDal.save(csc);

		return user.getCustomer();
	}

	@Override
	public Transaction addMoneyIntoWalletFromBankAccount(BankAccount bk, double amount, String unqId) {

		Optional<CurrentSession> optcs = csDal.findById(unqId);

		if (!optcs.isPresent()) {
			throw new BeneficiaryNotFound("you are not login");
		}

		Optional<BankAccount> bkOpt = bankRepo.findById(bk.getAccountNumber());

		if (bkOpt.isEmpty()) {

			throw new BankAccountNotExsists("Bank account Not Exist");
		}

		Optional<UserAccountDetails> opt = regDao.findById(optcs.get().getUserId());

		UserAccountDetails findUser = opt.get();

		if (!findUser.getBankAccounts().contains(bkOpt.get())) {

			throw new BankAccountNotAdded("Bank Account Not link to Wallet");
		}

		if (bkOpt.get().getBalance() < amount) {

			throw new InsufficientBalance("Insufficient Balance in Bank Account");
		}

		BankAccount bkAccount = bkOpt.get();

		bkAccount.setBalance(bkAccount.getBalance() - amount);

		bankRepo.save(bkAccount);

		if (findUser.getWallet().getBalance() == null) {

			findUser.getWallet().setBalance(amount);
		} else {

			findUser.getWallet().setBalance(findUser.getWallet().getBalance() + amount);
		}

		regDao.save(findUser);

		Transaction tr = new Transaction();

		tr.setTransactionId(Math.abs(random.nextInt() + 8769797));

		tr.setTransactionAmount(amount);

		tr.setTransationType("Wallet Added");
		tr.setDescription("Rs " + amount + " has been added to your wallet");
		tr.setUser(findUser);

		// findUser.getTransactions().add(tr);
		trasactionCurd.addTransactionService(tr);

		return tr;
	}

}
