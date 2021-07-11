package com.example.todolist.service;

import com.example.todolist.domain.Actions;
import com.example.todolist.domain.Card;
import com.example.todolist.domain.Column;
import com.example.todolist.dto.CardAddRequestDTO;
import com.example.todolist.dto.CardUpdateRequestDto;
import com.example.todolist.exception.CardNotFoundException;
import com.example.todolist.exception.ColumnNotFoundException;
import com.example.todolist.exception.ColumnNotMatchException;
import com.example.todolist.repository.CardRepository;
import com.example.todolist.repository.ColumnRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    private Card card;

    @MockBean
    private CardAddRequestDTO cardAddRequestDTO;

    @MockBean
    private CardUpdateRequestDto cardUpdateRequestDto;

    @Autowired
    private CardService cardService;

    @MockBean
    private LogService logService;

    @Test
    @DisplayName("카드 추가 기능 테스트")
    void addCard() {
        Long columnId = 1L;

        when(columnRepository.findById(anyLong())).thenReturn(Optional.of(column));

        cardService.addCard(columnId, cardAddRequestDTO);

        verify(logService, times(1)).createLog(any(Card.class), any(Actions.class), any(Column.class));
        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    @DisplayName("컬럼이 없을 시 ColumnNotFoundException 발생 테스트")
    void throwColumnNotFoundExceptionIfNotExistColumn() {
        Long columnId = 1L;

        when(columnRepository.findById(anyLong())).thenThrow(ColumnNotFoundException.class);

        assertThrows(ColumnNotFoundException.class, () -> cardService.addCard(columnId, cardAddRequestDTO));

        verify(logService, times(0)).createLog(any(Card.class), any(Actions.class), any(Column.class));
        verify(cardRepository, times(0)).save(any(Card.class));
    }

    @Test
    @DisplayName("카드 수정 기능 테스트")
    void updateCard() {
        when(columnRepository.findById(anyLong())).thenReturn(Optional.of(column));
        when(cardRepository.findById(anyLong())).thenReturn(Optional.of(card)); // 수정할 카드
        when(cardUpdateRequestDto.getTitle()).thenReturn("");
        when(cardUpdateRequestDto.getContent()).thenReturn("");

        cardService.updateCard(1L, 1L, cardUpdateRequestDto);

        verify(card, times(1)).update(anyString(), anyString(), any(Column.class));
        verify(logService, times(1)).createLog(any(Card.class), any(Actions.class), any(Column.class));
        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    @DisplayName("카드 수정 시 이동하는 카드인지 확인하는 기능 테스트")
    void getActionType() {
        Long columnId = 1L;

        cardService.getActionType(card, columnId);

        verify(card, times(1)).isSameColumnId(columnId);
    }

    @Test
    @DisplayName("카드 수정할 때 카드가 없을 시 CardNotFoundException 발생")
    void throwExceptionIfNotExistCard() {
        when(columnRepository.findById(anyLong())).thenReturn(Optional.of(column));
        when(cardRepository.findById(anyLong())).thenThrow(CardNotFoundException.class);

        assertThrows(CardNotFoundException.class, () -> cardService.updateCard(1L, 1L, cardUpdateRequestDto));

        verify(logService, times(0)).createLog(any(Card.class), any(Actions.class), any(Column.class));
        verify(cardRepository, times(0)).save(any(Card.class));
    }

    @Test
    @DisplayName("카드 삭제 기능 테스트")
    void deleteCard() {
        when(columnRepository.findById(anyLong())).thenReturn(Optional.of(column));
        when(cardRepository.findById(anyLong())).thenReturn(Optional.of(card));
        when(card.isSameColumnId(anyLong())).thenReturn(true);

        cardService.deleteCard(1L, 1L);

        verify(logService, times(1)).createLog(any(Card.class), any(Actions.class), any(Column.class));
        verify(cardRepository, times(1)).delete(any(Card.class));
    }

    @Test
    @DisplayName("컬럼 id가 카드의 컬럼 id와 다를 때 ColumnNotMatchException 발생")
    void throwExceptionIfColumnNotMatch() {
        when(columnRepository.findById(anyLong())).thenReturn(Optional.of(column));
        when(cardRepository.findById(anyLong())).thenReturn(Optional.of(card));
        when(card.isSameColumnId(anyLong())).thenReturn(false);

        assertThrows(ColumnNotMatchException.class, () -> cardService.deleteCard(1L, 1L));

        verify(cardRepository, times(0)).delete(any(Card.class));
    }
}
