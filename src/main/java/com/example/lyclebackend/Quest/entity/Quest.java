package com.example.lyclebackend.Quest.entity;

import com.example.lyclebackend.Quest.dto.PutQuestListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="quest")
@Entity
@AllArgsConstructor
@Builder
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="quest_id")
    private Long questId;
    //왜 여기에는 column 따로 안 붙힘?
    private String category;

    private Integer level;

    @Column(name = "need_nft")
    private String needNft;

    @Column(name = "need_time")
    private Integer needTime;

    @Column(name = "need_token")
    private Integer needToken;

    @Column(name = "reward_token")
    private Integer rewardToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    private Integer goal;

    private Integer times;

    @OneToMany(mappedBy = "quest", fetch = FetchType.LAZY)
    private List<SuccessQuest> successQuestList = new ArrayList<>();

    @OneToMany(mappedBy = "quest", fetch = FetchType.LAZY)
    private List<CurrentQuest> currentQuestList = new ArrayList<>();

    public void update(PutQuestListDto putQuestListDto) {
        this.category = putQuestListDto.getCategory();
        this.level = putQuestListDto.getLevel();
        this.needNft = putQuestListDto.getNeedNft();
        this.needTime = putQuestListDto.getNeedTime();
        this.needToken = putQuestListDto.getNeedToken();
        this.rewardToken = putQuestListDto.getRewardToken();
        this.startDate = putQuestListDto.getStartDate();
        this.finishDate = putQuestListDto.getFinishDate();
        this.goal = putQuestListDto.getGoal();
        this.times = putQuestListDto.getTimes();
    }
}