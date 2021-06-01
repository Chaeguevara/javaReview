package com.cos.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/test/login")
	public @ResponseBody String testLogin(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails) { // DI의존성 주입Dependancy Injection
		//@AuthenticationPrincipal ->Session정보 가져옴
		System.out.println("/test/login =============");
		PrincipalDetails princicpalDetails = (PrincipalDetails)authentication.getPrincipal();
		System.out.println("authentication: "+princicpalDetails.getUser());
		System.out.println("userDetails: "+userDetails.getUser()); //더 간편한 방법. From @AUth~
		return "세션 정보 확인하기";
	}

	@GetMapping("/test/oauth/login")
	public @ResponseBody String testOAuthLogin(Authentication authentication,
			@AuthenticationPrincipal OAuth2User oauth) { // DI의존성 주입Dependancy Injection
		//@AuthenticationPrincipal ->Session정보 가져옴
		System.out.println("/test/oauth/login =============");
		OAuth2User oauth2User = (OAuth2User)authentication.getPrincipal();
		System.out.println("authentication: "+oauth2User.getAttributes());
		System.out.println("oahtu2User : "+oauth.getAttributes());
		return "세션 정보 확인하기";
	}
	
	// userDetails + Oauth2User -> princicipalDetials -> Authentication -> SecuritySession
	
	@GetMapping({ "", "/" })
	public String index() {
		// 머스ㅌ터치 사용함. 기본위치는 src/main/resources/
		// 뷰리졸버 : template(prefix), .mustache(suffix) -> application.yml
		// Dependency로 생략 가능
		return "index"; // src/main/resources/templates/index.mustach를 의미 -> config에서 처리
	}

	@GetMapping("/user") // 두개의 로그인 정보 중 무엇을 어떻게 할 것인가 -> 하나의 클래스 만듬 -> 두개의 클래스를 implement
	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("princicpalDetails:"+principalDetails.getUser());
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

	//일반 또는 OAuth둘다에 대해서 정보를 받아옴
	@PostMapping("/join")
	public @ResponseBody String join(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		// ID와 CreationTIme은 어노테이션이 알아서 해줌
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);// 회원가입 됨 but 비번 1234 -> 시큐리티 로그인 불가. 암호화 안됨.
		return "redirect:/loginForm";
	}

	@Secured("ROLE_ADMIN") // 어드민만 볼 수 있다.
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 실행직전에 된다
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터정보";
	}

}
