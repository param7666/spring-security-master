package com.nt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	// Bean method to create security filters
	
	@Bean
	public SecurityFilterChain defualtSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((request)->request
				.requestMatchers("/bank/welcome").permitAll()
				.requestMatchers("/bank/balance","/bank/loanAprove","/bank/offers").authenticated())
		.formLogin(Customizer.withDefaults());
		return http.build(); // return DefualtSecurityFilterChain object which is implemention class of SecurityFilterChain
	}
	
	// Bean method to create User as inMemory Data
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
// we have to option for create user as inmemory data
		
// option 1) where we use withDefualtPasswordEncoder() (deprecated) for crete user details
		
		// create admin detials
//		UserDetails admin=User.withDefaultPasswordEncoder()
//				.username("param")
//				.password("sundar")
//				.build();
//		
//		// create user details
//		UserDetails user=User.withDefaultPasswordEncoder()
//				.username("sundar")
//				.password("param")
//				.build();
		
		
		
// option 2) where we use BCryptPasswordEncoder bean while create useer
		
		UserDetails user1=User.withUsername("sundar")
				.password(encoder().encode("param"))
				.authorities("CUSTOMER").build();
		
		UserDetails user2=User.withUsername("param")
				.password(encoder().encode("sundar"))
				.authorities("CUSTOMER","MANAGER").build();
		
		return new InMemoryUserDetailsManager(user1,user2);
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
