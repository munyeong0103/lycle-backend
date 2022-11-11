package com.example.lyclebackend.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LoginErrorCode implements ErrorCode {

    FAIL_LOGIN(HttpStatus.FORBIDDEN, "아이디 혹은 비밀번호가 잘못되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
