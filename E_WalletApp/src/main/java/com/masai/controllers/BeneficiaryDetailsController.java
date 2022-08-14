package com.masai.controllers;

import java.util.List;

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
import com.masai.servicesIntr.BenificiaryDetailsServiceIntr;

@RestController
public class BeneficiaryDetailsController {
	
	@Autowired
	private BenificiaryDetailsServiceIntr benDetSerIntr;
	
	
	/*postMaping
	 * 
	 * http://localhost:8080/Beneficiaries
	 * 
	       {
		    "phoneNumber": "123",
		    "name": "laxmi"
    		}
    		
    		  {
		    "phoneNumber": "1234",
		    "name": "Bhasker kumar"
		    }
		    
		        {
			    "phoneNumber": "12345",
			    "name": "shatyam jha"
				}
	 */
	//save 
	@PostMapping(value="/Beneficiaries")
	public ResponseEntity<BeneficiaryDetails> saveBeneficiaryHandler(@RequestBody BeneficiaryDetails beneficiaryDetails){
		
//		UserAccountDetails uad= new UserAccountDetails();
//		uad.setBeneficiaryDetails(beneficiaryDetails);
		
		
		return new ResponseEntity<BeneficiaryDetails>(benDetSerIntr.saveBeneficiaryDetails(beneficiaryDetails), HttpStatus.CREATED);
	}
	
	
	
	
	/*
	 * http://localhost:8080/Beneficiary/123
	 * */
	//get by id
	@GetMapping(value="/Beneficiary/{id}")
	public ResponseEntity<BeneficiaryDetails> getBeneficiaryDetailsByIdHandler(@PathVariable("id") String id){
		
		
		return new ResponseEntity<BeneficiaryDetails>(benDetSerIntr.getBeneficiaryDetails(id), HttpStatus.OK);
		
	}
	
	
	 
	
	/*
	 * http://localhost:8080/Beneficiaries
	 */
	//get all
	@GetMapping(value=("/Beneficiaries"))
	public List<BeneficiaryDetails> getAllBeneficiaryDetailsHandler(){
		
		return benDetSerIntr.getAllBeneficiaryDetails();
	}
	
	
	
	//delete by id
	@DeleteMapping("/Beneficiaries/{id}")
	public ResponseEntity<String> deleteByIdHandler(@PathVariable("id") String id){
		
		benDetSerIntr.deleteBeneficiaryDetails(id);
		
		return new ResponseEntity<String>("deleted ", HttpStatus.OK);
	}
	
	

}
