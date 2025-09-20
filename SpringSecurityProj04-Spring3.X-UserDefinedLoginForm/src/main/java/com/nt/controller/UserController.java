package com.nt.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.User;
import com.nt.service.IUserService;

import jakarta.servlet.http.HttpSession;

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
	public String register(@ModelAttribute("user")User u) {
		return "reg";
	}
	
	@PostMapping("/reg")
	public String register(@ModelAttribute("user") User u, RedirectAttributes atrs) {
		String message=ser.register(u);
		atrs.addFlashAttribute(message);
		System.out.println(message);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute("user")User u) {
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("user") User u, HttpSession ses,RedirectAttributes atrs) {
		System.out.println("UserController.login()");
		User user=ser.login(u.getUserName(), u.getPassword());
		if(user!=null) {
			ses.setAttribute("user", user);
			return "dashboard";
		} else {
			atrs.addFlashAttribute("msg","Login Fail....");
			System.out.println("Login Fail....");
			return "redirect:/";
		}
	}
}
