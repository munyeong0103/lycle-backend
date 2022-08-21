package com.example.lyclebackend.Member.service;

import com.example.lyclebackend.Member.dto.AccountNameDto;
import com.example.lyclebackend.Member.dto.EmailDto;
import com.example.lyclebackend.Member.dto.NicknameDto;
import com.example.lyclebackend.Member.dto.WalletAddressDto;
import com.example.lyclebackend.Member.entity.ConfirmationToken;
import com.example.lyclebackend.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ValidService {

    private final MemberRepository memberRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @Transactional
    public boolean existsAccountName(AccountNameDto accountNameDto) {
        return memberRepository.existsByAccountName(accountNameDto.getAccountName());
    }

    @Transactional
    public boolean existsNickname(NicknameDto nicknameDto) {
        return memberRepository.existsByNickname(nicknameDto.getNickname());
    }

    @Transactional
    public boolean existsEmail(EmailDto emailDto) {
        return memberRepository.existsByEmail(emailDto.getEmail());
    }

    @Transactional
    public void confirmEmail(String token) {
        ConfirmationToken findConfirmationToken = confirmationTokenService.findByIdAndExpirationDateAfterAndExpired(token);
        findConfirmationToken.useToken();	// expired 값 & certification 값을 true로 변경
    }

    @Transactional
    public boolean checkEmail(EmailDto emailDto) {
        List<ConfirmationToken> findConfirmationToken = confirmationTokenService.findAllByReceiverEmail(emailDto.getEmail());

        AtomicBoolean check = new AtomicBoolean(false);
        findConfirmationToken.stream().filter(s->s.isCertification()).forEach((s)->check.set(true));
        return check.get();
    }

    @Transactional
    public boolean existsWalletAddress(WalletAddressDto walletAddressDto) {
        return memberRepository.existsByWalletAddress(walletAddressDto.getWalletAddress());
    }

}
