package com.example.todolist.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Column extends Core{

    @Id @GeneratedValue
    private Long id;

    private String type;

    @OneToMany(mappedBy = "column", fetch = FetchType.LAZY)
    private List<Card> cards;

    protected Column() {
    }
}
