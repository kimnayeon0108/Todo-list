package com.example.todolist.domain;

import javax.persistence.*;

@Entity
public class Log extends Core {

    @Id
    @GeneratedValue
    private Long id;

    private String user;

    @Enumerated(value = EnumType.STRING)
    private Actions action;

    @ManyToOne
    @JoinColumn(name = "to_column_id")
    private Column toColumn;

    @ManyToOne
    @JoinColumn(name = "from_column_id")
    private Column fromColumn;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    protected Log() {
    }
}
