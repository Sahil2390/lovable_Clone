package com.firstproject.lovable_clone.service;

import com.firstproject.lovable_clone.dto.auth.UserProfileResponse;

public interface UserService {

    UserProfileResponse getProfile(Long userId);
}
