package com.example.lyclebackend.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmailDto {
    @NotBlank(message = "이메일은 필수 입력사항 입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
}
