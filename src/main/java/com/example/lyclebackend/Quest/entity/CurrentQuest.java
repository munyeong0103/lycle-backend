package com.example.lyclebackend.Quest.entity;

import com.example.lyclebackend.Member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="current_quest")
@Entity
@AllArgsConstructor
@Builder
public class CurrentQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="current_quest_id")
    private Long currentQuestId;

    private String category;

    private Integer level;

    @Column(name = "need_token")
    private Integer needToken;

    @Column(name = "reward_token")
    private Integer rewardToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "start_time")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "finish_time")
    private LocalDateTime finishDate;

    @Column(name= "achievement_json")
    private Integer goal;

    private Integer times;

    private String nftNeed;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_member_currentquest"), insertable = false , updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "quest_id", foreignKey = @ForeignKey(name = "FK_quest_currentquest"), insertable = false , updatable = false)
    private Quest quest;
}
