package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.UserAccountDetails;

public interface RegisterUserDAL extends JpaRepository<UserAccountDetails, String> {

}
