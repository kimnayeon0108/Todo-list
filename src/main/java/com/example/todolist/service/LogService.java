package com.example.todolist.service;

import com.example.todolist.domain.Actions;
import com.example.todolist.domain.Card;
import com.example.todolist.domain.Log;
import com.example.todolist.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void createLog(Card card) {
        Log log = new Log(card.getAuthor(), Actions.ENROLL, card.getColumn(), card.getColumn(), card);
        logRepository.save(log);
    }
}
