package com.example.todolist.exception;

public class CardNotFoundException extends BusinessException {

    public CardNotFoundException() {
        super(ErrorCode.CARD_NOT_FOUND);
    }
}
