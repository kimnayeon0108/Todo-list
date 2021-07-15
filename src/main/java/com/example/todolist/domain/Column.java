package com.example.todolist.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Column extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @javax.persistence.Column(name = "column_name")
    private String columnName;

    @OneToMany(mappedBy = "column", fetch = FetchType.LAZY)
    private List<Card> cards;

    protected Column() {
    }

    public Long getId() {
        return id;
    }

    public String getColumnName() {
        return columnName;
    }

    public List<Card> getCards() {
        return cards;
    }
}
