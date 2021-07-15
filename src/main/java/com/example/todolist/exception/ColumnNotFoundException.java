package com.example.todolist.exception;

public class ColumnNotFoundException extends BusinessException {

    public ColumnNotFoundException() {
        super(ErrorCode.COLUMN_NOT_FOUND);
    }
}
