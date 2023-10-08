package com.pe.hampcode.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;
    private long timestamp;
    private List<String> validationErrors;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = new Date().getTime();
    }
}
