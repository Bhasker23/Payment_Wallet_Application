package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.BankAccount;

public interface SaveBankAccDAL extends JpaRepository<BankAccount, Integer> {

}
