package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity  // makes the spring configuation class as spring security configuration class
public class SecurityConfig extends WebSecurityConfigurerAdapter { 

	/*
	 WebSecurityConfigurerAdapter is deprecated from spring 5 version
	 if you want to still use chnege your spring version for pom.xml file by adding version 
	 <version>2.6.15</version>
	 */
	
	// method for create in memory user
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		// creates in memory user with param as uesrname and sundar as password with encryption and customer as role
		auth.inMemoryAuthentication().withUser("param").password("${noop}sundar").roles("CUSTOMER");
		// creates in memory user with param as uesrname and sundar as password with encryption and customer as role
		// ${noop}param means encrypted password
		auth.inMemoryAuthentication().withUser("sundar").password("${noop}param").roles("MANAGER");

	}
	
	// confihuration for authentication + authorization
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll() // authentication and authorzation not required
		.antMatchers("/offer").authenticated() // only authentication
		.antMatchers("/balance").hasAnyRole("CUSTOMER","MANAGER") // authentication and authorzation for customer and manager
		.antMatchers("/loanAprove").hasAnyRole("MANAGER") //authentication and authorzatio ONLY FOR  manager
		.anyRequest().authenticated() // remaining all must be authenticated
		
		.and().httpBasic() // specify authentication mode (uses the browser managed form for username and password)
		.and().exceptionHandling().accessDeniedPage("/denied"); // controller goes to denied page generate error 404
	}
	
	
	
}
