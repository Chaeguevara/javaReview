package com.cos.security1.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.config.oauth.provider.FacebookUserInfo;
import com.cos.security1.config.oauth.provider.GoogleUserInfo;
import com.cos.security1.config.oauth.provider.OAuth2UserInfo;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	// google로그인 후 후처리
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration: " + userRequest.getClientRegistration()); // registrationId로 어떤
																								// OAuth로그인 했는지 확인가능
		System.out.println("getTokenValue: " + userRequest.getAccessToken().getTokenValue());

		OAuth2User oauth2User = super.loadUser(userRequest);
		// 구글로그인 클릭 -> 창이 뜸 -> 로그인 완료 -> 코드 리턴받음(OAuth-Client가 받음) -> Access Toekn요청.
		// UserRequest Wjdqh
		// UserRequest정보 -> 회원 프로필 "loadUser 함수" -> 프로필 받음 from google
		System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes());

		// 강제 회원가입.?
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북 로그인 요청");
			oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
		}else {
			System.out.println("우리는 구글과 페이스북만...");
		}
		
		String provider = oAuth2UserInfo.getProvider(); // Google or facebook
		String providerId = oAuth2UserInfo.getProviderId(); // PK
		String username = provider + "_" + providerId; //google_subid(1022312???) -> 유저이름 충동할일 없음. 사실상 Oauth2User로 로그인하면 필요없긴함
		String password = bCryptPasswordEncoder.encode("하이데어");
		String email = oAuth2UserInfo.getEmail(); // PK
		String role = "ROLE_USER";
		
		//이미 아이디 있는지 확인
		User userEntity = userRepository.findByUsername(username);

		if (userEntity == null) {
			System.out.println("구글로그인이 최초입니다");
			//Constructor와 같음. 어노테이션 활용
			userEntity = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.providerId(providerId)
					.provider(provider)
					.build();
			userRepository.save(userEntity);
		}else {
			System.out.println("이미 구글로그인을 한적이 있습니다."); 
		}
		// authentication객체에 들어갈 것임
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());
	}
}
