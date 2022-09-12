package com.example.lyclebackend.Nft.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BuyNftItemDto {
    private String sellerWalletAddress;

    private String buyerWalletAddress;

    private String nftItemImg;
}
