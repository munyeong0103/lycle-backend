package com.example.lyclebackend.Member.service;

import com.example.lyclebackend.Member.dto.SignUpDto;
import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.util.JwtUtil;
import com.example.lyclebackend.Member.util.SaltUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuthService {

    private final SaltUtil saltUtil;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;

    @Transactional
    public boolean saveMember(SignUpDto signUpDto) {
        String salt = saltUtil.genSalt();
        signUpDto.setSalt(salt);
        signUpDto.setPassword(saltUtil.encodePassword(salt, signUpDto.getPassword()));

        Member member = signUpDto.toEntity();
        memberRepository.save(member);
        return true;
    }

    public String login(SignUpDto signUpDto){
        Member member = memberRepository.findByAccountName(signUpDto.getAccountName());
        String salt = member.getSalt();
        String encodePassword = saltUtil.encodePassword(salt, signUpDto.getPassword());
        if (member.getPassword().equals(encodePassword)) {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(signUpDto.getAccountName());
            final String jwt = jwtUtil.generateToken(userDetails);

            return jwt;
        }
        return "f";

    }
}
