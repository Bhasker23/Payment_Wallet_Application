package com.masai.servicesIntr;

import java.util.List;

import com.masai.exceptions.BeneficiaryNotFound;
import com.masai.exceptions.InvalidBeneficiaryDetails;
import com.masai.models.BeneficiaryDetails;
import com.masai.repositories.BeneficiaryDetailsDao;

public interface BenificiaryDetailsServiceIntr{
	
	

	public BeneficiaryDetails saveBeneficiaryDetails(BeneficiaryDetails beneficiaryDetails);
	
	public BeneficiaryDetails getBeneficiaryDetails(String id) throws BeneficiaryNotFound;
	
	public List<BeneficiaryDetails> getAllBeneficiaryDetails() throws BeneficiaryNotFound;
	
	public String  deleteBeneficiaryDetails(String id)throws InvalidBeneficiaryDetails;
	
	
	
}
