package com.example.lyclebackend.Nft.dto;

import com.example.lyclebackend.Nft.entity.NftItemStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Long nftId;

    private String collectionName;

    private String nftItemImg;

    private String profileImg;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    private Integer price;

    private Integer viewCnt;

    private Integer likeCnt;

    private String content;

    private Long sellerId;

    private Boolean isOwner;

    private Boolean isLike;

    private Boolean isDelete;

    private NftItemStatus status;
}
