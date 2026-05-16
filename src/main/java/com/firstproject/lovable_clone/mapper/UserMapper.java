package com.firstproject.lovable_clone.mapper;


import com.firstproject.lovable_clone.dto.auth.SignupRequest;
import com.firstproject.lovable_clone.dto.auth.UserProfileResponse;
import com.firstproject.lovable_clone.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest request);

    UserProfileResponse touserProfileResponse(User user);

}
