package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.payloads.CurrentSession;

@Repository
public interface CurrentSessionDAL extends JpaRepository<CurrentSession, String> {

}
