package com.javasecurity.jwt.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javasecurity.jwt.api.entity.AuthRequest;
import com.javasecurity.jwt.api.util.JwtUtil;

@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String welcome() {
		return "Welcome to java!";
	}

	// 여기에는 security가 달려있어선 안됨
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			// 매니저가 유저이름과 pwd를 통해 authenticate를 시도
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("Invalid username/password");
		}

		// 성공하면 토큰 발급
		return jwtUtil.generateToken(authRequest.getUserName());
	}

}
