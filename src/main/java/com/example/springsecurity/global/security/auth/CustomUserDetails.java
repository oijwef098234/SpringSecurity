package com.example.springsecurity.global.security.auth;

import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.entity.enums.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public record CustomUserDetails(User user) implements UserDetails {
    @Override
    public String getUsername() { // username을 가져오는 메서드
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority>  getAuthorities() {
        Roles userRole = user.getRole(); // 여기서 user에 해당하는 role가져오기

        String authorityName = userRole.getRole(); // 여기서 role enum에 대한 role값 가져오기

        return Collections.singletonList(new SimpleGrantedAuthority(authorityName)); // 여기서 SimpleGrantedAuthority객체에 감싸서 보내기
    }

    @Override
    public String getPassword() { // 비밀번호 가져오기
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() { // 계정이 만료되었는지 확인
        return !user.isExpired();
    }

    @Override
    public boolean isAccountNonLocked() { // 계정이 잠금 상태인지 확인
        return !user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호가 만료되진 않았는지 확인
        return !user.isCredentialsExpired();
    }

    @Override
    public boolean isEnabled() { // 계정이 정지되진 않았는지 확인
        return user.isEnabled();
    }
}