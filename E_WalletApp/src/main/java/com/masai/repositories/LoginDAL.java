package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.payloads.CurrentSession;

public interface LoginDAL extends JpaRepository<CurrentSession, String> {

}
