package com.firstproject.lovable_clone.service.impl;

import com.firstproject.lovable_clone.Error.ResourceNotFoundException;
import com.firstproject.lovable_clone.dto.project.ProjectRequest;
import com.firstproject.lovable_clone.dto.project.ProjectResponse;
import com.firstproject.lovable_clone.dto.project.ProjectSummaryResponse;
import com.firstproject.lovable_clone.entity.Project;
import com.firstproject.lovable_clone.entity.ProjectMember;
import com.firstproject.lovable_clone.entity.ProjectMemberId;
import com.firstproject.lovable_clone.entity.User;
import com.firstproject.lovable_clone.enums.ProjectRole;
import com.firstproject.lovable_clone.mapper.ProjectMapper;
import com.firstproject.lovable_clone.repository.ProjectMemberRepository;
import com.firstproject.lovable_clone.repository.ProjectRepository;
import com.firstproject.lovable_clone.repository.UserRepository;
import com.firstproject.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

   ProjectRepository projectRepository;
   UserRepository userRepository;
   ProjectMapper projectMapper;
   ProjectMemberRepository projectMemberRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("user",userId.toString())
        );

       Project project = Project.builder()
               .name(request.name())
               .isPublic(false)
               .build();

       project =projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(),owner.getId());

        ProjectMember projectMember =  ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .projectRole(ProjectRole.OWNER)
                .build();

        projectMemberRepository.save(projectMember);
        return projectMapper.toprojectResponse(projectMember.getProject());
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }



    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id,userId);
        return projectMapper.toprojectResponse(project);
    }



    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(id,userId);

        project.setName(request.name());
        project = projectRepository.save(project);

        return projectMapper.toprojectResponse(project);


    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id,userId);


        project.setDeletedAt(Instant.now());
        projectRepository.save(project);

    }

    ///  INTERNAL FUNCTIONS
    public Project getAccessibleProjectById(Long projectId, Long userId){
        return projectRepository.findAllAccessibleProjectById(projectId,userId)
                .orElseThrow(()-> new ResourceNotFoundException("project",projectId.toString()));
    }
}
