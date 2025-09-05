package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringSecurityController {

	@GetMapping("/")
	public String showHomepage() {
		System.out.println("SpringSecurityController.showHomepage()");
		return "home";
	}
	
	@GetMapping("/denied")
	public String accessDenied() {
		System.out.println("SpringSecurityController.accessDenied()");
		return "denied";
	}
}
