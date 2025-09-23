package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.model.AppUser;
import com.nt.repository.UserRepo;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public String register(AppUser user) {
		System.out.println("UserService.register()");
		Long id=repo.save(user).getId();
		return "User saved with id number "+id;
	}

//	@Override
//	public Optional<User> login(String userName, String pass) {
//		System.out.println("UserService.login()");
//		return repo.findByUsernameAndPassword(userName, pass);
//	}

}
