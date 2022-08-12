package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Transaction;

public interface SaveTransactionDAL extends JpaRepository<Transaction, Integer> {

}
