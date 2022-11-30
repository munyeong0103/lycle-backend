package com.example.lyclebackend.Member.dto;

import com.example.lyclebackend.Member.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignUpDto {

    @NotBlank(message = "아이디는 필수 입력사항 입니다.")
    private String accountName;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
    private String password;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{4,10}$", message = "닉네임은 특수문자를 제외한 4~10자리여야 합니다.")
    @NotBlank(message = "닉네임은 필수 입력사항 입니다.")
    private String nickname;

    @NotBlank(message = "지갑주소는 필수 입력사항 입니다.")
    private String walletAddress;

    private String salt;

    @NotBlank(message = "이메일은 필수 입력사항 입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    private String profileImg;

    private Integer tokenCnt;

    public Member toEntity(){
        Member build = Member.builder()
                .accountName(accountName)
                .password(password)
                .nickname(nickname)
                .walletAddress(walletAddress)
                .salt(salt)
                .email(email)
                .profileImg(profileImg)
                .tokenCnt(tokenCnt)
                .build();
        return build;
    }
}
