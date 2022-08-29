package com.example.lyclebackend.Nft.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FindPostInfoDto {
    private String nickname;

    private String profileImg;

    private List<NftImgDto> nftImgDtoList;
}
