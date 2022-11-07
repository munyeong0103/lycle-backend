package com.example.lyclebackend.Quest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DeleteExpiredSuccessQuestListDto {

    private String category;

    private Integer level;

    private LocalDateTime expiredDate;
}
