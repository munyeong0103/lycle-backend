package com.example.lyclebackend.Member.controller;

import com.example.lyclebackend.Member.dto.LoginDto;
import com.example.lyclebackend.Member.dto.ResultDto;
import com.example.lyclebackend.Member.dto.SignUpDto;
import com.example.lyclebackend.Member.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @GetMapping("/test")
    public String test() {

        return "이메일 인증이 완료되었습니다";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){
        authService.saveMember(signUpDto);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignUpDto signUpDto){
        LoginDto loginDto = authService.login(signUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(loginDto);
    }
}
