package com.nt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nt.repository.UserRepo;


@Configuration
@EnableWebSecurity
public class BankSecurityConfig {
	
	@Autowired
	private UserRepo repo;	
	
	@Bean
	public UserDetailsService userDetialsService() {
		return username->repo.findByUsername(username)
				.map(user->User.withUsername(user.getUserName())
						.password(user.getPassword()).disabled(!"ENABLED".equals(user.getStatus()))
						.roles("USER").build()).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
				
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/", "/login", "/reg", "/css/**", "/js/**").permitAll() // public URLs
	            .requestMatchers("/dashboard").authenticated()
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")                 // custom login page (GET)
	            .loginProcessingUrl("/doLogin")      // form action POST URL
	            .usernameParameter("username")       // input name for username
	            .passwordParameter("password")       // input name for password
	            .defaultSuccessUrl("/dashboard", true) // redirect after login
	            .failureUrl("/login?error=true")     // redirect on failed login
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login?logout=true")
	            .permitAll()
	        )
	        .csrf(csrf -> csrf.disable()); // disable CSRF for testing (enable later!)

	    return http.build();
	}

}
