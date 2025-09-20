package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.User;
import com.nt.repository.UserRepo;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public String register(User user) {
		System.out.println("UserService.register()");
		Long id=repo.save(user).getId();
		return "User saved with id number "+id;
	}

	@Override
	public User login(String userName, String pass) {
		System.out.println("UserService.login()");
		return repo.login(userName, pass);
	}

}
