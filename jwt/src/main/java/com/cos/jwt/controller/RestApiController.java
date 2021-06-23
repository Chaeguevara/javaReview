package com.cos.jwt.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.jwt.config.repository.UserRepository;
import com.cos.jwt.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestApiController {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/home")
	public String home() {
		return "<h1>home</h1>";
	}
	
	@PostMapping("/token")
	public String token() {
		return "<h1>toekn</h1>";
	}
	
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles("ROLE_USER");
		userRepository.save(user);
		return "회원가입완료";
	}
	
	//user / manager / admin 권한 접근 가능
	@PostMapping("api/v1/user")
	public String user() {
		return "user";
	}

	//manager / admin 권한 접근 가능
	@PostMapping("api/v1/mananger")
	public String mananger() {
		return "mananger";
	}
	
	//admin 권한 접근 가능
	@PostMapping("api/v1/admin")
	public String admin() {
		return "admin";
	}
}
