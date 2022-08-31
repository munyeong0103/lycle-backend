package com.example.lyclebackend.Item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PutItemDto {

    private String title;

    private String content;

    private Integer Price;
}
