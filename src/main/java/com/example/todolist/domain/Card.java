package com.example.todolist.domain;

import javax.persistence.*;

@Entity
public class Card extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private Column column;

    protected Card() {
    }

    public Card(String title, String content, String author, Column column) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.column = column;
    }

    public void update(String title, String content, Column column) {
        this.title = title;
        this.content = content;
        this.column = column;
    }

    public boolean isSameColumnId(Long columnId) {
        return this.column.getId().equals(columnId);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Column getColumn() {
        return column;
    }
}
