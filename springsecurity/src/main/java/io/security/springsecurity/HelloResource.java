package io.security.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.security.springsecurity.models.AuthenticationRequest;
import io.security.springsecurity.models.AuthenticationResponse;
import io.security.springsecurity.services.MyUserDetailsService;
import io.security.springsecurity.util.JwtUtil;

//Rest API
@RestController
public class HelloResource {
	
	//authenticate 하기위해서 manager필요
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	
	//User name / passowrd를 authenticationRequest로 받음
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			//토큰을 검증함 
			// 토큰이 잘못되었다면, Exception을 던진다
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
					);			
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or password",e);
		}
		
		//성공했다면 -> JWT
		// userDetail로 부터 jwt검증?
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails); // Token생성
		//return 
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
