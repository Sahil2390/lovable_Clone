package com.firstproject.lovable_clone.controller;

import com.firstproject.lovable_clone.dto.project.FileContentResponse;
import com.firstproject.lovable_clone.dto.project.FileNode;
import com.firstproject.lovable_clone.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/project/{projectId}/files")
@AllArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileNode> >getFileTree(@PathVariable Long projectId){
        Long userId =1L;

        return ResponseEntity.ok(fileService.getFileTree(projectId,userId));
    }
    @GetMapping("/*{path}")
    public ResponseEntity<FileContentResponse> getFile(@PathVariable Long projectId,@PathVariable String path){
        Long userId =1L;

        return ResponseEntity.ok(fileService.getFileContet(projectId,path,userId));
    }
}


