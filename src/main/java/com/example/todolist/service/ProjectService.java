package com.example.todolist.service;

import com.example.todolist.dto.CardProjectResponseDTO;
import com.example.todolist.dto.ColumnResponseDTO;
import com.example.todolist.repository.ColumnRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ColumnRepository columnRepository;

    public ProjectService(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public List<ColumnResponseDTO> getProject() {
        return columnRepository.findAll().stream()
                .map(column -> {
                    List<CardProjectResponseDTO> cardDTOS = column.getCards().stream()
                            .map(card -> new CardProjectResponseDTO(card.getId(), card.getTitle(), card.getContent(), card.getAuthor()))
                            .collect(Collectors.toList());
                    return new ColumnResponseDTO(column.getId(), column.getColumnName(), cardDTOS);
                }).collect(Collectors.toList());
    }
}
