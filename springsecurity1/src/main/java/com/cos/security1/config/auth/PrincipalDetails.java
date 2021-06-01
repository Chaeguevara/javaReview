package com.cos.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.security1.model.User;

import lombok.Data;

//시큐ㅣㄹ티가 /login 주소 요청이 오면 낚아챔
// 로그인이 완료되면 시큐리티session을 만들어 넣어줌
//시큐리티의 session??. 시큐리티 자신만의 세션 공간(Security ContextHolder) 이곳에 세션 정보 저장
// 들어갈 수 있는 오브젝트가정해져있음. -> Authentication 타입 객체
// in Authentication 안에 유저 정보 있어야함
// 유저 오브젝트 타입 -> 유저 디테일즈 타입만 포함??
// 시큐리티 세션에 정보 저장 <= Authentication 저장(Fix) <= 유저정보(UserDetails(PrincipalDetails) Type)이어야함. <= 유저정보
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
	
	private User user;//컴포지션. 우리의 유저정보
	private Map<String, Object> attributes;
	
	//일반로그인
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	//OAuth로그
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	//해당 유저의 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*Role 은 user.getRole()로 구할 수 있지만 String이라서 변환*/
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole(); //String타입임.;
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 사이트에서 1년동안 로그인을 안하면 휴면 계정.
		// user.getLoginDate();  -> 현재시간이랑 차이. 1년 초과 -> return false
		return true;
	}

	//OAuth2의 모든 정보들
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	//구그르이 PK. 안중요. 스킵
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
