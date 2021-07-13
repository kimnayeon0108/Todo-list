package com.example.todolist.controller;

import com.example.todolist.dto.ApiResult;
import com.example.todolist.dto.LogResponseDTO;
import com.example.todolist.service.LogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todolist/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ApiResult<List<LogResponseDTO>> showAllLogs() {
        return ApiResult.ok(logService.getAllLogs());
    }
}
