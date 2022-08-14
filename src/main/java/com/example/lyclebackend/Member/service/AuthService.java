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

    public String randomNickname(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    @Transactional
    public boolean saveMember(String walletAddress) {
        String salt = saltUtil.genSalt();
        String nickname = randomNickname();

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setNickname(nickname);
        signUpDto.setSalt(salt);
//        signUpDto.setWalletAddress(saltUtil.encodePassword(salt, walletAddress));
        signUpDto.setWalletAddress(walletAddress);

        Member member = signUpDto.toEntity();
        memberRepository.save(member);
        return true;
    }

    public String login(String walletAddress){
        Member member = memberRepository.findByWalletAddress(walletAddress);
//        String salt = member.getSalt();
//        String encodeWalletAddress = saltUtil.encodePassword(salt, walletAddress);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(walletAddress);
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }
}
