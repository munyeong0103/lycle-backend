package com.example.lyclebackend.Member.controller;

import com.example.lyclebackend.Member.dto.SignUpDto;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.service.AuthService;
import com.example.lyclebackend.Member.service.ConfirmationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class AuthControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthService authService;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Test
    @Transactional
    @Rollback(false)
    public void email() throws Exception {
        String link = confirmationTokenService.createEmailConfirmationToken("dmswn0261@naver.com", 2);
        mvc.perform(get(link))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void joinMember() throws Exception {
        SignUpDto member = new SignUpDto();
        member.setAccountName("dmswn02611");
        member.setNickname("Gold");
        member.setPassword("123456789aA!");
        member.setEmail("dmswn0261@naver.com");
        member.setWalletAddress("sdfasdfasdfsd");
        Boolean result = authService.saveMember(member);
        assertEquals(result, true);
    }

}