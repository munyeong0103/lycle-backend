package com.example.lyclebackend.Quest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.description.field.FieldDescription;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FindQuestListDto {

    private Long questId;

    private String category;

    private Integer level;

    private Integer needToken;

    private Integer rewardToken;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    private Integer goal;

    private Integer times;

    private String nftNeed;
}
