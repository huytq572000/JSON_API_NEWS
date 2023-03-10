package com.example.bt2vnpt.reponse;


import org.springframework.http.HttpStatus;


public class CustomException extends RuntimeException {
    private final String code;
    private final HttpStatus status;

    public CustomException(HttpStatus status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public CustomException(String code, String message) {
        super(message);
        this.status = HttpStatus.OK;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
