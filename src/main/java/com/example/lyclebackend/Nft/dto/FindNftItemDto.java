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
public class FindNftItemDto {

    private Long nftItemId;

    private String nickname;

    private String nftItemImg;

    private String profileImg;

    private String title;

    private LocalDateTime createdDate;

    private Integer price;

    private Integer viewCnt;

    private Integer likeCnt;

    private String content;

    private Long sellerId;

    private Boolean isOwner;

    private Boolean isLike;


}
