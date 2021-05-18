package com.example.configuration.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.repository.UserRepo;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final UserRepo userRepo;

	public SecurityConfig(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> userRepo
				.findByUserName(username)
				.orElseThrow(
						() -> newUsernameNotFoundException(
								format("User: %s, not found",username)
								)
						));
		
	}




}
