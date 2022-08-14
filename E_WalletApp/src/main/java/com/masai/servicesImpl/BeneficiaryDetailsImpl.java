package com.masai.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BeneficiaryNotFound;
import com.masai.exceptions.InvalidBeneficiaryDetails;
import com.masai.models.BeneficiaryDetails;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.BeneficiaryDetailsDao;
import com.masai.repositories.RegisterUserDAL;
import com.masai.servicesIntr.BenificiaryDetailsServiceIntr;

@Service
public class BeneficiaryDetailsImpl implements BenificiaryDetailsServiceIntr{
	
	@Autowired
	private BeneficiaryDetailsDao bdao;
	
	@Autowired
	RegisterUserDAL dal;

	@Override
	public BeneficiaryDetails saveBeneficiaryDetails(BeneficiaryDetails beneficiaryDetails) {
		
	Optional<UserAccountDetails> uacd=	dal.findById(beneficiaryDetails.getPhoneNumber());
		
	if(!uacd.isPresent()) {
		
		throw new BeneficiaryNotFound("beneficiary user not register");
	}
		
		return bdao.save(beneficiaryDetails);
	}

	@Override
	public BeneficiaryDetails getBeneficiaryDetails(String id) throws BeneficiaryNotFound {
		
		return bdao.findById(id).orElseThrow(()-> new BeneficiaryNotFound("beneficiary not found id you are giving that is not exist"));
	}

	@Override
	public List<BeneficiaryDetails> getAllBeneficiaryDetails() throws BeneficiaryNotFound {
		
		return bdao.findAll();
	}

	@Override
	public String deleteBeneficiaryDetails(String id) throws InvalidBeneficiaryDetails {
		
		
		bdao.findById(id).orElseThrow(()-> new BeneficiaryNotFound("beneficiary not found try again with other id"));
		
		bdao.deleteById(id);
		
		return "beneficiary deleted ";
	}
	
	

}
