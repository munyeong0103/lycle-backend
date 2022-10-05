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
public class PutQuestListDto {

    private String category;

    private Integer level;

    private String needNft;

    private Integer needTimes;

    private Integer needToken;

    private Integer rewardToken;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    private Integer goal;

    private Integer times;
}