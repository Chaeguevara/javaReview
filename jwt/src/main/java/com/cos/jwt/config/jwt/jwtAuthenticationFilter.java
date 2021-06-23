package com.cos.jwt.config.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.config.auth.PrincipalDetails;
import com.cos.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

//스프링 시큐리티에 아래의 필터 존재함.
// /login 요청할때 UsernamePasswordAuthenticationFilter 작동 (w/ Username, password, post)

@RequiredArgsConstructor
public class jwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	// /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter: 로그인 시도중");
		// 1.username,password받어서
		try {
//			BufferedReader br = request.getReader();
//			String input = null;
//			while((input=br.readLine())!=null) {
//				System.out.print(input);
//			}
			// json기준
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println(user);

			// 토큰 생성
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					user.getUsername(), user.getPassword());

			// 토큰을 통해 로그인 시도를 해봄. 로그인이 되면 만들어짐
			// principalDetailsservice의 loadUserByUsername()함수 실행
			// DB에 있는 username / password일치
			Authentication authentication = authenticationManager.authenticate(authenticationToken);

			// authentication 객체가 session에 저장. -> session의 principal가져옴 -> 가져와지면 로그인 되었음
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.println("로그인 완료됨: " + principalDetails.getUser().getUsername()); // 여기 잘 찍히면 로그인 된것임
			// authentication객체가 Session영역에 저장된 후 return
			// return은 권한관리를 Security가 해주기 때문에
			// 굳이 세션만들필요는 없지만 권한 처리 때문에 Session 만듬

			// JWT토큰 만들기
			return authentication; // ->세션에 저장됨
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("=========================================");
		// 2.정상인지 로그인 시도를 해봄 authenticationManager로 로그인 시도
		// -> PrincipalDetailsService호출 -> loadUserbyUsername 실행
		// 3. princicpal details -> session에 담음(권한 관리)

		// 4.JWT토큰을 만들어서 응답
		return null;
	}

	// attempt실행 후 인증이 정상적으로 되었으면 실행
	// 따라서 JWT토큰을 만들어서 request 요청한 사용자에게 JWT토큰을 응답
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		System.out.println("successfulAuthentication 실행됨: 인증이 완료 되었다는 뜻");
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal(); // 유저 정보

		// 라이브러리 이용한 토큰 만들기
		// Hash암호방식
		String jwtToken = JWT.create().withSubject("cos토큰") // 기본 패턴
				.withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 30)))// 토큰의 유효기간 10분
				.withClaim("id", principalDetails.getUser().getId()) // 클레임은 원하는대로
				.withClaim("username", principalDetails.getUser().getUsername())
				.sign(Algorithm.HMAC512("cos"));

		response.addHeader("Authorization", "Bearer " + jwtToken);
	}
}
