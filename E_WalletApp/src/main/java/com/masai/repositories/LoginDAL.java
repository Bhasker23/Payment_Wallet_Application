package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.userInput.CurrentSession;

public interface LoginDAL extends JpaRepository<CurrentSession, String> {

}
