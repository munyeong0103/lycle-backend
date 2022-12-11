package com.example.lyclebackend.Member.controller;

import com.example.lyclebackend.Member.dto.SignUpDto;
import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.service.AuthService;
import com.example.lyclebackend.Member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class AuthControllerTest {

    @Autowired
    AuthService authService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void signUp() throws Exception{

        //given
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setAccountName("hello");
        signUpDto.setPassword("123456789aA!");
        signUpDto.setNickname("hi1234");
        signUpDto.setWalletAddress("123dsfasdf");
        signUpDto.setEmail("dmswn0261@gmail.com");

        //when
        Boolean result = authService.saveMember(signUpDto);

        //then
        assertEquals(result, true);

    }


}