package com.cos.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@GetMapping({ "", "/" })
	public String index() {
		//머스ㅌ터치 사용함. 기본위치는 src/main/resources/
		//뷰리졸버 : template(prefix), .mustache(suffix) -> application.yml
		// Dependency로 생략 가능
		return "index"; //src/main/resources/templates/index.mustach를 의미 -> config에서 처리
	}
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public @ResponseBody String join(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		//ID와 CreationTIme은 어노테이션이 알아서 해줌
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);// 회원가입 됨 but 비번 1234 -> 시큐리티 로그인 불가. 암호화 안됨.
		return "redirect:/loginForm";
	}

}
