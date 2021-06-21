package com.cos.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 내서버가 응답을 할때 json을 자바스크립트에서 처리 할 ㅅ ㅜ 있게 할지 설정
		config.addAllowedOrigin("*"); // 모든 ip의 응답을 허용함
		config.addAllowedHeader("*"); // 모든 header의 응답을 허용
		config.addAllowedMethod("*"); // 모든 post,get,put,delete,patch 요청을 허용 -> 필터에 등록해줘야함
		source.registerCorsConfiguration("/api/**", config); // Api로 들어온 것들은 모두 이 컨피글르 따름
		return new CorsFilter(source);
	}
}
