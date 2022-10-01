package com.example.lyclebackend.Quest.repository;

import com.example.lyclebackend.Member.entity.QMember;
import com.example.lyclebackend.Quest.dto.FindQuestListDto;
import com.example.lyclebackend.Quest.entity.QQuest;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class QuestRepositoryImpl implements CustomQuestRepository{

    private final JPAQueryFactory queryFactory;

    QQuest q = new QQuest("q");

    public QuestRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<FindQuestListDto> findQuestList(String category) {
        return queryFactory
                .select(Projections.bean(FindQuestListDto.class, q.questId, q.category, q.level, q.needNft, q.needTime, q.needToken, q.rewardToken, q.startDate, q.finishDate, q.goal, q.times))
                .from(q)
                .where(q.category.eq(category))
                .fetch();
    }

    @Override
    public FindQuestListDto findQuestListBy(Long questId, Long memberId) {
        return queryFactory
                .select(Projections.bean(FindQuestListDto.class, q.questId, q.category, q.level, q.needNft, q.needTime, q.needToken, q.rewardToken, q.startDate, q.finishDate, q.goal, q.times))
                .from(q)
                .where(q.questId.eq(questId))
                .fetchOne();
    }

}
