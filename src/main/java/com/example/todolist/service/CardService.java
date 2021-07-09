package com.example.todolist.service;

import com.example.todolist.domain.Card;
import com.example.todolist.domain.Column;
import com.example.todolist.dto.CardRequestDTO;
import com.example.todolist.exception.ColumnNotFoundException;
import com.example.todolist.repository.CardRepository;
import com.example.todolist.repository.ColumnRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;

    public CardService(CardRepository cardRepository, ColumnRepository columnRepository) {
        this.cardRepository = cardRepository;
        this.columnRepository = columnRepository;
    }

    public void addCard(Long columnId, CardRequestDTO cardRequest) {
        Column column = columnRepository.findById(columnId).orElseThrow(ColumnNotFoundException::new);
        Card card = new Card(cardRequest.getTitle(), cardRequest.getContent(), cardRequest.getAuthor(), column);
        cardRepository.save(card);
    }
}
