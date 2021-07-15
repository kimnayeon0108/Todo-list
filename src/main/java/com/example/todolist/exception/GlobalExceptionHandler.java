package com.example.todolist.exception;

import com.example.todolist.dto.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResult<Void> businessException(BusinessException e) {
        return ApiResult.fail(e.getErrorCode());
    }
}
