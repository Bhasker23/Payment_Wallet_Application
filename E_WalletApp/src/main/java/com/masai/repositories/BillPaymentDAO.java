package com.masai.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.models.BillPayment;

@Repository
public interface BillPaymentDAO extends JpaRepository<BillPayment, Integer> {
	
	@Query("from BillPayment where user user.id=?1")
	public Set<BillPayment> findByUserId(String userid);

}
