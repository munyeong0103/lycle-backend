package com.example.lyclebackend.Nft.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class NftItemListInDto {
    private Long nftItemId;

    private String nftItemImg;

    private String nickname;

    private Long memberId;

    private String title;

    private Integer price;

    private Integer viewCnt;

    private Integer likeCnt;

    private LocalDateTime createdDate;
}
