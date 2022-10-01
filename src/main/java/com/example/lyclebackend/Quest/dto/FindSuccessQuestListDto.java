package com.example.lyclebackend.Quest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.description.field.FieldDescription;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FindSuccessQuestListDto {

    private Long successQuestId;

    private String category;

    private Integer level;

    private String needNft;

    private Integer needTime;

    private Integer needToken;

    private Integer rewardToken;

    private Integer goal;

    private Duration expiredDate;
}
