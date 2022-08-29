package com.example.lyclebackend.Nft.dto;

import com.example.lyclebackend.Nft.entity.NftItem;
import com.example.lyclebackend.Nft.entity.NftItemLike;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NftItemLikeDto {

    private Long nftItemId;

    private Long memberId;

    public NftItemLike toEntity(){
        NftItemLike build = NftItemLike.builder()
                .nftItemId(nftItemId)
                .memberId(memberId)
                .build();
        return build;
    }
}
