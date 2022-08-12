package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Wallet;

public interface SaveWalletDAL extends JpaRepository<Wallet, Integer> {

}
