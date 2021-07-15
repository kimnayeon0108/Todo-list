package com.example.todolist.exception;

public enum ErrorCode {

    CARD_NOT_FOUND(500, "card not found!!!"),
    COLUMN_NOT_FOUND(500, "column not found!!!!"),
    COLUMN_NOT_MATCH(400, "column not match!!!");

    private final Integer errorCode;
    private final String message;

    ErrorCode(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
