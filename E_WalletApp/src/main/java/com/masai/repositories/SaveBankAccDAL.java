package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.BankAccount;
import com.masai.models.UserAccountDetails;

import antlr.collections.List;

public interface SaveBankAccDAL extends JpaRepository<BankAccount, Integer> {

}
