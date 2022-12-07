package com.example.lyclebackend.Member.controller;

import com.example.lyclebackend.Member.dto.LoginDto;
import com.example.lyclebackend.Member.dto.ResultDto;
import com.example.lyclebackend.Member.dto.SignUpDto;
import com.example.lyclebackend.Member.service.AuthService;
import com.example.lyclebackend.error.ErrorCode.LoginErrorCode;
import com.example.lyclebackend.error.Exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @GetMapping("/test")
    public String test() {

        return "장은주ddddqk바바보";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto signUpDto, Error error) throws Exception{
        authService.saveMember(signUpDto);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignUpDto signUpDto) {
        LoginDto loginDto = authService.login(signUpDto);
        if (loginDto.getMemberId() == null){
            throw new RestApiException(LoginErrorCode.FAIL_LOGIN);
        }
        return ResponseEntity.status(HttpStatus.OK).body(loginDto);
    }
}
