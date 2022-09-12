package com.example.lyclebackend.Quest.entity;


import com.example.lyclebackend.Member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    @Column(name = "nft_need")
    private String nftNeed;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_member_successquest"), insertable = false , updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "quest_id", foreignKey = @ForeignKey(name = "FK_quest_successquest"), insertable = false , updatable = false)
    private Quest quest;
}
