package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.models.Transaction;

@Repository
public interface SaveTransactionDAL extends JpaRepository<Transaction, Integer> {

}
