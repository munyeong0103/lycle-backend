package com.example.lyclebackend.Nft.dto;

import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Nft.entity.NftItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostNftItemDto {
    private Long nftId;

    private String nftItemImg;

    private String title;

    private Integer price;

    private String content;

    private Integer viewCnt;

    private Long sellerId;

    public NftItem toEntity(){
        NftItem build = NftItem.builder()
                .nftId(nftId)
                .nftItemImg(nftItemImg)
                .title(title)
                .price(price)
                .content(content)
                .viewCnt(viewCnt)
                .sellerId(sellerId)
                .build();
        return build;
    }
}
