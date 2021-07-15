package com.example.todolist.service;

import com.example.todolist.domain.Actions;
import com.example.todolist.domain.Card;
import com.example.todolist.domain.Column;
import com.example.todolist.domain.Log;
import com.example.todolist.dto.LogCardResponseDTO;
import com.example.todolist.dto.LogResponseDTO;
import com.example.todolist.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void createLog(Card card, Actions action, Column toColumn) {
        Log log = new Log(card.getAuthor(), action, toColumn, card.getColumn(), card);
        logRepository.save(log);
    }

    public void updateCardLog(Card card, Actions action, Column fromColumn, Column toColumn) {
        Log log = new Log(card.getAuthor(), action, toColumn, fromColumn, card);
        logRepository.save(log);
    }

    public List<LogResponseDTO> getAllLogs() {
        return logRepository.findAll().stream()
                .map(log -> {
                    LogCardResponseDTO logCardDTO= new LogCardResponseDTO(log.getCard().getId(), log.getCard().getTitle());
                    return new LogResponseDTO(log.getId(), log.getUser(), log.getAction(), log.getToColumn().getColumnName(),
                            log.getFromColumn().getColumnName(), logCardDTO, log.getCreatedTime());
                })
                .collect(Collectors.toList());
    }
}
