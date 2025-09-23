package com.nt.service;

import java.util.Optional;

import com.nt.model.AppUser;

public interface IUserService {

	public String register(AppUser user);
	//public Optional<User> login(String userName,String pass);
}
