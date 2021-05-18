package io.security.springsecurity.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// 새로운 jwt 생성 / 기존 JWT로 부터 정보 추출 
@Service
public class JwtUtil {
	   private String SECRET_KEY = "secret";

	   // ExtractClaim을 이용해서 그 안의 이름 또는 종료 날짜를 얻어냄
	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	    
	    //두번째로 중요하다
	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        // 클레임이 무엇인지 밝힘
	        return claimsResolver.apply(claims);
	    }
	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	    }

	    //날짜를 뽑아서 이전인지 알아낸다.
	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	    
	    // 가장 중요. userDetail object를 받는다
	    // UserDetail 기반으로 jwt생성
	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        //Claim과 이름을 createToken에 넘김
	        return createToken(claims, userDetails.getUsername());
	    }

	    private String createToken(Map<String, Object> claims, String subject) {
	    	// 토큰 생성. Claims -> 누가 -> 언제 -> 언제까지
	        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	    }
	    
	    // 날짜와 이름을 확인한다	 
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }
}
