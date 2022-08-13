package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.models.BillPayment;

@Repository
public interface BillPaymentDAO extends JpaRepository<BillPayment, Integer> {

}
