package com.cos.jwt.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.config.auth.PrincipalDetails;
import com.cos.jwt.config.repository.UserRepository;
import com.cos.jwt.model.User;

//시큐리티가 Filter 가지고 잇음. 그 필터중 BasicAuthenticationFilter 존재
// 권한, 인증이 필요한 특정 주소 요청시 무조건 위의 필터 타게 되어있음
// 만약 권한 인증이 필요한 주소가 아니면 필터를 타지 않음
public class JwtAuthorizatinoFilter extends BasicAuthenticationFilter{

	private UserRepository userRepository;
	
	public JwtAuthorizatinoFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}
	
	//인증이나 권한이 필요한 주소요청이 있을때 해당 필터를 타게 됨.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//super.doFilterInternal(request, response, chain); 지워야함 -> 응답을 두번하게 되서 오류
		System.out.println("인증이나 권한이 필요한 주소 요청됨");
		
		//헤더 확인
		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader: "+jwtHeader);
		
		
		//JWT 토큰을 검증해서 정상적인 사용자인지 확인
		// 헤더가 없거나, 시작 값이 문제가 있다면
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return; //아무것도 하지 않음
		}
		
		String jwtToken = request.getHeader("Authorization").replace("Bearer ", ""); // 앞의 "Bearer"제거 -> 토큰 내용만 남음
		String username = JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken).getClaim("username").asString(); //서명해서 유저이름을 가져옴
		System.out.println(username);
		
		//서명이 정상적으로 됨
		// 유저 리포와 연결
		if (username!=null) {
			//정상적으로 선택되면 정상적인 유저임
			User userEntity = userRepository.findByUsername(username);
			
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			//강제로 만든다. JWT토큰 서명이 정상이면 Authentication객체를 만들어줌
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities()); //가짜를 만드니까 비밀번호는 필요 없음. 유저가 null이 아닌 것은 유저가 있다는 뜻
			SecurityContextHolder.getContext().setAuthentication(authentication); // 세션 공간. 강제로 저장시킴
			//체인을 타게 함
			chain.doFilter(request, response);
		}
	}

}
