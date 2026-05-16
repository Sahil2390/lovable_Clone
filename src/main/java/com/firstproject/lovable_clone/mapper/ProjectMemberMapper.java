package com.firstproject.lovable_clone.mapper;


import com.firstproject.lovable_clone.dto.member.MemberResponse;
import com.firstproject.lovable_clone.entity.ProjectMember;
import com.firstproject.lovable_clone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.lang.annotation.Target;

@Mapper(componentModel="spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "role",constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner);

    @Mapping(target = "userId" ,source = "user.id")
    @Mapping(target = "username" ,source = "user.username")
    @Mapping(target = "name" ,source = "user.name")
    @Mapping(target = "role",source = "projectRole")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember member);
}
