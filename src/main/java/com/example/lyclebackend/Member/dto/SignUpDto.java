package com.example.lyclebackend.Member.dto;

import com.example.lyclebackend.Member.entity.Member;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignUpDto {

    private String nickname;

    private String walletAddress;

    private String salt;

    public Member toEntity(){
        Member build = Member.builder()
                .nickname(nickname)
                .walletAddress(walletAddress)
                .salt(salt)
                .build();
        return build;
    }
}
