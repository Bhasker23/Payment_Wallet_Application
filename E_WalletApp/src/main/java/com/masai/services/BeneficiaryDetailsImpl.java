package com.masai.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BeneficiaryNotFound;
import com.masai.exceptions.InvalidBeneficiaryDetails;
import com.masai.models.BeneficiaryDetails;
import com.masai.models.UserAccountDetails;
import com.masai.repositories.BeneficiaryDetailsDao;
import com.masai.repositories.CurrentSessionDAL;
import com.masai.repositories.RegisterUserDAL;
import com.masai.userInput.CurrentSession;

@Service
public class BeneficiaryDetailsImpl implements BenificiaryDetailsServiceIntr {

	@Autowired
	private BeneficiaryDetailsDao bdao;

	@Autowired
	RegisterUserDAL dal;

	@Autowired
	CurrentSessionDAL curdao;

	@Override
	public BeneficiaryDetails saveBeneficiaryDetails(BeneficiaryDetails beneficiaryDetails, String uniqueId) {

		Optional<CurrentSession> opt = curdao.findById(uniqueId);

		if (!opt.isPresent()) {
			throw new BeneficiaryNotFound("you are not login");
		}

		Optional<UserAccountDetails> uacd = dal.findById(opt.get().getUserId());
		
		
		if (uacd.isEmpty()) {

			throw new BeneficiaryNotFound("beneficiary user not register");
		}

		Optional<UserAccountDetails> user = dal.findById(opt.get().getUserId());
		
		System.out.println("22222");

		UserAccountDetails userAccountDetails = user.get();
		
        beneficiaryDetails.setUser(userAccountDetails);
        
		userAccountDetails.getBeneficiaryDetails().add(beneficiaryDetails);

		dal.save(userAccountDetails);

		return beneficiaryDetails;
	}

	@Override
	public BeneficiaryDetails getBeneficiaryDetails(String id, String uniqueId) throws BeneficiaryNotFound {

		Optional<CurrentSession> opt = curdao.findById(uniqueId);

		if (!opt.isPresent()) {
			throw new BeneficiaryNotFound("you are not login");
		}

		return bdao.findById(id).orElseThrow(
				() -> new BeneficiaryNotFound("beneficiary not found id you are giving that is not exist"));
	}

	@Override
	public Set<BeneficiaryDetails> getAllBeneficiaryDetails(String uniqueId) throws BeneficiaryNotFound {

		Optional<CurrentSession> opt = curdao.findById(uniqueId);

		if (!opt.isPresent()) {
			throw new BeneficiaryNotFound("you are not login");
		}

		UserAccountDetails userOpt = dal.getById(opt.get().getUserId());

		return userOpt.getBeneficiaryDetails();
	}

	@Override
	public String deleteBeneficiaryDetails(String id, String uniqueId) throws InvalidBeneficiaryDetails {

		Optional<CurrentSession> opt = curdao.findById(uniqueId);

		if (!opt.isPresent()) {
			throw new BeneficiaryNotFound("you are not login");
		}

		UserAccountDetails user = dal.getById(opt.get().getUserId());

		System.out.println("11111");
		
		Set<BeneficiaryDetails> bnSet = user.getBeneficiaryDetails();
		
		System.out.println("2222");

		boolean b = true;
		
		for (BeneficiaryDetails bn : bnSet) {

			System.out.println(bn.getPhoneNumber()+"  "+id);
			if (bn.getPhoneNumber().equals(id)) {
				bnSet.remove(bn);
				b = false;
				break;
			}
		}
		System.out.println("333");
		
		if (b) {

			throw new BeneficiaryNotFound("Beneficiary Not Found in your Beneficiary set by id " + id);
		}
		
		
		System.out.println("3333");

		dal.save(user);

		return "beneficiary deleted ";
	}

}
