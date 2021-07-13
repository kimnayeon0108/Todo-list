package com.example.todolist.service;

import com.example.todolist.domain.Actions;
import com.example.todolist.domain.Card;
import com.example.todolist.domain.Column;
import com.example.todolist.dto.CardAddRequestDTO;
import com.example.todolist.dto.CardResponseDTO;
import com.example.todolist.dto.CardUpdateRequestDto;
import com.example.todolist.exception.CardNotFoundException;
import com.example.todolist.exception.ColumnNotFoundException;
import com.example.todolist.exception.ColumnNotMatchException;
import com.example.todolist.repository.CardRepository;
import com.example.todolist.repository.ColumnRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;
    private final LogService logService;

    public CardService(CardRepository cardRepository, ColumnRepository columnRepository, LogService logService) {
        this.cardRepository = cardRepository;
        this.columnRepository = columnRepository;
        this.logService = logService;
    }

    public CardResponseDTO addCard(Long columnId, CardAddRequestDTO cardRequest) {
        Column column = columnRepository.findById(columnId).orElseThrow(ColumnNotFoundException::new);
        Card card = new Card(cardRequest.getTitle(), cardRequest.getContent(), cardRequest.getAuthor(), column);
        Card savedCard = cardRepository.save(card);
        logService.createLog(savedCard, Actions.ENROLL, column);
        return new CardResponseDTO(savedCard.getId(), columnId, savedCard.getTitle(), savedCard.getContent(), savedCard.getAuthor());
    }

    public CardResponseDTO updateCard(Long columnId, Long cardId, CardUpdateRequestDto cardUpdateRequestDto) {
        Column fromColumn = columnRepository.findById(columnId).orElseThrow(ColumnNotFoundException::new);
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        Column toColumn = columnRepository.findById(cardUpdateRequestDto.getToColumnId()).orElseThrow(ColumnNotFoundException::new);

        card.update(cardUpdateRequestDto.getTitle(), cardUpdateRequestDto.getContent(), toColumn);
        Card savedCard = cardRepository.save(card);

        logService.createLog(card, getActionType(card, cardUpdateRequestDto.getToColumnId()), toColumn);

        return new CardResponseDTO(cardId, cardUpdateRequestDto.getToColumnId(), savedCard.getTitle(), savedCard.getContent(), savedCard.getAuthor());
    }

    public Actions getActionType(Long fromColumnId, Long toColumnId) {
        if (fromColumnId.equals(toColumnId)) {
            return Actions.UPDATE;
        }
        return Actions.MOVE;
    }

    public void deleteCard(Long columnId, Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        Column column = columnRepository.findById(columnId).orElseThrow(ColumnNotFoundException::new);

        if (!card.isSameColumnId(columnId)) {
            throw new ColumnNotMatchException();
        }

        logService.createLog(card, Actions.DELETE, column);
        cardRepository.delete(card);
    }
}
