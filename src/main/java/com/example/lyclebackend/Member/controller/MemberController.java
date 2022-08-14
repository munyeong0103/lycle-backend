package com.example.lyclebackend.Member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class MemberController {

    @GetMapping("/test")
    public String test() {

        return "이메일 인증이 완료되었습니다";
    }

    @PostMapping("/sign-up")
    public String signUp(){
        return " adsf";
    }
}
