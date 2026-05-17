package com.firstproject.lovable_clone.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


public record JwtUserPrincipal(
        String username,
        Long userId,
        List<GrantedAuthority> authorities


) {
}
