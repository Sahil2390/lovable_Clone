package com.firstproject.lovable_clone.dto.member;

import com.firstproject.lovable_clone.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
