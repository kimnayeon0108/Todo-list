package com.example.todolist.controller;

import com.example.todolist.dto.ApiResult;
import com.example.todolist.dto.ColumnResponseDTO;
import com.example.todolist.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todolist")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ApiResult<List<ColumnResponseDTO>> showProject() {
        return ApiResult.ok(projectService.getProject());
    }
}
