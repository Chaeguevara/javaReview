package com.loginServer.loginserver.cofig.auth;

//Security 가 Login 낚아챔
// 로그인 완료되면 Security Session에 넣어줌(Security ContextHolder가 Key값)
// Authentication객체만 들어갈 수 있음
// in Authentication -> 유저정보가 있음.
// User 오브젝트 타입은 = UserDetails 타입

import com.loginServer.loginserver.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Security Session -> Authentication -> UserDetails(PrincipalDetails)
public class PrincipalDetails implements UserDetails {

    private User user; //z컴포지션

    public PrincipalDetails(User user){
        this.user = user;
    }

    //해당 유저의 권한 return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 1년 지났는가
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //1년동안 로그인 안하면 휴면계정 -> user 객체에 login date 필요
        // 현재 시간과 login date를 통해 계산

        return true;
    }
}
