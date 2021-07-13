package com.example.todolist.controller;

import com.example.todolist.dto.ApiResult;
import com.example.todolist.dto.CardAddRequestDTO;
import com.example.todolist.dto.CardResponseDTO;
import com.example.todolist.dto.CardUpdateRequestDto;
import com.example.todolist.service.CardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todolist/columns")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/{columnId}/cards")
    public ApiResult<CardResponseDTO> addCard(@PathVariable Long columnId, @RequestBody CardAddRequestDTO requestDTO) {
        return ApiResult.ok(cardService.addCard(columnId, requestDTO));
    }

    @PutMapping("/cards/{cardId}")
    public ApiResult<CardResponseDTO> updateCard(@PathVariable Long cardId, @RequestBody CardUpdateRequestDto requestDto) {
        return ApiResult.ok(cardService.updateCard(cardId, requestDto));
    }

    @DeleteMapping("/{columnId}/cards/{cardId}")
    public ApiResult<Void> deleteCard(@PathVariable Long columnId, @PathVariable Long cardId) {
        cardService.deleteCard(columnId, cardId);
        return ApiResult.ok(null);
    }
}
