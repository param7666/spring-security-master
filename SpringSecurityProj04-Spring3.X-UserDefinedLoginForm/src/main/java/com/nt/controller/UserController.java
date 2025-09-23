package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.AppUser;
import com.nt.service.IUserService;

//@RestController
//@RequestMapping("/sec-app")
@Controller
public class UserController {
	
	@Autowired
	private IUserService ser;
	
	@GetMapping("/")
	public String homepage() {
		return "homepage";
	}
	
	@GetMapping("/reg")
	public String register(@ModelAttribute("user")AppUser u) {
		return "reg";
	}
	
	@PostMapping("/reg")
	public String register(@ModelAttribute("user") AppUser u, RedirectAttributes atrs) {
		String message=ser.register(u);
		atrs.addFlashAttribute(message);
		System.out.println(message);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute("user")AppUser u) {
		return "login";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		System.out.println("UserController.dashboard()");
		return "dashboard";
	}

	
	
}
