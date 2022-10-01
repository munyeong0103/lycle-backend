package com.example.lyclebackend.Nft.dto;

import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Nft.entity.NftItem;
import com.example.lyclebackend.Nft.entity.NftItemStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostNftItemDto {

    private String nftItemImg;

    private String title;

    private Integer price;

    private String content;

    private Integer viewCnt;

    private Long sellerId;

    private NftItemStatus status;

    private Boolean isDelete;

    public NftItem toEntity(){
        NftItem build = NftItem.builder()
                .nftItemImg(nftItemImg)
                .title(title)
                .price(price)
                .content(content)
                .viewCnt(viewCnt)
                .sellerId(sellerId)
                .status(status)
                .isDelete(isDelete)
                .build();
        return build;
    }
}
