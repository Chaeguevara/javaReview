package com.cos.jwt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.cos.jwt.config.jwt.JwtAuthorizatinoFilter;
import com.cos.jwt.config.jwt.jwtAuthenticationFilter;
import com.cos.jwt.config.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private final CorsFilter corsFilter;
	@Autowired
	private final UserRepository userRepository;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); // filter3번을 제일 먼저 실행
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 세션 사용하지 않을 것 -> 쿠기 더이상 사용 안함
		.and()
		.addFilter(corsFilter) // 무조건 이 필터를 타게 됨. @CrosssOrigin(인증x), 인증(o)일때는 시큐리티 필터에 등록??
		.formLogin().disable() //From안씀
		.httpBasic().disable()  //Http로그인 안씀
		.addFilter(new jwtAuthenticationFilter(authenticationManager())) //필터 만들고 등록. Parameter필요 AuthenticationManager필요
		.addFilter(new JwtAuthorizatinoFilter(authenticationManager(),userRepository)) //
		.authorizeRequests()
		.antMatchers("/api/v1/user/**")
		.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/api/v1/manager/**")
		.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/api/v1/admin/**")
		.access("hasRole('ROLE_ADMIN')") // 각각 URL은 각각 해당 ROle이 있어야 접근 가능	
		.anyRequest().permitAll();
	}

}
