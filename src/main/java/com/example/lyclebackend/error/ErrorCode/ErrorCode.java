package com.example.lyclebackend.error.ErrorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    Object FILE_SIZE_EXCEED = 20;

    String name();

    HttpStatus getHttpStatus();

    String getMessage();

}