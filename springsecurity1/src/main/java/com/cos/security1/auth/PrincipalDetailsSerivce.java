package com.cos.security1.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

// 시큐리티 설정에서 loginProcessUrl("/login")
// /login청이 오면 자동으로 UserDetaislService 타입으로  IoC되어 있는 loadUserByUsername함수 실행
@Service //IoC등록
public class PrincipalDetailsSerivce implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	 
	// 시큐리티 session <= Auth <= Userdetails
	// 여기서 생성된 것은 Authentication에 들어감
	//Session안에는 다시 Auth가 들어감
	// 이거하면 로그인 끝..?
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//login에서 username이라고 꼭 적어야함. 안그러면 위에 안됨. 바꾸고 싶다면 SecurityConfig에서
		System.out.println("username:"+username);
		User userEntity = userRepository.findByUsername(username);// 기본으로 없음. 있는지 탐색
		if(userEntity != null) {//유저가 있다면
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

}
