package com.example.todolist.service;

import com.example.todolist.domain.Column;
import com.example.todolist.repository.ColumnRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ColumnRepository columnRepository;

    @MockBean
    private List<Column> columns;

    @Test
    @DisplayName("프로젝트 조회 기능 테스트")
    void getAll() {
        when(columnRepository.findAll()).thenReturn(columns);

        projectService.getProject();

        verify(columnRepository, times(1)).findAll();
    }
}
