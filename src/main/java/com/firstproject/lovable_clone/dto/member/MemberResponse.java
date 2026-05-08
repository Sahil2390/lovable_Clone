package com.firstproject.lovable_clone.dto.member;

import com.firstproject.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String email,
        String name,
        String avatarUrl,
        Instant invitedAt,
        ProjectRole role
) {
}
