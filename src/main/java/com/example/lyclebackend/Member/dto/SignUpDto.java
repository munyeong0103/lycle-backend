package com.example.lyclebackend.Member.dto;

import com.example.lyclebackend.Member.entity.Member;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignUpDto {


    private String accountName;

    private String password;

    private String nickname;

    private String walletAddress;

    private String salt;

    private String email;

    public Member toEntity(){
        Member build = Member.builder()
                .accountName(accountName)
                .password(password)
                .nickname(nickname)
                .walletAddress(walletAddress)
                .salt(salt)
                .email(email)
                .build();
        return build;
    }
}
