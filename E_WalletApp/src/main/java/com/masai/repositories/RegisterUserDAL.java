package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.models.UserAccountDetails;

@Repository
public interface RegisterUserDAL extends JpaRepository<UserAccountDetails, String> {

	
}
