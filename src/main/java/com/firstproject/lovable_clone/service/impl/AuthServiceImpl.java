package com.firstproject.lovable_clone.service.impl;

import com.firstproject.lovable_clone.dto.auth.AuthResponse;
import com.firstproject.lovable_clone.dto.auth.SigninRequest;
import com.firstproject.lovable_clone.dto.auth.SignupRequest;
import com.firstproject.lovable_clone.service.AuthService;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(SigninRequest request) {
        return null;
    }
}
