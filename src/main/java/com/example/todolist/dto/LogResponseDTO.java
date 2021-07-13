package com.example.todolist.dto;

import com.example.todolist.domain.Actions;

import java.time.LocalDateTime;

public class LogResponseDTO {

    private Long id;
    private String user;
    private Actions action;
    private String toColumnName;
    private String fromColumnName;
    private LogCardResponseDTO card;
    private LocalDateTime createdTime;

    public LogResponseDTO(Long id, String user, Actions action, String toColumnName, String fromColumnName, LogCardResponseDTO card, LocalDateTime createdTime) {
        this.id = id;
        this.user = user;
        this.action = action;
        this.toColumnName = toColumnName;
        this.fromColumnName = fromColumnName;
        this.card = card;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public Actions getAction() {
        return action;
    }

    public String getToColumnName() {
        return toColumnName;
    }

    public String getFromColumnName() {
        return fromColumnName;
    }

    public LogCardResponseDTO getCard() {
        return card;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
