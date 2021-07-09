package com.example.todolist.service;

import com.example.todolist.domain.Card;
import com.example.todolist.domain.Column;
import com.example.todolist.dto.CardRequestDTO;
import com.example.todolist.exception.ColumnNotFoundException;
import com.example.todolist.repository.CardRepository;
import com.example.todolist.repository.ColumnRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CardServiceTest {

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private ColumnRepository columnRepository;

    @MockBean
    private Column column;

    @MockBean
    private CardRequestDTO cardRequest;

    @Autowired
    private CardService cardService;

    @Test
    @DisplayName("카드 추가 기능 테스트")
    void addCard() {
        Long columnId = 1L;

        when(columnRepository.findById(anyLong())).thenReturn(Optional.of(column));

        cardService.addCard(columnId, cardRequest);

        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    @DisplayName("컬럼이 없을 시 ColumnNotFoundException 발생 테스트")
    void addCardColumnNotFoundException() {
        Long columnId = 1L;

        when(columnRepository.findById(anyLong())).thenThrow(ColumnNotFoundException.class);

        Assertions.assertThrows(ColumnNotFoundException.class, () -> cardService.addCard(columnId, cardRequest));

        verify(cardRepository, times(0)).save(any(Card.class));
    }
}
