package com.nt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank") // globel path
public class SecurityController {

	@GetMapping("/welcome")
	public ResponseEntity<String> homepage(){
		System.out.println("SecurityController.homepage()");
		return new ResponseEntity<String>("Home page ",HttpStatus.OK);
	}
	
	@GetMapping("/offers")
	public ResponseEntity<String> offers() {
		System.out.println("SecurityController.offers()");
		return new ResponseEntity<String>("Offer page",HttpStatus.OK);
	}
	
	@GetMapping("/loanAprove")
	@PreAuthorize("hasAuthority('MANAGER')") // specifying authority
	public ResponseEntity<String> loanAprove() {
		System.out.println("SecurityController.loanAprove()");
		return new ResponseEntity<String>("Loan Aprove..",HttpStatus.OK);
	}
	
	@GetMapping("/balance")
	@PreAuthorize("hasAnyAuthority('MANAGER','CUSTOMER')") // specifying authority
	public ResponseEntity<String> balanceCheck() {
		System.out.println("SecurityController.balanceCheck()");
		return new ResponseEntity<String>("balance check",HttpStatus.OK);
	}
	
	
}
