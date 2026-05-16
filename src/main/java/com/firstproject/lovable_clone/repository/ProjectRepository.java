package com.firstproject.lovable_clone.repository;

import com.firstproject.lovable_clone.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

 @Query("""
         Select p from Project p
          where p.deletedAt IS NULL
          ORDER BY p.updatedAt DESC
""")
 List<Project> findAllAccessibleByUser(@Param("userId") Long userId);

@Query("""
      Select p from Project p
      where p.id =:projectId
      AND p.deletedAt IS NULL
""")
 Optional<Project> findAllAccessibleProjectById(@Param("projectId") Long projectId,  @Param("userId") Long userId);

}
