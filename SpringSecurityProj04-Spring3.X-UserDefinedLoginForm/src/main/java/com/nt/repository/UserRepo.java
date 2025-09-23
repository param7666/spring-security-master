package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Long> {
	
	@Query("from AppUser where userName = ?1")
	Optional<AppUser> findByUsername(String username);

}
