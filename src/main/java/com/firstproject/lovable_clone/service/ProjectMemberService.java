package com.firstproject.lovable_clone.service;

import com.firstproject.lovable_clone.dto.member.InviteMemberRequest;
import com.firstproject.lovable_clone.dto.member.MemberResponse;
import com.firstproject.lovable_clone.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {

    public List<MemberResponse> getProjectMember(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);


    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);


    void removeProjectMember(Long projectId, Long memberId);
}
