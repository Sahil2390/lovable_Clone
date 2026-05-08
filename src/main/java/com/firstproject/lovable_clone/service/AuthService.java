package com.firstproject.lovable_clone.service;

import com.firstproject.lovable_clone.dto.auth.AuthResponse;
import com.firstproject.lovable_clone.dto.auth.SigninRequest;
import com.firstproject.lovable_clone.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(SigninRequest request);
}
