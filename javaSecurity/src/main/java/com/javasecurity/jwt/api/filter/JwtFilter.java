package com.javasecurity.jwt.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.javasecurity.jwt.api.service.CustomUserDetailsService;
import com.javasecurity.jwt.api.util.JwtUtil;

//Filter 등록
@Component
public class JwtFilter extends OncePerRequestFilter{

	// token에서 이름 뺴오기
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//Request에서 Authorizationfilter를 먼저 꺼낸다
		String authorizationHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		//Bearer ~~~~~ 구조로 되어 있음. -> Key부분만 꺼내야한다
		if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7); //앞에 부분 잘라냄
			userName = jwtUtil.extractUsername(token);
		}
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() ==null) {
			UserDetails userDetails = service.loadUserByUsername(userName);
			// 토큰 Validation
			if(jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
		
	}
}
