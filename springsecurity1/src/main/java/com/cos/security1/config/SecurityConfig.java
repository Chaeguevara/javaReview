package com.cos.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.security1.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured어노테이션 활성화
//preAuthorization,post활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	//Bean을 적으면, 해당 메서드의 리턴되는 오브젝트를 IoC등록
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/user/**")
				.authenticated()
				.antMatchers("/manager/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
				.antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')")
				.anyRequest()
				.permitAll()
				.and()
				.formLogin()
				.loginPage("/loginForm")
				.loginProcessingUrl("/login")//login 주소가 호출되면 시큐리티가 낚아채서 대신 진행?? -> 컨트롤러에 로그인 만들필요가 없음
				.defaultSuccessUrl("/")//성공시 기본 이동. 특정 페이지를 호출했다면 그곳으로 로그인 후 이동
				.and()
				.oauth2Login() // oauth2URL실행될때 무언가 하는 것
				.loginPage("/loginForm")//구글로그인이 완료된 뒤의 후처리 필요. 
				//카카오의 경우코드 받기(인증) -> 액세스 토큰(권한) -> 권한을 통해 사용자 프로필 -> 
				//토대로 자동 가입 또는 추가적인 정보 필요(배송정보)하면 추가 창 띄움
				//구글의 경우 액세스토큰 + 사용자프로필정보 받음
				.userInfoEndpoint()
				.userService(principalOauth2UserService)
				; 
	}
}
