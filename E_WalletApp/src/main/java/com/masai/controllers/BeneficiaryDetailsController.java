package com.masai.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.BeneficiaryDetails;
import com.masai.models.UserAccountDetails;
import com.masai.services.BenificiaryDetailsServiceIntr;

@RestController
public class BeneficiaryDetailsController {
	
	@Autowired
	private BenificiaryDetailsServiceIntr benDetSerIntr;
	
	 

	@PostMapping(value="/Beneficiaries/{uniqueId}")
	public ResponseEntity<BeneficiaryDetails> saveBeneficiaryHandler(@RequestBody BeneficiaryDetails beneficiaryDetails,@PathVariable String uniqueId){
		
//		UserAccountDetails uad= new UserAccountDetails();
//		uad.setBeneficiaryDetails(beneficiaryDetails);
		
		
		return new ResponseEntity<BeneficiaryDetails>(benDetSerIntr.saveBeneficiaryDetails(beneficiaryDetails,uniqueId), HttpStatus.CREATED);
	}
	
	
	
	
	/*
	 * http://localhost:8080/Beneficiary/123
	 * */
	//get by id
	@GetMapping(value="/Beneficiary/{id}/{uniqueId}")
	public ResponseEntity<BeneficiaryDetails> getBeneficiaryDetailsByIdHandler(@PathVariable("id") String id,@PathVariable String uniqueId){
		
		
		return new ResponseEntity<BeneficiaryDetails>(benDetSerIntr.getBeneficiaryDetails(id,uniqueId), HttpStatus.OK);
		
	}
	
	
	 
	
	/*
	 * http://localhost:8080/Beneficiaries
	 */
	//get all
	@GetMapping(value=("/Beneficiaries/{uniqueId}"))
	public Set<BeneficiaryDetails> getAllBeneficiaryDetailsHandler(@PathVariable String uniqueId){
		
		return benDetSerIntr.getAllBeneficiaryDetails(uniqueId);
	}
	
	
	
	//delete by id
	@DeleteMapping("/Beneficiaries/{id}/{uniqueId}")
	public ResponseEntity<String> deleteByIdHandler(@PathVariable("id") String id,@PathVariable String uniqueId){
		
		benDetSerIntr.deleteBeneficiaryDetails(id,uniqueId);
		
		return new ResponseEntity<String>(id +" deleted ", HttpStatus.OK);
	}
	
	

}
