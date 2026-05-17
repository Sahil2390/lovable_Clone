package com.firstproject.lovable_clone.service.impl;

import com.firstproject.lovable_clone.Error.ResourceNotFoundException;
import com.firstproject.lovable_clone.dto.auth.UserProfileResponse;
import com.firstproject.lovable_clone.entity.User;
import com.firstproject.lovable_clone.repository.UserRepository;
import com.firstproject.lovable_clone.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService, UserDetailsService {

   UserRepository userRepository;

    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User",username));
    }
}
