package com.example.lyclebackend.Quest.entity;


import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Quest.dto.PutQuestListDto;
import com.example.lyclebackend.Quest.dto.PutSuccessQuestListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="success_quest")
@Entity
@AllArgsConstructor
@Builder
public class SuccessQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="success_quest_id")
    private Long successQuestId;

    private String category;

    private Integer level;

    @Column(name = "need_nft")
    private String needNft;

    @Column(name = "need_times")
    private Integer needTimes;

    @Column(name = "need_token")
    private Integer needToken;

    @Column(name = "reward_token")
    private Integer rewardToken;

    private Integer goal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_member_successquest"), insertable = false , updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "quest_id", foreignKey = @ForeignKey(name = "FK_quest_successquest"), insertable = false , updatable = false)
    private Quest quest;

    public void update(PutSuccessQuestListDto putSuccessQuestListDto) {
        this.category = putSuccessQuestListDto.getCategory();
        this.level = putSuccessQuestListDto.getLevel();
        this.expiredDate = putSuccessQuestListDto.getExpiredDate();
    }
}