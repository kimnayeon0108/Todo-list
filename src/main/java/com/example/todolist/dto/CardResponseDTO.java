package com.example.todolist.dto;

public class CardResponseDTO {

    private Long id;
    private Long columnId;
    private String title;
    private String content;
    private String author;

    public CardResponseDTO(Long id, Long columnId, String title, String content, String author) {
        this.id = id;
        this.columnId = columnId;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
