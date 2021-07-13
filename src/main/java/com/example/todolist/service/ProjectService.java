package com.example.todolist.service;

import com.example.todolist.domain.Column;
import com.example.todolist.repository.ColumnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ColumnRepository columnRepository;

    public ProjectService(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public List<Column> getProject() {
        return columnRepository.findAll();
    }
}
