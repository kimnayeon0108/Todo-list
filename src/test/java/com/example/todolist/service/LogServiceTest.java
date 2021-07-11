package com.example.todolist.service;

import com.example.todolist.domain.Actions;
import com.example.todolist.domain.Card;
import com.example.todolist.domain.Column;
import com.example.todolist.domain.Log;
import com.example.todolist.repository.LogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @MockBean
    private Log log;

    @MockBean
    private Card card;

    @MockBean
    private Actions action;

    @MockBean
    private Column column;

    @MockBean
    private LogRepository logRepository;

    @Test
    @DisplayName("로그 추가 기능 테스트")
    void createLog() {

        logService.createLog(card, action, column);

        verify(logRepository, times(1)).save(any(Log.class));
    }
}
