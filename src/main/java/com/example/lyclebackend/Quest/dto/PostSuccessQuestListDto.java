package com.example.lyclebackend.Quest.dto;

import com.example.lyclebackend.Nft.entity.NftItem;
import com.example.lyclebackend.Quest.entity.Quest;
import com.example.lyclebackend.Quest.entity.SuccessQuest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostSuccessQuestListDto {
    private Long questId;

    private String category;

    private Integer level;

    private String needNft;

    private Integer needTimes;

    private Integer needToken;

    private Integer rewardToken;

    private LocalDateTime expiredDate;

    private Integer goal;

    public SuccessQuest toEntity(){
        SuccessQuest build = SuccessQuest.builder()
                .category(category)
                .level(level)
                .needNft(needNft)
                .needTimes(needTimes)
                .needToken(needToken)
                .rewardToken(rewardToken)
                .expiredDate(expiredDate)
                .goal(goal)
                .build();
        return build;
    }
}
