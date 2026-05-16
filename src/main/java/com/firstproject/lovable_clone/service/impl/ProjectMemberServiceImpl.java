package com.firstproject.lovable_clone.service.impl;

import com.firstproject.lovable_clone.dto.member.InviteMemberRequest;
import com.firstproject.lovable_clone.dto.member.MemberResponse;
import com.firstproject.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.firstproject.lovable_clone.entity.Project;
import com.firstproject.lovable_clone.entity.ProjectMember;
import com.firstproject.lovable_clone.entity.ProjectMemberId;
import com.firstproject.lovable_clone.entity.User;
import com.firstproject.lovable_clone.mapper.ProjectMemberMapper;
import com.firstproject.lovable_clone.repository.ProjectMemberRepository;
import com.firstproject.lovable_clone.repository.ProjectRepository;
import com.firstproject.lovable_clone.repository.UserRepository;
import com.firstproject.lovable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectMemberMapper projectMemberMapper;
    ProjectRepository projectRepository;
    UserRepository userRepository;


    @Override
    public List<MemberResponse> getProjectMember(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        return projectMemberRepository.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .toList();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {

        Project project = getAccessibleProjectById(projectId, userId);

        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,userId);

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Cannot invite once again");
        }

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(member);

        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);


        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);

        ProjectMember member = projectMemberRepository.findById(projectMemberId).orElseThrow();

        member.setProjectRole(request.role());
        projectMemberRepository.save(member);

        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
       Project project = getAccessibleProjectById(projectId, userId);

       ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
       if(!projectMemberRepository.existsById(projectMemberId)){
           throw new RuntimeException("project member not found in project");
       }
       projectMemberRepository.deleteById(projectMemberId);

    }

    ///  INTERNAL FUNCTIONS
    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAllAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
