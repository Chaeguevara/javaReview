package io.security.springsecurity.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Spring Service로 바뀜
@Service
public class MyUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		//userName을 받아 본인의 방식대로 user를 반환함.
		
		//Security의 Default constructor. ID / PWD / Authorities
		// 여기에선 그냥 id : foo / pwd: foo
		return new User("foo","foo",new ArrayList<>());
	}

}
