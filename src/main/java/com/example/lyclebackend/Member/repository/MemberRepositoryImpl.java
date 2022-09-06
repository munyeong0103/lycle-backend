package com.example.lyclebackend.Member.repository;

import com.example.lyclebackend.Member.dto.FindMyPageDto;
import com.example.lyclebackend.Member.entity.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class MemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory queryFactory;

    QMember m = new QMember("m");

    public MemberRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public FindMyPageDto findMemberBy(Long myPageMemberId, Long memberId) {
        return queryFactory
                .select(Projections.bean(FindMyPageDto.class, m.memberId, m.accountName, m.nickname, m.email, m.profileImg, m.walletAddress, m.tokenCnt,  m.memberId.eq(memberId).as("isOwner")))
                .from(m)
                .where(m.memberId.eq(myPageMemberId))
                .fetchOne();

    }
}
