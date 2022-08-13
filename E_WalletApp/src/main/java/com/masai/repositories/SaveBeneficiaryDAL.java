package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.BeneficiaryDetails;

public interface SaveBeneficiaryDAL extends JpaRepository<BeneficiaryDetails, String>{

}
