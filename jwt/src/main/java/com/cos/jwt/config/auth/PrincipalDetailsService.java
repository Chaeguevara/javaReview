package com.cos.jwt.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.jwt.config.repository.UserRepository;
import com.cos.jwt.model.User;

import lombok.RequiredArgsConstructor;


//http://locahost:8080/login 일때 해야하지만 login disable로 안됨 -> trigger 시키는 filter 필요
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			System.out.println("PrincicpalDetailsService의 loadUserByUsername()");
			User userEntity = userRepository.findByUsername(username);
			System.out.println("userEntity :"+userEntity);
			return new PrincipalDetails(userEntity);
		}
}
