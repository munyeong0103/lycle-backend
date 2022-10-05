package com.example.lyclebackend.Quest.dto;

import com.example.lyclebackend.Nft.entity.NftItem;
import com.example.lyclebackend.Quest.entity.Quest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostQuestListDto {
    private Long questId;

    private String category;

    private Integer level;

    private String needNft;

    private Integer needTimes;

    private Integer needToken;

    private Integer rewardToken;

    private LocalDateTime startDate; //json으로 변경

    private LocalDateTime finishDate;

    private Integer goal;

    public Quest toEntity(){
        Quest build = Quest.builder()
                .category(category)
                .level(level)
                .needNft(needNft)
                .needTimes(needTimes)
                .needToken(needToken)
                .rewardToken(rewardToken)
                .startDate(startDate)
                .finishDate(finishDate)
                .goal(goal)
                .build();
        return build;
    }
}
