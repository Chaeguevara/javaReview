package com.cos.jwt.config.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

//스프링 시큐리티에 아래의 필터 존재함.
// /login 요청할때 UsernamePasswordAuthenticationFilter 작동 (w/ Username, password, post)

@RequiredArgsConstructor
public class jwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	
	// /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter: 로그인 시도중");
		//1.username,password받어서
		//2.정상인지 로그인 시도를 해봄 authenticationManager로 로그인 시도 
		//-> PrincipalDetailsService호출 -> loadUserbyUsername 실행
		//3. princicpal details -> session에 담음(권한 관리)
		
		//4.JWT토큰을 만들어서 응답
		return super.attemptAuthentication(request, response);
	}
}
