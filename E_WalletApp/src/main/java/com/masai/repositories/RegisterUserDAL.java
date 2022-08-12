package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Customer;

public interface RegisterUserDAL extends JpaRepository<Customer, Integer> {

}
