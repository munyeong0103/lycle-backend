package com.example.lyclebackend.Quest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DeleteSuccessQuestListDto {

    private String category;

    private Integer level;
}
