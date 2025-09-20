package com.nt.service;

import com.nt.model.User;

public interface IUserService {

	public String register(User user);
	public User login(String userName,String pass);
}
