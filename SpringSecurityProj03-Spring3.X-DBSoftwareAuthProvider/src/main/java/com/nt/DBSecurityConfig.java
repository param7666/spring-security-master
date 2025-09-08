package com.nt;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class DBSecurityConfig {

	@Autowired
	private DataSource ds;
	
	@Bean
	public JdbcUserDetailsManager createUserUDM(DataSource ds) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(ds);

	    // Custom queries for your table structure
	    jdbcUserDetailsManager.setUsersByUsernameQuery(
	        "SELECT USERNAME, PASSWORD, STATUS as enabled FROM APP_USERS WHERE USERNAME = ?"
	    );
	    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
	        "SELECT USERNAME, ROLE_NAME as authority FROM APP_ROLES WHERE USERNAME = ?"
	    );

	    return jdbcUserDetailsManager;
	}
	
	@Bean
	public SecurityFilterChain defualtSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((req)->
		req.requestMatchers("/bank/welcome").permitAll()
		.requestMatchers("/bank/balance","/bank/loanAprove","/bank/offers").authenticated())
		.formLogin(Customizer.withDefaults());
		return http.build();
	}
	

	
//	@Bean
//	public PasswordEncoder encoder() {
//		//return new BCryptPasswordEncoder();
//		return new PasswordEncoder();
//	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return NoOpPasswordEncoder.getInstance(); // for no ecryption
	}
}
