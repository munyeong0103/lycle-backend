package com.example.lyclebackend.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WalletAddressDto {
    @NotBlank(message = "지갑주소는 필수 입력사항 입니다.")
    private String walletAddress;
}
