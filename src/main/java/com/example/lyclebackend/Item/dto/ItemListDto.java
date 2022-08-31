package com.example.lyclebackend.Item.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemListDto {
    private Integer pageCnt;
    private Integer limit;
    private List<ItemListInDto> itemList;
}