package com.firstproject.lovable_clone.service.impl;

import com.firstproject.lovable_clone.Error.BadRequestException;
import com.firstproject.lovable_clone.dto.auth.AuthResponse;
import com.firstproject.lovable_clone.dto.auth.SigninRequest;
import com.firstproject.lovable_clone.dto.auth.SignupRequest;
import com.firstproject.lovable_clone.entity.User;
import com.firstproject.lovable_clone.mapper.UserMapper;
import com.firstproject.lovable_clone.repository.UserRepository;
import com.firstproject.lovable_clone.security.AuthUtil;
import com.firstproject.lovable_clone.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user->{
            throw new BadRequestException("user already exists with username" +request.username());
        });

        User user =userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        String token = authUtil.generateAccessToken(user);

        return new AuthResponse(token,userMapper.touserProfileResponse(user));
    }

    @Override
    public AuthResponse login(SigninRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(),request.password())
        );

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return new AuthResponse(token,userMapper.touserProfileResponse(user));
    }
}
