package com.example.lyclebackend.Member.service;

import com.example.lyclebackend.Member.dto.EmailDto;
import com.example.lyclebackend.Member.dto.LoginDto;
import com.example.lyclebackend.Member.dto.SignUpDto;
import com.example.lyclebackend.Member.entity.ConfirmationToken;
import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.util.JwtUtil;
import com.example.lyclebackend.Member.util.SaltUtil;
import com.example.lyclebackend.error.ErrorCode.LoginErrorCode;
import com.example.lyclebackend.error.ErrorCode.SignUpErrorCode;
import com.example.lyclebackend.error.Exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuthService {

    private final SaltUtil saltUtil;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;
    private final ValidService validService;

    @Transactional
    public boolean saveMember(SignUpDto signUpDto) {

        if(memberRepository.existsByAccountName(signUpDto.getAccountName())){
            throw new RestApiException(SignUpErrorCode.FAIL_SIGNUP_ID);
        }

        if(memberRepository.existsByNickname(signUpDto.getNickname())){
            throw new RestApiException(SignUpErrorCode.FAIL_SIGNUP_NICKNAME);
        }

        if(memberRepository.existsByWalletAddress(signUpDto.getWalletAddress())){
            throw new RestApiException(SignUpErrorCode.FAIL_SIGNUP_WALLET_ADDRESS);
        }

        EmailDto emailDto = new EmailDto();
        emailDto.setEmail(signUpDto.getEmail());
        if(validService.checkEmail(emailDto)) {
            throw new RestApiException(SignUpErrorCode.FAIL_SIGNUP_EMAIL);
        }

        String salt = saltUtil.genSalt();
        signUpDto.setSalt(salt);
        signUpDto.setPassword(saltUtil.encodePassword(salt, signUpDto.getPassword()));
        signUpDto.setTokenCnt(0);
        Member member = signUpDto.toEntity();


        memberRepository.save(member);
        return true;
    }

    public LoginDto login(SignUpDto signUpDto){
        Member member = memberRepository.findByAccountName(signUpDto.getAccountName());
        String salt = member.getSalt();
        String encodePassword = saltUtil.encodePassword(salt, signUpDto.getPassword());
        LoginDto loginDto = new LoginDto();
        if (member.getPassword().equals(encodePassword)) {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(signUpDto.getAccountName());
            final String jwt = jwtUtil.generateToken(userDetails);

            loginDto.setAccessToken(jwt);
            loginDto.setMemberId(member.getMemberId());

            return loginDto;
        }
        return loginDto;

    }
}
