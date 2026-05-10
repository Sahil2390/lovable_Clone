package com.firstproject.lovable_clone.mapper;

import com.firstproject.lovable_clone.dto.project.ProjectResponse;
import com.firstproject.lovable_clone.dto.project.ProjectSummaryResponse;
import com.firstproject.lovable_clone.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.xml.transform.Source;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toprojectResponse(Project project);


    ProjectSummaryResponse toprojectSummaryResponse(Project project);

    //@Mapping(source="name",target="projectName")
    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);

}
