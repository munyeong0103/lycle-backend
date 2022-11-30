package com.example.lyclebackend.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PutMyPageErrorCode implements ErrorCode{
    FAIL_PUT_PAGE_NICKNAME(HttpStatus.CONFLICT, "닉네임은 특수문자를 제외한 4~10자리여야 합니다."),
    FAIL_PUT_PAGE_PASSWORD(HttpStatus.CONFLICT, "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
