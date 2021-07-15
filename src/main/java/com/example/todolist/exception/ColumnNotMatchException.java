package com.example.todolist.exception;

public class ColumnNotMatchException extends BusinessException {

    public ColumnNotMatchException() {
        super(ErrorCode.COLUMN_NOT_MATCH);
    }
}
