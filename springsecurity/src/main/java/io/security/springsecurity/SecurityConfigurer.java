package io.security.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.security.springsecurity.services.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Spring security가 myUserDetailsService를 이용하도록 하는 흐름
		auth.userDetailsService(myUserDetailsService);
	}
	
	// 모든것에 대해 security를 필요로하는것은 말이 안됨. 
	// security를 필요로 하는것과 그렇지 않은것들 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/authenticate").permitAll() // 누구나 접근 가능
		.anyRequest().authenticated(); // 나머지는 authentication 필요함
	}

	//New Version에 따른 조치 
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	//아무런 해싱도 하지 않는다
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
