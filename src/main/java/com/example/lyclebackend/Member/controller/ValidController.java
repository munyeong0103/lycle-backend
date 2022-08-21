package com.example.lyclebackend.Member.controller;

import com.example.lyclebackend.Member.dto.*;
import com.example.lyclebackend.Member.service.ConfirmationTokenService;
import com.example.lyclebackend.Member.service.ValidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valid")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class ValidController {

    private final ValidService validService;

    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping("accountName/exists")
    public ResponseEntity<?> existsAccountName(@RequestBody AccountNameDto accountNameDto) {
        ResultDto result = new ResultDto();
        result.setResult(validService.existsAccountName(accountNameDto));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("nickname/exists")
    public ResponseEntity<?> existsNickname(@RequestBody NicknameDto nicknameDto) {
        ResultDto result = new ResultDto();
        result.setResult(validService.existsNickname(nicknameDto));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("email/send")
    public ResponseEntity<?> emailSend(@RequestBody EmailDto emailDto) {
        ResultDto result = new ResultDto();
        if(!validService.existsEmail(emailDto)) {
            confirmationTokenService.createEmailConfirmationToken(emailDto.getEmail());
            result.setResult(true);
        } else {
            result.setResult(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("email/confirm")
    public String confirmEmail(@RequestParam String token) {
        validService.confirmEmail(token);
        return "이메일 인증이 완료되었습니다";
    }

    @PostMapping("email/check")
    public ResponseEntity<?> checkEmail(@RequestBody EmailDto emailDto) {
        ResultDto result = new ResultDto();
        if(validService.checkEmail(emailDto)) {
            result.setResult(true);
        } else {
            result.setResult(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("walletAddress/exists")
    public ResponseEntity<?> checkWalletAddress(@RequestBody WalletAddressDto walletAddressDto) {
        ResultDto result = new ResultDto();
        if(validService.existsWalletAddress(walletAddressDto)) {
            result.setResult(true);
        } else {
            result.setResult(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
