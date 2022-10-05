package com.example.lyclebackend.Quest.repository;

import com.example.lyclebackend.Quest.dto.FindSuccessQuestListDto;
import com.example.lyclebackend.Quest.entity.QSuccessQuest;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class SuccessQuestRepositoryImpl implements CustomSuccessQuestRepository{

    private final JPAQueryFactory queryFactory;

    QSuccessQuest s = new QSuccessQuest("s");

    public SuccessQuestRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<FindSuccessQuestListDto> findSuccessQuestList(String category) {
        return queryFactory
                .select(Projections.bean(FindSuccessQuestListDto.class, s.successQuestId, s.category, s.level, s.needNft, s.needTimes, s.needToken,
                        s.rewardToken, s.goal, s.expiredDate, s.member.memberId))
                .from(s)
                .where(s.category.eq(category))
                .fetch();
    }

    @Override
    public FindSuccessQuestListDto findSuccessQuestListBy(Long successQuestId, Long memberId) {
        return (FindSuccessQuestListDto) queryFactory
                .select(Projections.bean(FindSuccessQuestListDto.class, s.successQuestId, s.category, s.level, s.needNft, s.needTimes, s.needToken,
                        s.rewardToken, s.goal, s.expiredDate, s.member.memberId))
                .from(s)
                .where(s.successQuestId.eq(successQuestId))
                .fetch();
    }

}
