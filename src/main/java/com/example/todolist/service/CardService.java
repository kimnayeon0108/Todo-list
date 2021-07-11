package com.example.todolist.service;

import com.example.todolist.domain.Card;
import com.example.todolist.domain.Column;
import com.example.todolist.dto.CardAddRequestDTO;
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

    public void addCard(Long columnId, CardAddRequestDTO cardRequest) {
        Column column = columnRepository.findById(columnId).orElseThrow(ColumnNotFoundException::new);
        Card card = new Card(cardRequest.getTitle(), cardRequest.getContent(), cardRequest.getAuthor(), column);
        logService.createLog(card);
        cardRepository.save(card);
    }

    public Card updateCard(Long columnId, Long cardId, CardUpdateRequestDto cardUpdateRequestDto) {
        Column column = columnRepository.findById(columnId).orElseThrow(ColumnNotFoundException::new);
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);

        card.update(cardUpdateRequestDto.getTitle(), cardUpdateRequestDto.getContent(), column);

        return cardRepository.save(card);
    }

    public void deleteCard(Long columnId, Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);

        if(!card.isSameColumnId(columnId)) {
            throw new ColumnNotMatchException();
        }

        cardRepository.delete(card);
    }
}
