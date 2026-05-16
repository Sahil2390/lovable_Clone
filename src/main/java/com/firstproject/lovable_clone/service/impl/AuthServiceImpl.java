package com.firstproject.lovable_clone.service.impl;

import com.firstproject.lovable_clone.Error.BadRequestException;
import com.firstproject.lovable_clone.dto.auth.AuthResponse;
import com.firstproject.lovable_clone.dto.auth.SigninRequest;
import com.firstproject.lovable_clone.dto.auth.SignupRequest;
import com.firstproject.lovable_clone.entity.User;
import com.firstproject.lovable_clone.mapper.UserMapper;
import com.firstproject.lovable_clone.repository.UserRepository;
import com.firstproject.lovable_clone.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user->{
            throw new BadRequestException("user already exists with username" +request.username());
        });

        User user =userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        return new AuthResponse("dummy",userMapper.touserProfileResponse(user));
    }

    @Override
    public AuthResponse login(SigninRequest request) {
        return null;
    }
}
