package com.example.lyclebackend.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SignUpErrorCode implements ErrorCode {

    FAIL_SIGNUP_ID(HttpStatus.CONFLICT, "아이디가 중복입니다."),
    FAIL_SIGNUP_NICKNAME(HttpStatus.CONFLICT, "닉네임이 중복입니다."),
    FAIL_SIGNUP_WALLET_ADDRESS(HttpStatus.CONFLICT, "지갑주소가 중복입니다."),
    FAIL_SIGNUP_EMAIL(HttpStatus.CONFLICT, "이메일이 중복입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
