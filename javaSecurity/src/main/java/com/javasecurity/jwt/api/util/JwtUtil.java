package com.javasecurity.jwt.api.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String secret = "javatechie";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// 날짜검증.
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	// 시작점
	public String generateToken(String username) {
		// 유저이름을 받아서, Empty맵과 username을 넘김
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String subject) {
		// claime은 emptymap, subject = username
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간 유효 토큰
				.signWith(SignatureAlgorithm.HS256, secret).compact(); // 여러 알고리즘중 하나
	}

	// 토큰 검증
	public Boolean validateToken(String token, UserDetails userDetails) {
		// 토큰에서 이름 가져옴
		final String username = extractUsername(token);
		// 이름이 있는지 + 토큰 유효기간이 넘지 않았는지.
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
