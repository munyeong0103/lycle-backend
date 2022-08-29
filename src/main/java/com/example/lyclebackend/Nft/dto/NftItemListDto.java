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
public class NftItemListDto {
    private Integer pageCnt;
    private Integer limit;
    private List<NftItemListInDto> itemList;
}
