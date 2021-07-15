package com.example.todolist.dto;

import com.example.todolist.exception.ErrorCode;

public class ApiResult<T> {

    private boolean success;
    private T data;
    private ErrorCode error;

    public ApiResult(boolean success, T data, ErrorCode error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(true, data, null);
    }

    public static ApiResult<Void> fail(ErrorCode errorCode) {
        return new ApiResult<>(false, null, errorCode);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }
}
