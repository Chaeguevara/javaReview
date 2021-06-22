package com.cos.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter3 implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//down cast
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 토큰은 로그인이 잘 되었을때 만들어줌
		// 요청할 때 마다 header에 Authorizationo에 value값으로 토큰을 가지고 옴
		// 토큰이 넘어오면, 내가 만든 토큰이 맞는지만 검증
		//토큰 만들었다고 가정. 이름은 코스
		if(req.getMethod().equals("POST")) {
			System.out.println("POST 요청됨");
			String headerAuth = req.getHeader("Authorization");
			System.out.println(headerAuth);
			
			if(headerAuth.equals("cos")) {
				chain.doFilter(req, res);				
			}else {
				PrintWriter out = res.getWriter();
				out.println("인증안됨");
			}
		}
		System.out.println("필터3");
	}

}
