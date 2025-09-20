package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query("from User where userName=?1 and password=?2")
	public User login(String userName,String password);

}
