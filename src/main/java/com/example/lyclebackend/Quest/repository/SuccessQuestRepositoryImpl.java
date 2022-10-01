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

    QSuccessQuest q = new QSuccessQuest("q");

    public SuccessQuestRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<FindSuccessQuestListDto> findSuccessQuestList(String category) {
        return queryFactory
                .select(Projections.bean(FindSuccessQuestListDto.class, q.successQuestId, q.category, q.level, q.needNft, q.needTime, q.needToken,
                        q.rewardToken, q.goal, q.expiredDate, q.member.memberId))
                .from(q)
                .where(q.category.eq(category))
                .fetch();
    }

}
