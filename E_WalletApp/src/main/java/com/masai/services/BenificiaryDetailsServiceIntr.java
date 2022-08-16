package com.masai.services;

import java.util.List;
import java.util.Set;

import com.masai.exceptions.BeneficiaryNotFound;
import com.masai.exceptions.InvalidBeneficiaryDetails;
import com.masai.models.BeneficiaryDetails;
import com.masai.repositories.BeneficiaryDetailsDao;

public interface BenificiaryDetailsServiceIntr{
	
	

	public BeneficiaryDetails saveBeneficiaryDetails(BeneficiaryDetails beneficiaryDetails, String uniqueId);
	
	public BeneficiaryDetails getBeneficiaryDetails(String id,String uniqueId) throws BeneficiaryNotFound;
	
	public Set<BeneficiaryDetails> getAllBeneficiaryDetails(String uniqueId) throws BeneficiaryNotFound;
	
	public String  deleteBeneficiaryDetails(String id,String uniqueId)throws InvalidBeneficiaryDetails;
	
	
	
	
}
