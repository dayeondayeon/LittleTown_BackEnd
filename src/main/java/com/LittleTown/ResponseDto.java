package com.LittleTown;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private int status;
    private String message;
    private T data;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static<T> ResponseDto<T> res(int status, String message) {
        return res(status, message, null);
    }

    public static<T> ResponseDto<T> res(int status, String message, T t) {
        return ResponseDto.<T>builder()
                .data(t)
                .status(status)
                .message(message)
                .build();
    }
}
