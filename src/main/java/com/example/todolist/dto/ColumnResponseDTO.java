package com.example.todolist.dto;

import java.util.List;

public class ColumnResponseDTO {

    private Long id;
    private String columnName;
    private List<CardProjectResponseDTO> cards;

    public ColumnResponseDTO(Long id, String columnName, List<CardProjectResponseDTO> cardDTOS) {
        this.id = id;
        this.columnName = columnName;
        this.cards = cardDTOS;
    }

    public Long getId() {
        return id;
    }

    public String getColumnName() {
        return columnName;
    }

    public List<CardProjectResponseDTO> getCards() {
        return cards;
    }
}
