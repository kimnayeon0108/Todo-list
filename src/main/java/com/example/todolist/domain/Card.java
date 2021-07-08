package com.example.todolist.domain;

import javax.persistence.*;

@Entity
public class Card extends Core {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String author;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private Column column;

    protected Card() {
    }
}
