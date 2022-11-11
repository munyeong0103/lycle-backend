package com.example.lyclebackend.Item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PutItemDto {

    @NotBlank(message = "제목은 필수 입력사항 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력사항 입니다.")
    private String content;

    private Integer Price;
}
