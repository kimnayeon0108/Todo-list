package com.example.todolist.dto;

public class LogCardResponseDTO {

    private Long id;
    private String title;

    public LogCardResponseDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
