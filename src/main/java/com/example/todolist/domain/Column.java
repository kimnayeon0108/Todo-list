package com.example.todolist.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Column extends Core{

    @Id @GeneratedValue
    private Long id;

    private String type;

    @OneToMany(mappedBy = "column")
    private List<Card> cards;

    protected Column() {
    }
}
