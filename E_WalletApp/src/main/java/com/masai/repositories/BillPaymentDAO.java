package com.masai.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.models.BillPayment;

@Repository
public interface BillPaymentDAO extends JpaRepository<BillPayment, Integer> {
	
	@Query("from BillPayment where user_id=?1")
	public List<BillPayment> findByUserId(String userid);

}
