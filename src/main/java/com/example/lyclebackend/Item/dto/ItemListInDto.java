package com.example.lyclebackend.Item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemListInDto {
    private Long itemId;

    private String itemImg;

    private String title;

    private Integer price;

    private Integer viewCnt;

    private LocalDateTime createdDate;
}